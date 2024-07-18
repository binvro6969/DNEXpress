<%-- 
    Document   : DRV_ReceiveOrder_ListOrder
    Created on : Jun 29, 2024, 8:41:42 PM
    Author     : haian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- Boxicons -->
        <link href='https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css' rel='stylesheet'>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css"
              integrity="sha512-MV7K8+y+gLIBoVD59lQIYicR65iaqukzvf/nwasF0nqhPay5w/9lJmVM2hMDcnK1OnMGCdVK+iQrJ7lzPJQd1w=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <!-- My CSS -->
        <link rel="stylesheet" href="css/delivery_order.css">
        <link rel="stylesheet" type="text/css" href="css/footer.css">
        <link rel="stylesheet" href="css/order_history_style.css">

        <title>DN EXPRESS</title>
    </head>
    <section class="confiq">
            <!-- SIDEBAR -->
            <%@include file="/include/sidebar_drv.jsp" %>
            <!-- SIDEBAR -->



            <!-- CONTENT -->
            <section id="content">
                <!-- NAVBAR -->
                <nav>
                    <i class='bx bx-menu'></i>
                    <a href="#" class="nav-link"><strong>Shipment</strong></a>
                    <form action="#">
                        <div class="form-input">
                            <input type="search" placeholder="Search">
                            <button type="submit" class="search-btn"><i class='bx bx-search'></i></button>
                        </div>
                    </form>


                    <input type="checkbox" id="switch-mode" hidden>
                    <label for="switch-mode" class="switch-mode"></label>
                    <a href="#" class="notification">
                        <i class='bx bxs-bell'></i>
                        <span class="num">8</span>
                    </a>
                    <a href="#" class="profile">
                        <img src="img/img2.jpg">
                    </a>
                </nav>
                <!-- NAVBAR -->

                <!-- MAIN -->
                <main class="table" id="customers_table">
                    
                    <section class="table__body">
                        <c:if test="${empty list}">
                            <h1>You don't have any orders yet</h1>
                        </c:if>
                        <c:if test="${not empty list}">
                            <table>
                                <thead>
                                    <tr>
                                        <th> Id </th>
                                        <th> Receiver </th>
                                        <th> Receiver Number</th>
                                        <th> Address</th>
                                        <th> Fee</th>
                                        <th> Status</th>
                                        <th> Detail</th>                                       
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="list" items="${list}">
                                        <tr>
                                            <td>${list.invoice.shipment_order.order_id}</td>
                                            <td>${list.invoice.shipment_order.final_receiver_name}</td>
                                            <td>${list.invoice.shipment_order.final_receiver_phone}</td>
                                            <td>${list.invoice.shipment_order.final_apartment_number}, ${list.invoice.shipment_order.final_street_name}, ${list.invoice.shipment_order.final_district}, ${list.invoice.shipment_order.final_ward}, ${list.invoice.shipment_order.final_city}</td>
                                            <td>${list.invoice.total_amount}</td>
                                            <td>${list.order_Status.status}</td>
                                            <td><a href="Driver_ReceiveOrder_DetailOrder_Servlet?id=${list.invoice.shipment_order.order_id}" class="btn btn-warning btn-sm">Detail</a></td>
                                        </tr>
                                    </c:forEach>                                                                                              
                                </tbody>
                            </table>
                        </c:if>
                        
                    </section>
                <!-- MAIN -->
            </section>
        </section>
        <!-- CONTENT -->

        <!-- FOOTER -->
        <%@include file="/include/footer.jsp" %>
        <!-- FOOTER -->
        <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
        <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
        <script src="js/delivery_order.js"></script>
        <script src="js/order_history.js"></script>
    </body>
</html>
