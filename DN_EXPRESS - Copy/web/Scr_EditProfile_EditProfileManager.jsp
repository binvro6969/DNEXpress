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
            <%@include file="/include/sidebar_mng.jsp" %>
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
                                <%@include file="/include/editprofile_leftbar_mng.jsp" %>
                            </div>

                            <div class="col-md-9">
                                <div class="form-container right-bar">
                                    <header class="py-3 text-center">
                                        <h1>Edit Profile</h1>
                                    </header>

                                    <div class="container">
                                        <div class="col-md-10">
                                            <form class="form-container" action="Svl_EditProfile_Manager" method="post">

                                                <div class="form-group">
                                                    <label>First Name</label>
                                                    <div class="input-container">
                                                        <input class="text" type="text" name="fname" value="${info.getFirstName()}" >
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label>Last Name</label>
                                                    <div class="input-container">
                                                        <input class="text" type="text" name="lname" value="${info.getLastName()}">

                                                    </div>
                                                </div>

                                                <div class="form-group gender">
                                                    <label class="label-icon">Gender</label>
                                                    <div class="input-container">
                                                        <input type="radio" id="male" name="select-gen" value="Male" ${info.getGender() == 'Male' ? 'checked' : ''} >
                                                        <label for="male">Male</label>
                                                        <input type="radio" id="female" name="select-gen" value="Female" ${info.getGender() == 'Female' ? 'checked' : ''} >
                                                        <label for="female">Female</label>
                                                        <input type="radio" id="other" name="select-gen" value="Other" ${info.getGender() == 'Other' ? 'checked' : ''} >
                                                        <label for="other">Other</label>
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <label>Date of Birth</label>
                                                    <div class="input-container">
                                                        <input type="date" name="dob" value="<fmt:formatDate pattern='yyyy-MM-dd' value='${info.getDob()}'/>">
                                                    </div>

                                                </div>

                                                <div class="form-group">
                                                    <label>Email</label>
                                                    <div class="input-container">
                                                        <input type="email" name="email" value="${info.getEmail()}">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label>Phone Number</label>
                                                    <div class="input-container">
                                                        <input type="tel" id="phone" name="phone" value="${info.getPhone_numb()}" pattern="0[0-9]{9,10}">
                                                    </div>
                                                </div>


                                                <div class="form-group">
                                                    <label>CMND/ CCCD</label>
                                                    <div class="input-container">
                                                        <input type="text" id="idNumber" name="idNumber" value="${info.getCccd_numb()}" pattern="[0-9]{9}|[0-9]{10}|[0-9]{11}" title="ID Number must be 9, 10, or 11 digits long">

                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <input type="submit" class="btn btn-primary btn-login fw-bold px-3 py-2" id="submit" value="Update Profile">
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
