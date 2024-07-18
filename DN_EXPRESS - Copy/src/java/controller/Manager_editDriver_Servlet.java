package controller;

import Impl.Duong.DriverImplement;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;
import model.Account;
import model.Driver;
import model.Post_Office;

@MultipartConfig
public class Manager_editDriver_Servlet extends HttpServlet {

    static DriverImplement driverImplement = new DriverImplement();
    private static final String UPLOAD_DIRECTORY = "uploads"; // Định nghĩa thư mục upload
    private static final int MAX_WIDTH = 150;
    private static final int MAX_HEIGHT = 150;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            out.println("Start processing request");

            String accountIdParam = request.getParameter("account_id");
            String driverTypeIdParam = request.getParameter("driv_type_id");
            String fName = request.getParameter("fistName");
            String lName = request.getParameter("lastName");
            String gender = request.getParameter("gender");
            String license = request.getParameter("license_numb");
            String cid = request.getParameter("cccd_numb");
            String dob = request.getParameter("dob");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone_numb");
            String idPO = request.getParameter("post_OfficeID");
            String roleId = request.getParameter("roleId");

            // Lấy đường dẫn ảnh hiện tại từ input hidden
            String existingImagePath = request.getParameter("existingImage");

            // Kiểm tra và xử lý file upload ảnh
            Part filePart = request.getPart("image"); // 'image' là name của input chứa file trong form
            String imagePath = existingImagePath; // Đặt mặc định là đường dẫn ảnh hiện tại

            if (filePart != null && filePart.getSize() > 0) {
                // Kiểm tra loại content của file
                String contentType = filePart.getContentType();
                if (contentType == null || !contentType.toLowerCase().startsWith("image/") || contentType.toLowerCase().startsWith("image/png")) {
                    request.setAttribute("errorMessage", "Invalid file format. Please upload an image file (JPEG/JPG/PNG).");
                    request.getRequestDispatcher("MNG_Edit_Driver_Details.jsp").forward(request, response);
                    return;
                }

                // Kiểm tra extension của file
                String fileName = filePart.getSubmittedFileName();
                String fileExtension = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
                if (!(fileExtension.equals(".jpg") || fileExtension.equals(".jpeg") || fileExtension.equals(".png"))) {
                    request.setAttribute("errorMessage", "Invalid file format. Please upload a JPEG/JPG/PNG image file.");
                    request.getRequestDispatcher("MNG_Edit_Driver_Details.jsp").forward(request, response);
                    return;
                }

                // Xử lý lưu trữ file ảnh
                String applicationPath = request.getServletContext().getRealPath("");
                String uploadPath = applicationPath + File.separator + UPLOAD_DIRECTORY;

                // Kiểm tra và tạo thư mục nếu chưa tồn tại
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }

                BufferedImage imageBuffer = ImageIO.read(filePart.getInputStream());
                BufferedImage scaledImage = scaleImage(imageBuffer, MAX_WIDTH, MAX_HEIGHT);
                String filePath = uploadPath + File.separator + fileName;
                File outputFile = new File(filePath);
                ImageIO.write(scaledImage, "jpg", outputFile);

                imagePath = UPLOAD_DIRECTORY + "/" + fileName; // Lưu đường dẫn ảnh mới vào CSDL
            }

            // Parse các giá trị từ request
            int accountId = Integer.parseInt(accountIdParam);
            int driverTypeId = Integer.parseInt(driverTypeIdParam);
            int id_PO = Integer.parseInt(idPO);
            int roleId_int = Integer.parseInt(roleId);
            char gender_char = gender.charAt(0);
            Date dobDate = Date.valueOf(dob);

            // Kiểm tra Post Office ID tồn tại
            if (!driverImplement.checkPostOfficeExists(id_PO)) {
                request.setAttribute("errorMessage", "Post Office ID does not exist");
                request.getRequestDispatcher("MNG_Edit_Driver_Details.jsp").forward(request, response);
                return;
            }

            // Tạo Account và Driver mới
            Account editAccount = new Account();
            editAccount.setAccount_id(accountId);
            editAccount.setFirstName(fName);
            editAccount.setLastName(lName);
            editAccount.setEmail(email);
            editAccount.setPhone_numb(phone);
            editAccount.setDob(dobDate);
            editAccount.setCccd_numb(cid);
            editAccount.setAvatar(imagePath); // Lưu đường dẫn ảnh vào thuộc tính avatar
            editAccount.setGender(gender_char);
            editAccount.setRole(roleId_int);

          

            Post_Office PO = new Post_Office();
            PO.setPost_office_id(id_PO);

            Driver editDriver = new Driver();
            editDriver.setAccount(editAccount);
            editDriver.setLicense_numb(license);
            editDriver.setPost_office(PO);

            // Gọi DAO để chỉnh sửa thông tin Driver
            driverImplement.editDriver(editDriver, driverTypeId);

            // Chuyển hướng hoặc forward cần thiết
            response.sendRedirect("Manager_ListDriverStaff_Servlet");

            out.println("Request processed successfully");
        } catch (Exception e) {
            out.println("Exception occurred: " + e.getMessage());
            e.printStackTrace(out);
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
