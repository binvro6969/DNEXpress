<%-- 
    Document   : Scr_EditProfile_EditProfileCustomer
    Created on : Jun 15, 2024, 10:41:05 PM
    Author     : DINH
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<!--
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Html.html to edit this template
-->
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
        <link rel="stylesheet" href="css/edit_profile_style.css">
        <link href="css/driver_wallet.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" type="text/css" href="css/footer.css">

        <title>DN EXPRESS</title>
    </head>

    <body>

        <section class="confiq">
            <!-- SIDEBAR -->
            <%@include file="/include/sidebar_drv.jsp" %>
            <!-- SIDEBAR -->



            <!-- CONTENT -->
            <section id="content">
                <!-- NAVBAR -->
                <nav>
                    <i class='bx bx-menu' ></i>
                    <a href="#" class="nav-link" ><strong>View Profile</strong></a>
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

                <main id="edit-profile" class="d-flex justify-content-center">

                    <section class="col-md-10 ">
                        <div class="row">

                            <div class="col-md-3">
                                <div class="form-container left-bar">
                                    <div class="avatar"> 
                                        <label for="image-upload">
                                            <img src="${info.getAccount().getAvatar()}" alt="avatar" class=" img-fluid">
                                        </label>
                                        <!--<input type="file" accept="image/*" id="image-upload" hidden >-->
                                    </div>
                                    <h4>${info.getAccount().getFirstName()} ${info.getAccount().getLastName()}</h4>
                                    <span>@${info.getAccount().getPhone_numb()}</span>
                                    <hr>
                                    <ul>
                                        <li>
                                            <form action="Svl_Profile_Driver" method="get">
                                                <input class="link-button" type="submit" value="Edit Profile" />
                                            </form>
                                        </li>
                                        <!--<a href="Scr_Profile_CustomerProfile.jsp">Edit Profile</a></li>-->
                                        <li>
                                            <form action="Svl_EditProfile_ChangePass" method="get">
                                                <input class="link-button" type="submit" value="Change Password" />
                                            </form>
                                        </li>
                                        <li>
                                            <form action="Driver_ViewDriverWallet_Servlet" method="get">
                                                <input class="link-button" type="submit" value="Manage Wallet" />
                                            </form>
                                        </li>
                                    </ul>

                                </div>
                            </div>

                            <div class="col-md-9">
                                <div class="form-container right-bar">
                                    <header class="py-3 text-center">
                                        <h1>Your Profile</h1>
                                    </header>

                                    <div class="container">
                                        <div class="col-md-10">

                                            <div class="form-container">
                                                <div class="money-content-container">
                                                    <div class="totalMoney">
                                                        <h4>Total Money:</h4>
                                                        <h3>đ <%= session.getAttribute("balance")%></h3>
                                                    </div>
                                                    <div class="Driver-wallet-content">
                                                        <div class="row Driver-wallet-content-1 ">
                                                            <form action="Driver_DriverWallet_DepositMoney_Servlet" method="get">
                                                                <button type="submit" class="wallet-option col-md-3">
                                                                    <i class="Driver-wallet-icon fa-solid fa-sack-dollar"></i>
                                                                    <span>Deposit Money</span>
                                                                </button>
                                                            </form>

                                                            <!--                                                            <a href="#" class="wallet-option col-md-3">
                                                                                                                            <i class="Driver-wallet-icon fa-solid fa-qrcode"></i>
                                                                                                                            <span>Scan QR</span>
                                                                                                                        </a>
                                                                                                                        <a href="#" class="wallet-option col-md-3">
                                                                                                                            <i class="Driver-wallet-icon fa-solid fa-qrcode"></i>
                                                                                                                            <span>QR Payment</span>
                                                                                                                        </a>-->
                                                            <!--</div>-->
                                                            <!--<div class="row Driver-wallet-content-2">-->
                                                            <!--                                                            <a href="#" class="wallet-option col-md-3">
                                                                                                                            <i class="Driver-wallet-icon fa-solid fa-money-bill-transfer"></i>
                                                                                                                            <span>Transfer Money</span>
                                                                                                                        </a>-->
<!--                                                            <a href="DRV_manageDriverWallet_WithdrawMoney.jsp" class="wallet-option col-md-3">
                                                                <i class="Driver-wallet-icon fa-solid fa-building-columns"></i>
                                                                <span>Withdraw Money</span>
                                                            </a>-->
                                                            <!--                                                            <a href="#" class="wallet-option col-md-3">
                                                                                                                            <i class="Driver-wallet-icon fa-solid fa-wallet"></i>
                                                                                                                            <span>Transaction History</span>
                                                                                                                        </a>-->
                                                        </div>

                                                    </div>
                                                </div>

                                                </form>  


                                            </div>
                                        </div>

                                    </div>
                                </div>
                            </div>
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
        <script src="js/edit_profile.js"></script>


    </body>

</html>
