<%-- 
    Document   : Scr_Customer_Statistic
    Created on : Jun 19, 2024, 9:21:20 PM
    Author     : dangq
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <link rel="stylesheet" href="css/customer_statistic_style.css">
        <link rel="stylesheet" type="text/css" href="css/footer.css">
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined" rel="stylesheet">

        <title>DN EXPRESS</title>
    </head>

    <body>
        <section class="confiq">
            <%@include file="/include/sidebar_cus.jsp" %>
            <!-- SIDEBAR -->



            <!-- CONTENT -->
            <section id="content">
                <!-- NAVBAR -->
                <nav>
                    <i class='bx bx-menu'></i>
                    <a href="#" class="nav-link"><strong>Customer Statistic</strong></a>
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
                        <img src="images/img2.jpg">
                    </a>
                </nav>
                <!-- NAVBAR -->

                <!-- MAIN -->
                <main>
                    <div class="dashboard_content">       
                        <div class="date">
                            <input type="date">
                        </div>
                        <!-- --------- -->
                        <div class="insights">
                            <div class="orders">
                                <i class="fa-solid fa-chart-column"></i>
                                <div class="middle">
                                    <div class="lef">
                                        <h3>Total Order Finished</h3>
                                        <h1>${finishedOrders}/${totalOrders}</h1>
                                    </div>
                                    <div class="progress">
                                        <svg>
                                        <circle cx='38' cy='38' r='36'></circle>
                                        </svg>
                                        <div class="number">
                                            <p>0%</p>
                                        </div>
                                    </div>
                                </div>
                                <small class="text-muted">All time</small>
                            </div>
                            <!--=----------------- end of sales -->
                            <div class="ratings">
                                <i class="fa-solid fa-chart-area"></i>
                                <div class="middle">
                                    <div class="lef">
                                        <h3>Total Rating</h3>
                                        <h1>${rating}/10</h1>
                                    </div>
                                    <div class="progress">
                                        <svg>
                                        <circle cx='38' cy='38' r='36'></circle>
                                        </svg>
                                        <div class="number">
                                            <p>0%</p>
                                        </div>
                                    </div>
                                </div>
                                <small class="text-muted">All time</small>
                            </div>
                            <!--=----------------- end of expenses -->
                            <div class="incomes">
                                <i class="fa-solid fa-chart-line"></i>
                                <div class="middle">
                                    <div class="lef">
                                        <h3>Total Expenses</h3>
                                        <h1>$${totalAmount}</h1>
                                    </div>
                                    <!--                                    <div class="progress">
                                                                            <svg>
                                                                            <circle cx='38' cy='38' r='36'></circle>
                                                                            </svg>
                                                                            <div class="number">
                                                                                <p>51%</p>
                                                                            </div>
                                                                        </div>-->
                                </div>
                                <small class="text-muted">All time</small>
                            </div>
                            <!--=----------------- end of income -->
                        </div>
                        <!-- ======================End of insights============== -->
                        <!-- =======add chart ====== -->
                        <div class="graphBox">
                            <div class="box chart-container">
                                <div class="head_chart">
                                    <h2>Customer Statistic</h2>
                                    <select id="yearSelect">
                                        <c:forEach var="year" items="${orderYears}">
                                            <option value="${year}" <c:if test="${year == orderYears[orderYears.size() - 1]}">selected</c:if>> ${year}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <canvas id="myChart"></canvas>
                            </div>
                            <!--                        <div class="morderate_box">
                                                        <div class="head_chart">
                                                            <h2>Growing Statistic</h2>
                                                            <select>
                                                                <option value="">Order</option>
                                                                <option value="">Rating</option>
                                                                <option value="">Income</option>
                                                            </select>
                                                        </div>
                                                        <canvas id="myGrowingChart"></canvas>
                                                        <p style="margin-top: 2rem;">Monthly growth rate (%)</p>
                                                    </div>-->
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
        <script src="js/customer_statistic.js"></script>
    </body>
</html>