/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import Impl.Minh.OrderImpl;
import Impl.Minh.PackageImpl;
import Impl.Minh.ServiceImpl;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import model.Account;
import model.Delivery_Type;
import model.Package_Size;
import model.Package_Type;
import model.Payment_Method;
import model.Service_Property;

/**
 *
 * @author dangq
 */
public class Customer_CreateOrder_Servlet extends HttpServlet {

    static OrderImpl orderImpl = new OrderImpl();
    static ServiceImpl serviceImpl = new ServiceImpl();
    static PackageImpl packageImpl = new PackageImpl();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Customer_CreateOrder_Servlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Customer_CreateOrder_Servlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("loginSession");

        if (account == null) {
            response.sendRedirect("Authentication_LoginMain_Servlet");
        } else {
            request.setAttribute("account", account);

            List<Package_Type> packageTypes = packageImpl.getAllPackageTypes();
            session.setAttribute("packageTypes", packageTypes);

            List<Package_Size> packageSizes = packageImpl.getAllPackageSizes();
            session.setAttribute("packageSizes", packageSizes);

            List<Delivery_Type> deliveryTypes = packageImpl.getAllDeliveryTypes();
            session.setAttribute("deliveryTypes", deliveryTypes);

            int warrantyServiceTypeId = 1;

            List<Service_Property> warrantyOptions = serviceImpl.getServicePropertiesByServiceType(warrantyServiceTypeId);
            session.setAttribute("warrantyOptions", warrantyOptions);

            List<Payment_Method> paymentOptions = orderImpl.getAllPaymentMethods();
            session.setAttribute("paymentOptions", paymentOptions);

            request.getRequestDispatcher("CUS_CreateOrder.jsp").forward(request, response);
        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();

        String sendName = request.getParameter("send-name");
        String sendPhone = request.getParameter("send-phone");
        String recName = request.getParameter("rec-name");
        String recPhone = request.getParameter("rec-phone");
        String sendAdd = request.getParameter("send-address");
        String recAdd = request.getParameter("rec-address");
        int typeShipmentId = Integer.parseInt(request.getParameter("type-shipment"));
//        String requestDeliveryDate = request.getParameter("req-date").replace(" ", "T");
        String requestDeliveryDate = request.getParameter("req-date");

        int sizeId = Integer.parseInt(request.getParameter("select-size"));
        float weight = Float.parseFloat(request.getParameter("weight"));
        int typePackageId = Integer.parseInt(request.getParameter("type-package"));
        int warrantyValue = Integer.parseInt(request.getParameter("warranty"));
        float trueValue = Float.parseFloat(request.getParameter("true-value"));
        String note = request.getParameter("note");
        int paymentId = Integer.parseInt(request.getParameter("payment"));

        boolean inProvince = Boolean.parseBoolean(request.getParameter("inProvince"));

        String receivingNote = request.getParameter("rec-note");
//        String requestReceiveDate = request.getParameter("rec-date").replace(" ", "T");
        String requestReceiveDate = request.getParameter("rec-date");

        float totalMoney = Float.parseFloat(request.getParameter("total-value"));
        float serviceFee = Float.parseFloat(request.getParameter("service-fee"));

        String createdDate = request.getParameter("created-date");

        // Sender address components
        String senderStreetNumber = request.getParameter("streetNumber-1");
        String senderStreetName = request.getParameter("streetName-1");
        String senderWard = request.getParameter("ward-1");
        String senderDistrict = request.getParameter("district-1");
        String senderCity = request.getParameter("city-1");
        String senderCountry = request.getParameter("country-1");

        // Receiver address components
        String receiverStreetNumber = request.getParameter("streetNumber-2");
        String receiverStreetName = request.getParameter("streetName-2");
        String receiverWard = request.getParameter("ward-2");
        String receiverDistrict = request.getParameter("district-2");
        String receiverCity = request.getParameter("city-2");
        String receiverCountry = request.getParameter("country-2");

        // Set sender address components to session
        session.setAttribute("senderStreetNumber", senderStreetNumber);
        session.setAttribute("senderStreetName", senderStreetName);
        session.setAttribute("senderWard", senderWard);
        session.setAttribute("senderDistrict", senderDistrict);
        session.setAttribute("senderCity", senderCity);
        session.setAttribute("senderCountry", senderCountry);

        // Set receiver address components to session
        session.setAttribute("receiverStreetNumber", receiverStreetNumber);
        session.setAttribute("receiverStreetName", receiverStreetName);
        session.setAttribute("receiverWard", receiverWard);
        session.setAttribute("receiverDistrict", receiverDistrict);
        session.setAttribute("receiverCity", receiverCity);
        session.setAttribute("receiverCountry", receiverCountry);

        session.setAttribute("sendName", sendName);
        session.setAttribute("sendPhone", sendPhone);
        session.setAttribute("recName", recName);
        session.setAttribute("recPhone", recPhone);
        session.setAttribute("sendAdd", sendAdd);
        session.setAttribute("recAdd", recAdd);
        session.setAttribute("typeShipmentId", typeShipmentId);
        session.setAttribute("requestDeliveryDate", requestDeliveryDate);
        session.setAttribute("sizeId", sizeId);
        session.setAttribute("weight", weight);
        session.setAttribute("typePackageId", typePackageId);
        session.setAttribute("warrantyValue", warrantyValue);
        session.setAttribute("trueValue", trueValue);
        session.setAttribute("note", note);
        session.setAttribute("paymentId", paymentId);
        session.setAttribute("inProvince", inProvince);
        session.setAttribute("receivingNote", receivingNote);
        session.setAttribute("requestReceiveDate", requestReceiveDate);
        session.setAttribute("totalMoney", totalMoney);
        session.setAttribute("serviceFee", serviceFee);
        session.setAttribute("createdDate", createdDate);

        //Upload image
        Part filePart = request.getPart("file");
        String uploadDir = "./img/upload";

        if (filePart != null && filePart.getSize() > 0) {

            String documentName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

            String[] allowedExtensions = {"jpg", "jpeg", "png"};
            String fileExtension = documentName.substring(documentName.lastIndexOf(".") + 1).toLowerCase();

            if (Arrays.asList(allowedExtensions).contains(fileExtension)) {

            } else {
                response.getWriter().println("Invalid file type. Allowed types: jpg, jpeg, png.");
                return;
            }

            String filePath = uploadDir + "/" + documentName;
            session.setAttribute("filePath", filePath);

            try (InputStream inputStream = filePart.getInputStream()) {
                String realPath = request.getServletContext().getRealPath(uploadDir);
                File uploadPath = new File(realPath);
                if (!uploadPath.exists()) {
                    uploadPath.mkdir();
                }
                Files.copy(inputStream, Paths.get(realPath, documentName), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
//            response.getWriter().println(filePath);
            System.out.println(filePath);

        } else {
//            response.getWriter().println("No file uploaded.");
            System.out.println("No file uploaded.");
        }

        session.setAttribute("isExecuted", false); //Prevent multiple creation while reload

        // Redirect based on payment method
        if (paymentId == 1) {
            // Cash payment
            response.sendRedirect("Customer_Bill_Servlet");
        } else if (paymentId == 2) {
            // Online payment
            response.sendRedirect("Svl_Payment_Servlet");
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
