<%-- 
    Document   : Scr_Order_History
    Created on : Jun 17, 2024, 11:58:21 AM
    Author     : DINH
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- Boxicons -->
        <link href='https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css' rel='stylesheet'>
        <!-- My CSS -->
        <link rel="stylesheet" href="css/order_history_style.css">
        <link rel="stylesheet" type="text/css" href="css/footer.css">

        <title>DN EXPRESS</title>
    </head>
    <body>

        <section class="confiq">
            <!-- SIDEBAR -->
            <%@include file="/include/sidebar_cus.jsp" %>
            <!-- SIDEBAR -->



            <!-- CONTENT -->
            <section id="content">
                <!-- NAVBAR -->
                <nav>
                    <i class='bx bx-menu' ></i>
                    <a href="#" class="nav-link" ><strong>Order History</strong></a>
                    <form action="#">
                        <div class="form-input">
                            <input type="search" placeholder="Enter driver Id">
                            <button type="submit" class="search-btn"><i class='bx bx-search' ></i></button>
                        </div>
                    </form>
                    <input type="checkbox" id="switch-mode" hidden>
                    <label for="switch-mode" class="switch-mode"></label>
                    <a href="#" class="notification">
                        <i class='bx bxs-bell' ></i>
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
                        <!--<form action="Cus_Order_History" method="GET">-->
                        <form action="Customer_CheckStatus_Servlet" method="get" id="orderForm">     
                            <table>
                                <thead>
                                    <tr>
                                        <th>Id</th>
                                        <th>Receiver</th>
                                        <th>Address</th>
                                        <th>Date</th>
                                        <th>Service Fee</th>
                                        <th>Total Amount</th>
                                        <th>Phone Number</th>
                                        <th>Rating</th>
                                    </tr>
                                </thead>
                                <tbody>

                                    <c:forEach var="order" items="${list}">
                                    <input type="hidden" name="orderId" id="orderId">

                                    <tr class="clickable-row" data-order-id="${order.shipment_order.order_id}">
                                        <td>${order.shipment_order.order_id}</td>
                                        <td>${order.shipment_order.final_receiver_name}</td>
                                        <td>${order.shipment_order.final_apartment_number}, ${order.shipment_order.final_street_name}, ${order.shipment_order.final_district}, ${order.shipment_order.final_ward}, ${order.shipment_order.final_city}</td>
                                        <td>${order.shipment_order.formattedCreatedDate}</td>
                                        <td>${order.service_fee}</td>
                                        <td>${order.total_amount}</td>
                                        <td>${order.shipment_order.final_receiver_phone}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${order.shipment_order.cust_driver_rating == 0}">
                                                    <a class="btnRating" href="CUS_RatingDriver.jsp?orderID=${order.shipment_order.order_id}">Rating Driver</a>                                                </c:when>
                                                <c:otherwise>
                                                    ${order.shipment_order.getCust_driver_rating()}
                                                </c:otherwise>
                                            </c:choose>
                                        </td>


                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </form>

                        <!-- </form>-->
                    </section>
                </main>
                <!-- MAIN -->
            </section>
        </section>
        <!-- CONTENT -->

        <%@include file="/include/footer.jsp" %>

        <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
        <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
        <script src="js/order_history.js"></script>
    </body>
</html>
