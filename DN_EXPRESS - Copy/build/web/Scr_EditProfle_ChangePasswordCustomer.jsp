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
                    <a href="#" class="nav-link"><strong>View Profile</strong></a>
                    <form action="#">
                        <div class="form-input">
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

                <main id="edit-profile" class="d-flex justify-content-center">

                    <section class="col-md-10 ">
                        <div class="row">

                            <div class="col-md-3">
                                <%@include file="/include/editprofile_leftbar_cus.jsp" %>
                            </div>

                            <div class="col-md-9">
                                <div class="form-container right-bar">
                                    <header class="py-3 text-center">
                                        <h1>Edit Profile</h1>
                                    </header>

                                    <div class="container">
                                        <div class="col-md-10">
                                            <form class="form-container" action="Svl_EditProfile_ChangePass" method="post">

                                                <div class="form-group">
                                                    <label>Enter old password</label>
                                                    <div class="input-container">
                                                        <input class="text" type="password" name="oldPass" required>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label>Enter new password</label>
                                                    <div class="input-container">
                                                        <input class="text" type="password" name="newPass" required>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label>Confirm new password</label>
                                                    <div class="input-container">
                                                        <input class="text" type="password" name="confirmPass" required>
                                                    </div>
                                                </div>
                                                <p class="error">${msg}</p>
                                                <div class="form-group">
                                                    <input type="submit" class="btn btn-primary btn-login fw-bold px-3 py-2" id="submit" value="Change Password">
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

        <!-- FOOTER -->
        <%@include file="/include/footer.jsp" %>
        <!-- FOOTER -->


        <!-- Scripts  Starts -->
        <script src="https://kit.fontawesome.com/d60694e6d9.js" crossorigin="anonymous"></script>
        <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
        <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
        <script src="js/edit_profile.js"></script>


    </body>

</html>
