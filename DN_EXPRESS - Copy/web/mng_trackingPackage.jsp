<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>DN EXPRESS</title>

    <!-- External CSS and Fonts -->
    <link href="https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css" integrity="sha512-MV7K8+y+gLIBoVD59lQIYicR65iaqukzvf/nwasF0nqhPay5w/9lJmVM2hMDcnK1OnMGCdVK+iQrJ7lzPJQd1w==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link href="css/mng_trackingPackage.css" rel="stylesheet" type="text/css"/>
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
                <a href="#" class="nav-link"><strong>Tracking Package</strong></a>
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
                <header>
                    <form class="filterEntries"method="post">                      
                        <div class="filter" style="width: 50%;">
                            <label for="search" style="color: #083c3e;"><strong>Order ID: </strong></label>
                            <input type="search" id="search" placeholder="Enter here" name="searchIndex">                            
                        </div>
                        <button type="submit" class="filter">Search</button>
                    </form>
                </header>
                <section class="table__body">
                    <c:if test="${not empty packageDetail}">
                    <div class="reiceve_card" id="shipment_info">
                  

                            <header class="header">
                                <h1>Shipment Information</h1>
                            </header>
                            <form action="#" class="form" method="POST">
                                
                                <div style="display: flex; justify-content: space-between;border-bottom: 1px solid #d0d0d0;padding-bottom: 1em;">   
                                    
                                    <div class="card">
                                            <table>
                                                    <tbody>
                                                        <tr>
                                                            <td><strong>Sender</strong></td>
                                                            <td>                                       
                                                                ${packageDetail.shipment_order.customer.account.firstName} ${packageDetail.shipment_order.customer.account.lastName}                                                                    
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td><strong>Phone:</strong></td>
                                                            <td>                                                                                    
                                                               ${packageDetail.shipment_order.customer.account.phone_numb}
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td><strong>Address:</strong></td>
                                                            <td>
                                                                ${packageDetail.shipment_order.pick_up_apartment_number} ${packageDetail.shipment_order.pick_up_street_name}, ${packageDetail.shipment_order.pick_up_district},
                                                                ${packageDetail.shipment_order.pick_up_ward}, ${packageDetail.shipment_order.pick_up_city}
                                                            </td>
                                                        </tr>
                                                        
                                                    </tbody>

                                                </table>                                          
                                        </div>
                                    

                                    
                                        
                                        <div class="card">
                                            <table>
                                                    <tbody>
                                                        <tr>
                                                            <td><strong>Receiver</strong></td>
                                                            <td>                                       
                                                               ${packageDetail.shipment_order.final_receiver_name}                                                                  
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td><strong>Phone:</strong></td>
                                                            <td>                                                                                    
                                                               ${packageDetail.shipment_order.final_receiver_phone}
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td><strong>Address:</strong></td>
                                                            <td>
                                                                ${packageDetail.shipment_order.final_apartment_number} ${packageDetail.shipment_order.final_street_name}, ${packageDetail.shipment_order.final_district}, 
                                                                          ${packageDetail.shipment_order.final_ward}, ${packageDetail.shipment_order.final_city}
                                                            </td>
                                                        </tr>
                                                        
                                                    </tbody>
                                                </table>      
                                        </div>
                                 </div>               
                                    <div style="display: flex;">

                                            <div class="card_info">               
                                                <table style="inline-size: 38%;">
                                                    <tbody>
                                                        <tr>
                                                            <td><strong>Packge Type:</strong></td>
                                                            <td>
                                                                
                                                                ${packageDetail.package_type.package_type_value}
                                                              
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td><strong>Package Size:</strong></td>
                                                            <td>
                                                                
                                                                ${packageDetail.package_size.package_size_value}
                                                               
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td><strong>Driver ID:</strong></td>
                                                            
                                                            <td>
                                                                <c:choose>
                                                                    <c:when test="${order_Status.shipment_Order_driver.driver.account.account_id == null}">
                                                                        Waiting
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        ${order_Status.shipment_Order_driver.driver.account.account_id}
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </td>
                                                        </tr>
                                                         <tr>
                                                            <td><strong>Amount:</strong></td>
                                                            <td>
                                                                ${invoice.total_amount}
                                                            </td>
                                                        </tr>
                                                    </tbody>
                                                </table>    
                                            </div>
                                            <div class="card_info"> 
                                                <table style="inline-size: 50%;">
                                                    <tbody>
                                                        <tr>
                                                            <td><strong>Package Weight:</strong></td>
                                                            <td>${packageDetail.package_weight}</td>
                                                        </tr>
                                                        <tr>
                                                            <td><strong>Value of Package:</strong></td>
                                                            <td>${packageDetail.package_value}</td>
                                                        </tr>
                                                         <tr>
                                                            <td><strong>Status:</strong></td>
                                                            <td>
                                                                <c:choose>
                                                                    <c:when test="${order_Status.status == null}">
                                                                        Waiting
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        ${order_Status.status}
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </td>
                                                            
                                                        </tr>
                                                        <tr>
                                                            <td><strong>View location:</strong></td>
                                                            <c:choose>
                                                                    <c:when test="${order_Status.status == null}">
                                                                        
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <td><i class="viewInforBtn fa-regular fa-eye"></i></td>
                                                                        <td id="latitude" hidden>${latitude}</td>
                                                                        <td id="longitude" hidden>${longitude}</td>
                                                                    </c:otherwise>
                                                            </c:choose>
                                                                        
                                                        </tr>
                                                    </tbody>

                                                </table>
                                            </div>

                                    </div>
                            </form>

                    
                </div>
                     </c:if>
                    
                    <c:if test="${empty packageDetail}">
                        <div class="empty" align="center"><strong>${mgs}</strong></div>
                    </c:if>
                </section>
                <div class="mapintit" id="map"></div>

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
    <script src="js/mng_trackingPackage.js" type="text/javascript"></script>
    <script>
    document.addEventListener('DOMContentLoaded', (event) => {
        document.querySelector('.viewInforBtn').addEventListener('click', function() {
            const latitude = document.getElementById('latitude').innerText.trim();
            const longitude = document.getElementById('longitude').innerText.trim();
            initMap(parseFloat(latitude), parseFloat(longitude));
        });
    });

    function initMap(latitude, longitude) {
        // Display the map container
        document.getElementById('map').style.display = 'block';

        // Create a map object and specify the DOM element for display.
        const map = new google.maps.Map(document.getElementById('map'), {
            center: { lat: latitude, lng: longitude },
            zoom: 16
        });

        // Create a marker and set its position.
        const marker = new google.maps.Marker({
            map: map,
            position: { lat: latitude, lng: longitude },
            title: 'Package Location'
        });
    }
</script>
</body>

</html>
