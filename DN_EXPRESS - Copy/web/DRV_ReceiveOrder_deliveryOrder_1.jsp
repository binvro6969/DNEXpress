<%-- 
    Document   : DRV_ReceiveOrder_deliveryOrder
    Created on : Jun 27, 2024, 1:54:51 PM
    Author     : haian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

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

        <title>DN EXPRESS</title>
    </head>
    <style>
        body {
            margin: 0;
            padding: 0;
        }

        #map {
            width: 100%;
            height: 100vh;
        }
    </style>
    <body>

        <section class="confiq">
            <!-- SIDEBAR -->
            <%@include file="/include/sidebar_drv.jsp"%>
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
           
                    </head>
                    <body>
                        <c:set var="package_Size_value" value="${requestScope.package_Size_value}" />
                        <c:set var="package_Type_value" value="${requestScope.package_Type_value}" />
                        <c:set var="package_Weight" value="${requestScope.package_Weight}" />
                        <c:set var="name" value="${requestScope.name}" />
                        <c:set var="phone" value="${requestScope.phone}" />
                        <c:set var="invoice_infor" value="${requestScope.shipment_Order_infor}" />
                        <c:set var="final_address" value="${requestScope.final_address}" />
                        <c:set var="originLat" value="${requestScope.origin_lat}" />
                        <c:set var="originLng" value="${requestScope.origin_lng}" />
                        <c:set var="destinationLat" value="${requestScope.destination_lat}" />
                        <c:set var="destinationLng" value="${requestScope.destination_lng}" />
                        <c:set var="keyAPI" value="${requestScope.key_API}" />
                        
                        <iframe width="100%" height="100%" style="border:0" loading="lazy" allowfullscreen
                            src="https://www.google.com/maps/embed/v1/directions?key=${keyAPI}&origin=${originLat},${originLng},&destination=${destinationLat},${destinationLng}"></iframe>
                        
                        <div class="reiceve_card">
                            <header class="header">
                                <h1>Order Information</h1>
                            </header>

                            <div>
                                <div>
                                    <h2>Pick Up information</h2>

                                    <div class="card_information">               
                                        <table>
                                            <tbody>
                                                <tr>
                                                    <td>Name</td>
                                                    <td>${name}</td>
                                                </tr>
                                                <tr>
                                                    <td>Phone</td>
                                                    <td>${phone}</td>
                                                </tr>                                              
                                        </table>

                                    </div>
                                </div>
                                <div>
                                    <h2> Receiver Information</h2>

                                    <div class="card">
                                        <address>
                                            <strong>Name:</strong><br />
                                            ${shipment_Order_infor.shipment_order.final_receiver_name}<br />
                                            <strong>Address:</strong><br />
                                            ${final_address}<br />
                                            <strong>Phone:</strong><br />
                                            ${shipment_Order_infor.shipment_order.final_receiver_phone}

                                        </address>
                                    </div>
                                </div>


                                <div>
                                    <h2>Package Details</h2>

                                    <div class="card_information">               
                                        <table>
                                            <tbody>
                                                <tr>
                                                    <td>Packge Type</td>
                                                    <td>${package_Type_value}</td>
                                                </tr>
                                                <tr>
                                                    <td>Package Size</td>
                                                    <td >${package_Size_value}</td>
                                                </tr>
                                                <tr>
                                                    <td>Package Weight</td>
                                                    <td >${package_Weight} Kg</td>
                                                </tr>                                                                                             
                                            </tbody>
                                            <tfoot>
                                                <tr>
                                                    <td><strong>Amount :</strong></td>
                                                    <td >${shipment_Order_infor.total_amount}$</td>
                                                </tr>
                                            </tfoot>
                                        </table>

                                    </div>
                                </div>

                                <div class="btn_choose">
                                    <button class="btn_call button--full " type="submit" ><i class='bx bx-phone-call'></i></button>
                                    <button class="btn_chat button--full" type="submit" ><i class='bx bx-envelope'></i></button>
                                    <button class="btn_report button--full" type="submit" ><i class='bx bxs-comment-error'></i></button>
                                </div>

                                <div class="btn_choose">
                                    <a href="Driver_ReceiveOrder_UpdateOrderStatus_Servlet?id=${shipment_Order_infor.shipment_order.order_id}" class="button--full"><button class="btn_arrived button--full">Arrived</button></a>                                                                                                       
                                </div>
                            </div>
                        </div>

                </main>
                <!-- MAIN -->
            </section>
        </section>
        <!-- CONTENT -->

        <!-- FOOTER -->
        <%@include file="/include/footer.jsp" %>
        <!-- FOOTER -->
        <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
        <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
        <script src="delivery_order.js"></script>
    </body>

</html>
