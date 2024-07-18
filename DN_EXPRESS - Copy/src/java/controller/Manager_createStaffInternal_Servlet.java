package controller;

import Impl.Duong.StaffImplement;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.Account;
import model.Post_Office;
import model.Staff;

@MultipartConfig
public class Manager_createStaffInternal_Servlet extends HttpServlet {
    static StaffImplement staffImplement = new StaffImplement();
    private static final String UPLOAD_DIRECTORY = "uploads"; // Định nghĩa thư mục upload
    private static final int MAX_WIDTH = 150;
    private static final int MAX_HEIGHT = 150;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        try {
            String fName = request.getParameter("firstName");
            String lName = request.getParameter("lastName");
            String gender = request.getParameter("gender");
            String cid = request.getParameter("cccd_numb");
            String dob = request.getParameter("dob");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone_numb");
            String idPO = request.getParameter("post_OfficeID");
            Part filePart = request.getPart("image"); // 'file' là name của input chứa file trong form
            
            if (filePart == null || filePart.getSize() == 0) {
                request.setAttribute("errorMessage", "Image upload failed. No file selected.");
                request.getRequestDispatcher("MNG_Create_Staff_Internal.jsp").forward(request, response);
                return;
            }

            String fileName = filePart.getSubmittedFileName();
            String applicationPath = request.getServletContext().getRealPath("");
            String uploadPath = applicationPath + File.separator + UPLOAD_DIRECTORY;

          
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            BufferedImage image = ImageIO.read(filePart.getInputStream());
            BufferedImage scaledImage = scaleImage(image, MAX_WIDTH, MAX_HEIGHT);
            String filePath = uploadPath + File.separator + fileName;
            File outputFile = new File(filePath);
            ImageIO.write(scaledImage, "jpg", outputFile);

            String imagePath = UPLOAD_DIRECTORY + "/" + fileName; // Lưu đường dẫn ảnh vào CSDL

            int id_PO;
            try {
                id_PO = Integer.parseInt(idPO);
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Post Office ID");
                return;
            }

            char gen = gender.charAt(0);
            Date dobDate;
            try {
                dobDate = Date.valueOf(dob);
            } catch (IllegalArgumentException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid date format");
                return;
            }

            // Check if Post Office ID exists
            if (!staffImplement.checkPostOfficeExists(id_PO)) {
                request.setAttribute("errorMessage", "Post Office ID does not exist");
                request.getRequestDispatcher("MNG_Create_Staff_Internal.jsp").forward(request, response);
                return;
            }

            // Create Account and Driver objects
            Account newAccount = new Account();
            newAccount.setFirstName(fName);
            newAccount.setLastName(lName);
            newAccount.setEmail(email);
            newAccount.setPhone_numb(phone);
            newAccount.setDob(dobDate);
            newAccount.setCccd_numb(cid);
            newAccount.setAvatar(imagePath); // Lưu đường dẫn ảnh vào thuộc tính avatar
            newAccount.setGender(gen);
            newAccount.setRole(3);
            newAccount.setPassword("123456");

            Post_Office PO = new Post_Office();
            PO.setPost_office_id(id_PO);

            Staff newStaff = new Staff(PO, newAccount);

            // Call DAO to add staff
            staffImplement.addStaff(newStaff, id_PO);

            // Redirect or forward as needed
            response.sendRedirect("Manager_listStaffInternal_Servlet");
        } finally {
            out.close();
        }
    }

    private BufferedImage scaleImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        g.dispose();

        return resizedImage;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
