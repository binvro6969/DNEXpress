<%-- 
    Document   : sidebar_mng
    Created on : Jun 23, 2024, 4:41:25 PM
    Author     : MINHTRUNG
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/sidebar_mng.css" rel="stylesheet" type="text/css"/>
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
                <li >
                    <a href="mng_trackPackage">
                        <i class='bx bxs-package'></i>
                        <span class="text">Track Package</span>
                    </a>
                </li>
                <li>
                    <a href="mng_trackDriver">
                        <i class='bx bxs-truck'></i>
                        <span class="text">Track Driver</span>
                    </a>
                </li>
                <li>
                    <a href="mng_ManagePostOffice">
                        <i class='bx bxs-doughnut-chart'></i>
                        <span class="text">Manage Post Office</span>
                    </a>
                </li>
                <li>
                    <a href="Manager_listService_Servlet">
                        <i class='bx bxs-message-dots'></i>
                        <span class="text">Manage Service</span>
                    </a>
                </li>
                <li>
                    <a href="Manager_listStaffInternal_Servlet">
                        <i class='bx bxs-group'></i>
                        <span class="text">Manage Staff</span>
                    </a>
                </li>

                <li>
                    <a href="Manager_ListDriverStaff_Servlet" id="showDriver">
                        <i class='bx bxs-group'></i>
                        <span class="text">Manage Driver</span>
                    </a>
                </li>
                <jsp:include page="/include/sidebar_mng_2.jsp" />
                <li>
                    <a href="../MNG_Performance/view_performance.html">
                        <i class='bx bxs-group'></i>
                        <span class="text">Performance</span>
                    </a>
                </li>
            </ul>
            <ul class="side-menu" style="position: absolute; bottom: 0;">
                <li>
                    <a href="Svl_Profile_Manager">
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
