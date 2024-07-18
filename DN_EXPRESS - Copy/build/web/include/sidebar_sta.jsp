<%-- 
    Document   : sidebar_sta
    Created on : Jun 23, 2024, 4:49:54 PM
    Author     : MINHTRUNG
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sidebar STA</title>
        <link href="css/sidebar_sta.css" rel="stylesheet" type="text/css"/>
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
                  <a href="sta_inspectPackage">
                    <i class='bx bxs-package'></i>
                    <span class="text">Inspect Package</span>
                  </a>
                </li>
                <li>
                  <a href="../STA_ReceiveIssueTicket/Receive_IssueTicket.html">
                    <i class='bx bxs-truck'></i>
                    <span class="text">Receive Ticket</span>
                  </a>
                </li>
              </ul>
              
              <ul class="side-menu" style="position: absolute; bottom: 0;">
                <li>
                    <a href="Svl_Profile_Staff">
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
