<%-- 
    Document   : Scr_Create_Order
    Created on : Jun 19, 2024, 2:25:47 PM
    Author     : dangq
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- Boostrap -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
        <!-- Boxicons -->
        <link href='https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css' rel='stylesheet'>
        <!-- My CSS -->
        <link rel="stylesheet" href="css/create_order_style.css">
        <link rel="stylesheet" type="text/css" href="css/footer.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">


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
                    <a href="#" class="nav-link" ><strong>Create Order</strong></a>
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
                        <img src="images/img2.jpg">
                    </a>
                </nav>
                <!-- NAVBAR -->
                <!-- Hero Section Starts -->

                <main id="create-order" class="d-flex justify-content-center">
                    <section class="col-md-8 ">
                        <form class="row flex-column" action="Customer_CreateOrder_Servlet" id="frmCreateOrder" method="post" enctype="multipart/form-data">

                            <header class="text-center py-3">
                                <h1>Order Information</h1>
                            </header>

                            <div class="form-content">

                                <div class="container">
                                    <div class="name col-md-10">
                                        <div class="column">
                                            <div class="row form-group">
                                                <label class="required">Sender's Name</label>
                                                <input type="text" required placeholder="Enter full name" class="name-input" name="send-name" value="${account.firstName} ${account.lastName}">
                                                <div class="error name-error"  hidden></div>

                                            </div>
                                            <div class="row form-group">
                                                <label class="required">Sender's Phone Number</label>
                                                <input type="tel" required placeholder="Enter phone number" class="phone-input" name="send-phone" value="${account.phone_numb}">
                                                <div class="error phone-error" hidden></div>

                                            </div>
                                        </div>
                                        <div class="column">
                                            <div class="row form-group">
                                                <label class="required">Receiver's Name</label>
                                                <input type="text" required placeholder="Enter full name" class="name-input" name="rec-name">
                                                <div class="error name-error"  hidden></div>

                                            </div>
                                            <div class="row form-group">
                                                <label class="required">Receiver's Phone Number</label>
                                                <input type="tel" required placeholder="Enter phone number" class="phone-input" name="rec-phone">
                                                <div class="error phone-error"  hidden></div>

                                            </div>
                                        </div>
                                    </div>
                                </div>


                                <div class="container">
                                    <div class="address col-md-10 py-4">
                                        <div class="form-group d-flex">
                                            <i class="fa-solid fa-location-dot location-1"></i>
                                            <label class="label1 required">Sender's Address</label>
                                            <div class="input-wrapper">
                                                <input id="startAutocomplete" type="text" placeholder="Enter a place" required name="send-address">

                                            </div>
                                        </div>
                                        <div class="form-group d-flex">
                                            <i class="fa-solid fa-location-dot location-2"></i>
                                            <label class="label2 required">Receiver's Address</label>
                                            <div class="input-wrapper" >
                                                <input id="endAutocomplete" type="text" placeholder="Enter a place" required name="rec-address">
                                            </div>
                                        </div>

                                        <div id="distance" >Distance: 0 km</div>
                                        <input type="hidden" id="inProvince" name="inProvince" value="false">

                                    </div>
                                </div>

                                <div class="container">
                                    <div class="type col-md-10">
                                        <div class="column col-md-12">
                                            <div class="form-group">
                                                <div class="label-icon">
                                                    <i class="fa-solid fa-truck-fast"></i>
                                                    <label class="required">Delivering Type</label>
                                                </div>
                                                <select class="dropdown" id="type-shipment" name="type-shipment">
                                                    <c:forEach var="deliveryType" items="${deliveryTypes}" >
                                                        <option value="${deliveryType.delivery_type_id}" data-price="${deliveryType.delivery_type_price}">${deliveryType.delivery_type_value}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="form-group">
                                                <div class="label-icon">
                                                    <i class="fa-solid fa-calendar-days"></i>
                                                    <label class="required">Picking up Time</label>
                                                </div>
                                                <select class="dropdown" id="select-date">
                                                    <option value="1">Now</option>
                                                    <option value="2">Later</option>
                                                </select>
                                                <input type="datetime-local" id="req-date" name="req-date" hidden>
                                            </div>

                                        </div>
                                        <div class="column col-md-12" id="specific-type" hidden>

                                            <div class="form-group" >
                                                <div class="label-icon">
                                                    <i class="fa-solid fa-calendar-days"></i>
                                                    <label >Receiving Note (Optional)</label>
                                                </div>
                                                <textarea placeholder="Note here..." name="rec-note" id="rec-note" ></textarea>

                                            </div>

                                            <div class="form-group" >
                                                <div class="label-icon">
                                                    <i class="fa-solid fa-calendar-days"></i>
                                                    <label class="required">Receiving Time</label>
                                                </div>
                                                <input type="datetime-local" id="rec-date" name="rec-date">
                                            </div>

                                        </div>
                                        <div class="column col-md-12">
                                            <div class="form-group ">
                                                <div class="radio-size">
                                                    <label class="label-icon required">Size</label>

                                                    <c:forEach var="packageSize" items="${packageSizes}" varStatus="status">
                                                        <label>
                                                            <input type="radio" id="select-size" name="select-size" value="${packageSize.package_size_id}" data-price="${packageSize.package_size_price}"
                                                                   <c:if test="${status.first}">checked</c:if> />
                                                            <span>${packageSize.package_size_value}</span>
                                                        </label>
                                                    </c:forEach>
                                                </div>
                                                <div id="size-description">(L) 25cm x (W) 20cm x (H) 20cm</div>

                                            </div>
                                            <div class="form-group">
                                                <label class="required">Weight</label>
                                                <input class="weight" id="weight" type="number" min="0" step="0.25" required placeholder="0" name="weight"> kg
                                                <div class="error" id="weight-error" hidden></div>

                                            </div>
                                        </div>

                                        <div class="column col-md-12">
                                            <div class="form-group">
                                                <div class="label-icon">
                                                    <i class="fa-solid fa-box-archive"></i>
                                                    <label class="required">Package Type</label>
                                                </div>
                                                <select class="dropdown" name="type-package" id="type-package">
                                                    <c:forEach var="packageType" items="${packageTypes}">
                                                        <option value="${packageType.package_type_id}" data-price="${packageType.package_type_price}">${packageType.package_type_value}</option>
                                                    </c:forEach>
                                                </select>

                                            </div>

                                            <div class="form-group">
                                                <div class="label-icon">
                                                    <i class="fa-solid fa-shield-halved"></i>
                                                    <label class="required">Warranty </label>
                                                </div>
                                                <select class="dropdown" name="warranty" id="select-warranty">
                                                    <c:forEach var="property" items="${warrantyOptions}">
                                                        <option value="${property.service_property_id}" data-price="${property.service_property_price}">
                                                            ${property.service_property_value}
                                                        </option>
                                                    </c:forEach>
                                                </select>
                                                <div id="warranty-description">Warranty 20% of package's value</div>

                                                <div class="trueValue-container">
                                                    <label class="required">Value of package</label>
                                                    <div class="input-container">
                                                        <input type="text" placeholder="5,000,000" required name="true-value" id="true-value"> VND
                                                        <div class="error" id="value-error" hidden></div>
                                                    </div>
                                                </div>

                                            </div>
                                        </div>
                                    </div>
                                </div>


                                <div class="container">
                                    <div class="image-upload col-md-10">
                                        <label for="input-field" id="drop-area">
                                            <input type="file" accept="image/*" id="input-field" hidden name="file" oninput="showImage(event)"> 
                                            <div id="img-view">
                                                <p>Add Picture (Optional)</p>
                                                <span>Drag and drop here to upload image</span>
                                                <i class="fa-regular fa-image"></i>
                                                <img id="pic" alt="photo" hidden />
                                            </div>
                                        </label>
                                    </div>

                                </div>

                                <div class="container">
                                    <div class="note col-md-10">
                                        <label class="text-area">
                                            <textarea id="text-note" placeholder="Note here..." name="note"></textarea>

                                        </label>
                                    </div>

                                </div>

                                <div class="container">
                                    <div class="payment col-md-10">
                                        <div class="form-group">
                                            <i class="fa-regular fa-credit-card"></i>
                                            <label class="label-icon required">Payment</label>
                                            <div class="column justify-content-start align-items-center mt-3">

                                                <c:forEach var="paymentOption" items="${paymentOptions}">
                                                    <div class="me-5">
                                                        <input type="radio" id="payment${paymentOption.payment_method_id}" name="payment" value="${paymentOption.payment_method_id}"/>
                                                        <span>${paymentOption.payment_method_value}</span>
                                                    </div>
                                                </c:forEach>

                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="container">
                                    <div class="total col-md-10">
                                        <div class="form-group">
                                            <strong>Total Money</strong>
                                        </div>
                                        <div>
                                            <label for="total" id="total-money" >
                                                <fmt:setLocale value = "vi_VN"/>
                                                <fmt:formatNumber value = "${totalMoney}" type = "currency"/>
                                            </label>
                                            <input type="hidden" id="totalValue" name="total-value" value="0">
                                            <input type="hidden" id="serviceFee" name="service-fee" value="0">


                                        </div>
                                    </div>
                                </div>

                                <div class="container">
                                    <div class="submit-container col-md-10">
                                        <input type="submit" class="btn btn-primary fw-bold px-3 py-2" id="submit" value="Confirm">
                                        <input type="datetime-local" id="created-date" name="created-date" hidden>

                                    </div>
                                </div>
                            </div>
                        </form>
                    </section>


                </main>
            </section>
        </section>

        <!-- CONTENT -->

        <%@include file="/include/footer.jsp" %>


        <!-- Scripts  Starts -->
        <script src="https://kit.fontawesome.com/d60694e6d9.js" crossorigin="anonymous"></script>
        <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
        <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
        <script src="js/create_order.js"></script>
        <script src="js/jquery-1.11.0.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
        <script 
            src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA1mLLNrhFhndWA119ZTywc38Fyw9cyas0&loading=async&libraries=places&callback=initAutocomplete" async defer>
        </script>
        <script src="https://pay.vnpay.vn/lib/vnpay/vnpay.min.js"></script>

    </body>

</html>