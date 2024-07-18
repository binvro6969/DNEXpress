<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>DN EXPRESS</title>

    <link href="https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css" integrity="sha512-MV7K8+y+gLIBoVD59lQIYicR65iaqukzvf/nwasF0nqhPay5w/9lJmVM2hMDcnK1OnMGCdVK+iQrJ7lzPJQd1w==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link href="css/mng_trackingDriver_view_details.css" rel="stylesheet" type="text/css"/>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA1mLLNrhFhndWA119ZTywc38Fyw9cyas0"></script>
</head>

<body>
    <section class="confiq">
        <!-- SIDEBAR -->
        <%@include file="/include/sidebar_mng.jsp" %>
        <!-- SIDEBAR -->

        <!-- CONTENT -->
        <section id="content">
            <!-- NAVBAR -->
            <nav>
                <i class='bx bx-menu'></i>
                <a href="#" class="nav-link"><strong>Tracking Driver</strong></a>
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
                <a href="#" class="profile"><img src="img/img2.jpg" alt="Profile"></a>
            </nav>
            <!-- NAVBAR -->

            <!-- MAIN -->
            <main class="table" id="customers_table">
                
                <div id="map" style="height: 100vh;" 
                    data-latitude="${latitude}" 
                    data-longitude="${longitude}">
                </div>
                
                <div class="reiceve_card" id="driver_info">
                    <header class="header">
                        <h1>Driver Information</h1>
                    </header>
                    
                    <c:forEach var="drv" items="${driverList}">
                        <form action="#" class="form" method="POST">
                            <div>
                                <h2>Personal Information</h2>
                                <div class="card">
                                    <table>
                                        <tbody>
                                            <tr>
                                                <td><strong>Name</strong></td>
                                                <td>${drv.account.firstName} ${drv.account.lastName}</td>
                                            </tr>
                                            <tr>
                                                <td><strong>CID</strong></td>
                                                <td>${drv.account.cccd_numb}</td>
                                            </tr>
                                            <tr>
                                                <td><strong>DOB</strong></td>
                                                <td>${drv.account.dob}</td>
                                            </tr>
                                            <tr>
                                                <td><strong>Gender</strong></td>
                                                <td>${drv.account.gender}</td>
                                            </tr>
                                            <tr>
                                                <td><strong>Phone</strong></td>
                                                <td>${drv.account.phone_numb}</td>
                                            </tr>
                                            <tr>
                                                <td><strong>Mail</strong></td>
                                                 
                                                <td>${drv.account.email}</td>
                                                
                                            </tr>
                                        </tbody>                                 
                                    </table>
                                </div>
                            </div>

                            <div>
                                <h2>Vehical Information</h2>
                                <div class="card">
                                    <table>
                                        <tbody>
                                            <tr>
                                                <td><strong>Lisence</strong></td>
                                                <td>${drv.license_numb}</td>
                                            </tr>
                                            <tr>
                                                <td><strong>Vehical</strong></td>
                                                <c:forEach var="vhc" items="${vehicleList}">
                                                <td>${vhc.vehicle_type.vehicle_type_value}</td>
                                                 </c:forEach>
                                            </tr>
                                        </tbody>                                 
                                    </table>
                                </div>
                            </div>

                            <div>
                                <h2>Other Information</h2>
                                <div class="card">
                                    <table>
                                        <tbody>
                                            <tr>
                                                <td><strong>Rating</strong></td>
                                                <td>${drv.rating}</td>
                                            </tr>
                                            <tr>
                                                <td><strong>Status</strong></td>
                                                <td><p class="status ${drv.status ? 'Online' : 'Offline'}">${drv.status ? 'Online' : 'Offline'}</p></td>
                                            </tr>
                                            <tr>
                                                <td><strong>Post Office</strong></td>
                                                <td>${drv.post_office.city}</td>
                                            </tr>
                                            <tr>
                                                <td><strong>Have a shipment</strong></td>
                                                <td>${haveShipment ? 'Yes' : 'No' }</td>
                                            </tr>
                                        </tbody>                                 
                                    </table>
                                </div>
                            </div>

                            <div class="btn_choose">
                                <button class="btn_reject button--full" id="back_btn" type="button">Back</button>
                                <button class="btn_accept button--full" id="shipment_list_btn" type="button">Shipment</button>
                            </div>
                        </form>
                    </c:forEach>
                </div>
                
                <div class="reiceve_card_list" id="shipment_list" style="display:none; align-content:flex-start;">
                    <c:if test="${not empty packageDetailsList}">
                        
                    
                        <header class="header">
                            <h1>Driver's Shipment</h1>
                        </header>
                        <c:forEach var="packageDetail" items="${packageDetailsList}">
                            <form action="#" class="form" style="padding: 10px 0;" method="POST">

                                <div>
                                    <div class="card view_packageDetail_btn" data-order-id="${packageDetail.shipment_order.order_id}">
                                        <table>
                                        <tbody>
                                            <tr>
                                                <td><strong>Order ID :</strong></td>
                                                <td>${packageDetail.shipment_order.order_id}</td>
                                            </tr>
                                            <tr>
                                                <td><strong>Customer :</strong></td>
                                                <td>${packageDetail.shipment_order.customer.account.firstName} ${packageDetail.shipment_order.customer.account.lastName}</td>
                                            </tr>
                                        </tbody>                                 
                                    </table>
                                    </div>
                                </div>
                            </form>
                        </c:forEach>
                      
                    </c:if>
                    
                    <c:if test="${empty packageDetailsList}">
                        <div class="empty" align="center"><strong>Driver not in a shipment</strong></div>
                    </c:if>
                        <div class="btn_choose" >
                            <button class="btn_reject button--full" id="back_shipment_btn" type="button">Back</button>
                        </div>
                </div>

                <div class="reiceve_card" id="shipment_info" style="display:none;">
                  <c:if test="${not empty packageDetailsList}">
                      <div id="shipment_detail_content">
                          
                      </div>
   
                   </c:if>
                    
                    <c:if test="${empty packageDetailsList}">
                        <div class="empty" align="center"><strong>Driver not in a shipment</strong></div>
                    </c:if>
                    <div class="btn_choose">
                                    <button class="btn_reject button--full" id="back_shipment__list_btn" type="button">Back</button>
                                    <button class="btn_accept button--full" id="contact_btn" type="button">Contact</button>
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
    <script src="js/mng_trackDriver_viewDetails.js" type="text/javascript"></script>
    
</body>

</html>
