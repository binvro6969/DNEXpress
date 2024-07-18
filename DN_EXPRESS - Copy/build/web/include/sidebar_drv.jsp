<%-- 
    Document   : sidebar_drv
    Created on : Jun 23, 2024, 4:50:18 PM
    Author     : MINHTRUNG
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sidebar Driver</title>
        <link href="css/sidebar_drv.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <!-- SIDEBAR -->
        <section id="sidebar">
            <a href="#" class="brand">
                <i class='bx bxs-package'></i>
                <span class="text">DN Express</span>
                <!-- <img src="img/logopage1.png"> -->

            </a>
            <ul class="side-menu top">             
<!--                <li>
                    <a href="Driver_ViewDriverWallet_Servlet">
                        <i class='bx bxs-truck'></i>
                        <span class="text">Manage Wallet</span>
                    </a>
                </li>-->
                <li>
                    <a href="Driver_Statistic_Jsp_Servlet">
                        <i class='bx bxs-doughnut-chart' ></i>
                        <span class="text">Driver Statistic</span>
                    </a>
                </li>
                <li>
                    <a href="Driver_ReceiveOrder_ListOrder_Servlet">
                        <i class='bx bxs-doughnut-chart' ></i>
                        <span class="text">List Order</span>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <i class='bx bxs-doughnut-chart' ></i>
                        <span class="text">Report Issue</span>
                    </a>
                </li>
            </ul>

            <ul class="side-menu" style="position: absolute; bottom: 0;">
                <li>
                    <a href="Svl_Profile_Driver">
                        <i class='bx bxs-cog'></i>
                        <span class="text">Settings</span>
                    </a>
                </li>
                <li>
                    <a href="Authentication_SignOut_Servlet" class="logout">
                        <i class='bx bxs-log-out-circle'></i>
                        <span class="text">Logout</span>
                    </a>
                </li>
            </ul>

        </section>
        <!-- SIDEBAR -->
        <script src="js/sidebar.js" type="text/javascript"></script>
    </body>
</html>
