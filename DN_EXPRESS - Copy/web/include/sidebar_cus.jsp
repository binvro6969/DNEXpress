<%-- 
    Document   : sidebar_cus
    Created on : Jun 23, 2024, 4:50:11 PM
    Author     : MINHTRUNG
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Side Bar Customer</title>
        <link href="css/sidebar_cus.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <!-- SIDEBAR -->
    <section id="sidebar">
      <a href="#" class="brand">
        <i class='bx bxs-package'></i>
        <span class="text">DN Express</span>

      </a>
      <ul class="side-menu top">
        <li >
          <a href="Customer_CreateOrder_Servlet">
            <i class='bx bxs-package'></i>
            <span class="text">Create Order</span>
          </a>
        </li>
        <li>
          <a href="Customer_Statistic_Jsp_Servlet">
            <i class='bx bxs-group' ></i>
            <span class="text">Statistic</span>
          </a>
        </li>
<!--        <li>
          <a href="Customer_CheckStatus_Servlet">
            <i class='bx bxs-truck'></i>
            <span class="text">Check Order Status</span>
          </a>
        </li>-->
        <li>
          <a href="Customer_OrderHistory_Servlet">
            <i class='bx bxs-doughnut-chart' ></i>
            <span class="text">Order History</span>
          </a>
        </li>
<!--        <li>
          <a href="../CUS_Notification/Scr_ManageNotification_Notification.html">
            <i class='bx bxs-message-dots' ></i>
            <span class="text">Notification</span>
          </a>
        </li>-->
<!--        <li>
          <a href="../CUS_RatingDriver_notdone/#">
            <i class='bx bxs-group' ></i>
            <span class="text">Rating</span>
          </a>
        </li>       -->
      </ul>
      
      <ul class="side-menu" style="position: absolute; bottom: 0;">
        <li>
            <a href="Svl_Profile_Customer">
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
