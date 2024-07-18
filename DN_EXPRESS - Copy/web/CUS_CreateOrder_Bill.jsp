<%-- 
    Document   : Scr_CreateOrder_Bill
    Created on : Jul 4, 2024, 12:16:44 AM
    Author     : dangq
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <link rel="stylesheet" href="css/create_order_bill_style.css">
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
                    <i class='bx bx-menu'></i>
                    <a href="#" class="nav-link"><strong>Create Order</strong></a>
                    <form action="#">
                        <div class="form-input" hidden>
                            <input type="search" placeholder="Enter driver Id">
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
                <!-- Hero Section Starts -->

                <main id="bill" class="d-flex justify-content-center">

                    <section class="col-md-10 ">
                        <form action="Customer_CheckStatus_Servlet" method="get">
                            <header class="text-center py-3">
                                <h1>Order Created Successfully</h1>
                            </header>
                            <div class="form-content">
                                <div class="container">
                                    <div class="col-md-11">

                                        <div class="shipment-info">
                                            <div class="column d-flex">
                                                <div class="form-group">

                                                    <div class="column col-md-12">
                                                        <div class="row">
                                                            <div class="col-md-4">
                                                                <div class="left-text ">Order ID:</div>
                                                            </div>
                                                            <div class="col-md-6">
                                                                <div class="right-text">${orderId}</h>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="column order-details col-md-12">
                                                        <div class="row">
                                                            <div class="col-md-4">
                                                                <div class="left-text ">Order Type:</div>
                                                            </div>
                                                            <div class="col-md-6">
                                                                <div class="right-text">
                                                                    <c:choose>
                                                                        <c:when test="${inProvince == true}">
                                                                            In Province Delivery
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            Interprovincial Delivery
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="column order-details col-md-12">
                                                        <div class="row">
                                                            <div class="col-md-4">
                                                                <div class="left-text ">Date Created:</div>
                                                            </div>
                                                            <div class="col-md-6">
                                                                <div class="right-text">${createdDate}</h>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group d-flex justify-content-center">
                                                    <img src="${filePath}" alt="No image" class="img-fluid package-img">
                                                </div>
                                            </div>



                                            <div class="line"></div>
                                            <div class="column d-flex">

                                                <div class="form-group">

                                                    <div class="title d-flex">
                                                        <strong>Customer Information</strong>
                                                    </div>

                                                    <div class="column order-details col-md-12">
                                                        <div class="row">
                                                            <div class="col-md-4">
                                                                <strong>Sender</strong>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-md-4">Name:</div>
                                                            <div class="col-md-8">
                                                                <div class="right-text">${sendName}</h></div>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-md-4">Phone Number:</div>
                                                            <div class="col-md-8">
                                                                <div class="right-text">${sendPhone}</h></div>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-md-4">Address:</div>
                                                            <div class="col-md-8">
                                                                <div class="right-text">${sendAdd}</h></div>
                                                            </div>
                                                        </div>

                                                    </div>
                                                    <div class="column order-details col-md-12">
                                                        <div class="row">
                                                            <div class="col-md-4">
                                                                <strong>Receiver</strong>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-md-4">Name:</div>
                                                            <div class="col-md-8">
                                                                <div class="right-text">${recName}</h></div>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-md-4">Phone Number:</div>
                                                            <div class="col-md-8">
                                                                <div class="right-text">${recPhone}</h></div>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-md-4">Address:</div>
                                                            <div class="col-md-8">
                                                                <div class="right-text">${recAdd}</h></div>
                                                            </div>
                                                        </div>

                                                    </div>

                                                </div>

                                                <div class="form-group">

                                                    <div class="title d-flex offset-md-2 right-title">
                                                        <strong>Package Information</strong>
                                                    </div>


                                                    <div class="container-fluid">
                                                        <div class="row">
                                                            <div class="col-md-12 offset-md-2 "> <!-- Adjust column size and offset as needed -->
                                                                <div class="column order-details col-md-12">
                                                                    <div class="row">
                                                                        <div class="col-md-5">
                                                                            <div class="left-text">Note:</div>
                                                                        </div>
                                                                        <div class="col-md-7">
                                                                            <div class="right-text">${note}</h>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div class="column order-details col-md-12">
                                                                    <div class="row">
                                                                        <div class="col-md-5">
                                                                            <div class="left-text">Size:</div>
                                                                        </div>
                                                                        <div class="col-md-7">
                                                                            <c:forEach var="packageSize" items="${packageSizes}" varStatus="status">
                                                                                <c:if test="${sizeId == packageSize.package_size_id}">
                                                                                    <div class="right-text">${packageSize.package_size_value}</div>
                                                                                </c:if>
                                                                            </c:forEach>

                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div class="column order-details col-md-12">
                                                                    <div class="row">
                                                                        <div class="col-md-5">
                                                                            <div class="left-text">Weight:</div>
                                                                        </div>
                                                                        <div class="col-md-7">
                                                                            <div class="right-text">${weight} kg</div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div class="column order-details col-md-12">
                                                                    <div class="row">
                                                                        <div class="col-md-5">
                                                                            <div class="left-text">Delivering Type:</div>
                                                                        </div>
                                                                        <div class="col-md-7">
                                                                            <c:forEach var="deliveryType" items="${deliveryTypes}" varStatus="status">
                                                                                <c:if test="${typeShipmentId == deliveryType.delivery_type_id}">
                                                                                    <div class="right-text">${deliveryType.delivery_type_value}</div>
                                                                                </c:if>
                                                                            </c:forEach>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div class="column order-details col-md-12">
                                                                    <div class="row">
                                                                        <div class="col-md-5">
                                                                            <div class="left-text">Package Type:</div>
                                                                        </div>
                                                                        <div class="col-md-7">
                                                                            <div class="right-text">Food</div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div class="column order-details col-md-12">
                                                                    <div class="row">
                                                                        <div class="col-md-5">
                                                                            <div class="left-text">Warranty:</div>
                                                                        </div>
                                                                        <div class="col-md-7">
                                                                            <c:forEach var="property" items="${warrantyOptions}">
                                                                                <c:if test="${warrantyValue == property.service_property_id}">
                                                                                    <div class="right-text">${property.service_property_value}</div>
                                                                                </c:if>
                                                                            </c:forEach>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="line"></div>



                                            <div class="column d-flex align-items-center">
                                                <div class="form-group">
                                                    <div>
                                                        <input type="hidden" name="orderId" value="${orderId}" >
                                                        <button type="submit" class="btn btn-primary fw-bold px-3 py-2">Check Status</button>
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <div class="d-flex align-items-center justify-content-end">
                                                        <strong class="col-md-4">Total Money: </strong>
                                                        <label for="total" id="total-money" >
                                                            <fmt:setLocale value = "vi_VN"/>
                                                            <fmt:formatNumber value = "${totalMoney}" type = "currency"/>
                                                        </label>
                                                    </div>
                                                </div>

                                            </div>
                                        </div>
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
        <script src="js/create_order_bill.js"></script>


    </body>

</html>