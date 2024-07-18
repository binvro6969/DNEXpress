<%-- 
    Document   : Scr_Check_Order
    Created on : Jul 5, 2024, 5:25:29 PM
    Author     : dangq
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
        <link rel="stylesheet" href="css/check_order_status_style.css">
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

                <main id="check-order-status" class="d-flex justify-content-center">

                    <section class="col-md-10 ">
                        <form action="Customer_CancelOrder_Servlet" method="post">
                            <header class="text-center py-3">
                                <h1>Order's Status</h1>
                            </header>
                            <div class="form-content">
                                <div class="container">
                                    <div class="col-md-11">

                                        <c:set var="overview" value="" />
                                        <c:if test="${statusList.size() == 0}">
                                            <c:set var="overview" value="Order Created" />
                                            <c:set var="cancel" value="true" />
                                        </c:if>

                                        <c:forEach var="status" items="${statusList}" varStatus="loop">
                                            <c:choose>
                                                <c:when test="${status.status == 'Picking up'}">
                                                    <c:set var="overview" value="Picking Up" />
                                                </c:when>
                                                <c:when test="${status.status == 'In transit' || status.status == 'In transit-2' || 
                                                                status.status == 'At warehouse' || status.status == 'At warehouse-2' || 
                                                                status.status == 'Delivering'}">
                                                    <c:set var="overview" value="Delivering Package" />
                                                </c:when>
                                                <c:when test="${status.status == 'Delivered'}">
                                                    <c:set var="overview" value="Package Received" />
                                                </c:when>
                                            </c:choose>
                                        </c:forEach>

                                        <div class="order-status">
                                            <span>${overview}</span>
                                        </div>


                                        <div class="shipment-info">
                                            <div class="column d-flex">

                                                <div class="form-group col-md-7">
                                                    <div class="title d-flex">
                                                        <i class="fa-solid fa-truck-fast"></i>
                                                        <strong>Shipping Information</strong>
                                                    </div>

                                                    <div class="content mt-5">

                                                        <c:set var="isLastItem" value="${statusList.size() == 0}" />
                                                        <div class="row ${isLastItem ? 'group-final' : ''}">
                                                            <div class="column d-flex">
                                                                <div class="datetime">
                                                                    ${createdDate}<br/>
                                                                    ${createdTime}
                                                                </div>
                                                                <div class="status-line col-md-2">
                                                                    <div class="eclipse"></div>
                                                                    <c:if test="${!isLastItem}">
                                                                        <div class="line"></div>
                                                                    </c:if>
                                                                </div>
                                                                <div class="status col-md-7">Order Created</div>
                                                            </div>
                                                        </div>

                                                        <c:forEach var="status" items="${statusList}" varStatus="loop">
                                                            <c:set var="isLastItem" value="${loop.last}" />

                                                            <div class="row ${isLastItem ? 'group-final' : ''}">
                                                                <div class="column d-flex">
                                                                    <div class="datetime">
                                                                        ${status.startDate}<br/>
                                                                        ${status.startTime}
                                                                    </div>
                                                                    <div class="status-line col-md-2">
                                                                        <div class="eclipse"></div>
                                                                        <c:if test="${!isLastItem && status.process == 'Done' }">
                                                                            <div class="line"></div>
                                                                        </c:if>
                                                                    </div>

                                                                    <c:set var="description" value="" />

                                                                    <c:choose>

                                                                        <c:when test="${status.status == 'Picking up'}">
                                                                            <c:set var="description" value="Driver is picking up"/>
                                                                        </c:when>
                                                                        <c:when test="${status.status == 'In Transit' || status.status == 'In Transit-2'}">
                                                                            <c:set var="description" value="Delivering to ${status.location} post office"/>
                                                                        </c:when>

                                                                        <c:when test="${status.status == 'At warehouse' || status.status == 'At warehouse-2'}">
                                                                            <c:set var="description" value="Delivered to ${status.location} post office"/>
                                                                        </c:when>

                                                                        <c:when test="${status.status == 'Delivering'}">
                                                                            <c:set var="description" value="Delivering to recipient"/>
                                                                        </c:when>
                                                                        <c:when test="${status.status == 'Delivered'}">
                                                                            <c:set var="description" value="Delivered to recipient"/>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <c:set var="description" value="Failed"/>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                    <div class="status col-md-7">${description}</div>

                                                                </div>
                                                            </div>
                                                        </c:forEach>

                                                    </div>
                                                </div>

                                                <div class="form-group col-md-5">
                                                    <div class="column order-details">
                                                        <div class="row">
                                                            <div class="col-md-4">
                                                                <div class="left-text"><strong> Order ID</strong></div>
                                                            </div>
                                                            <div class="col-md-8">
                                                                <div class="right-text">${order.order_id}</div>
                                                            </div>
                                                        </div>

                                                    </div>

                                                    <div class="column order-details">
                                                        <div class="row">
                                                            <div class="col-md-4">
                                                                <div class="left-text"><strong>Sender</strong></div>
                                                            </div>
                                                            <div class="col-md-8">
                                                                <div class="right-text"> ${order.customer.account.firstName} ${order.customer.account.lastName}</div>
                                                            </div>

                                                        </div>
                                                    </div>


                                                    <div class="column order-details">
                                                        <div class="row">
                                                            <div class="col-md-4">
                                                                <div class="left-text"></div>
                                                            </div>
                                                            <div class="col-md-8">
                                                                <div class="right-text">${order.customer.account.phone_numb}</div>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="column order-details">
                                                        <div class="row">
                                                            <div class="col-md-4">
                                                                <div class="left-text"></div>
                                                            </div>
                                                            <div class="col-md-8">
                                                                <div class="right-text">${order.pick_up_apartment_number}, 
                                                                    ${order.pick_up_street_name},
                                                                    ${order.pick_up_district},
                                                                    ${order.pick_up_ward},
                                                                    ${order.pick_up_city}
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="column order-details">
                                                        <div class="row">
                                                            <div class="col-md-4">
                                                                <div class="left-text"><strong>Receiver</strong></div>
                                                            </div>
                                                            <div class="col-md-8">
                                                                <div class="right-text"> ${order.final_receiver_name}</div>
                                                            </div>

                                                        </div>
                                                    </div>


                                                    <div class="column order-details">
                                                        <div class="row">
                                                            <div class="col-md-4">
                                                                <div class="left-text"></div>
                                                            </div>
                                                            <div class="col-md-8">
                                                                <div class="right-text">${order.final_receiver_phone}</div>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="column order-details">
                                                        <div class="row">
                                                            <div class="col-md-4">
                                                                <div class="left-text"></div>
                                                            </div>
                                                            <div class="col-md-8">
                                                                <div class="right-text">${order.final_apartment_number}, 
                                                                    ${order.final_street_name},
                                                                    ${order.final_district},
                                                                    ${order.final_ward},
                                                                    ${order.final_city}
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="google-map mt-5">
                                                        <img class="img-fluid package-img" id="pic" alt="No image here" src="${pack.package_img}"/>                                                    
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="cancel-btn mt-5">

                                            <c:choose>
                                                <c:when test="${cancel}">
                                                    <input type="hidden" name="orderId" value="${order.order_id}" >
                                                    <button type="submit" class="btn btn-primary fw-bold px-3 py-2">Cancel Order</button>
                                                </c:when>
                                                <c:otherwise>
                                                    <button type="submit" class="btn btn-primary fw-bold px-3 py-2" disabled>Cancel Order</button>
                                                </c:otherwise>
                                            </c:choose>
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
        <script src="js/check_order_status.js"></script>


    </body>

</html>