

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
        <link href="css/Manage_Staff_3.css" rel="stylesheet" type="text/css"/>
        <link href="css/footer.css" rel="stylesheet" type="text/css"/>
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
                    <a href="#" class="nav-link"><strong>Manage Staff</strong></a>
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
                        <img src="img/img2.jpg">
                    </a>
                </nav>
                <!-- NAVBAR -->

                <!-- MAIN -->
                <main class="table" id="customers_table">


                    <!--Popup Form-->

                    <div class="dark_bg active">

                        <div class="popup active">
                            <header>
                                <h2 class="modalTitle">Fill the Form</h2>
                            </header>

                            <div class="body">
                                <form id="myForm" action="Manager_createStaffInternal_Servlet" method="post"  enctype="multipart/form-data">
                                    <div class="imgholder">
                                        <label for="uploadimg" class="upload">
                                            <input type="file" name="image" id="uploadimg" class="picture">
                                            <i class="fa-solid fa-plus"></i>

                                        </label>
                                        <img src="./img/pic1.png" alt="" width="150" height="150" class="img" id="previewImg">
                                        <h5 style="color:red">${requestScope.errorMessage}</h5>

                                    </div>

                                    <div class="inputFieldContainer">

                                        <div class="nameField">
                                            <div class="form_control">
                                                <label for="firstName">First Name:</label>
                                                <input type="text" name="firstName" id="fName" pattern="[A-Za-z]+" title="First name must contain only letters." required>
                                                <div id="firstnameError" class="error-message"></div>
                                            </div>
                                            <div class="form_control">
                                                <label for="lastName">Last Name:</label>
                                                <input type="text" name="lastName" id="lName" pattern="[A-Za-z]+" title="Last name must contain only letters." required>
                                                <div id="lastnameError" class="error-message"></div>
                                            </div>
                                        </div>
                                        <div class="identify">
                                            <div class="form_control">
                                                <label for="gender">Gender:</label>
                                                <input type="text" name="gender" id="gender" required>
                                            </div>

                                            <div class="form_control">
                                                <label for="cccd_numb">Citizen Identity:</label>
                                                <input type="text" name="cccd_numb" id="cid" required>
                                            </div>
                                        </div>
                                        <div class="location">

                                            <div class="form_control">
                                                <label for="post_OfficeID">Post Office ID: </label>
                                                <input name="post_OfficeID" type="text" id="post_OfficeID" pattern="[1-9]+" title="Post Office ID must contain only digits." required>
                                                <h5 style="color:red">${requestScope.errorMessage}</h5>
                                            </div>
                                        </div>

                                        <div class="form_control">
                                            <label for="dob">Date of Birth:</label>
                                            <input type="date" name="dob" id="dob" required>
                                        </div>

                                        <div class="form_control">
                                            <label for="email">Email:</label>
                                            <input type="email" name="email" id="email" required>
                                        </div>
                                        <div class="form_control">
                                            <label for="phone_numb">Phone:</label>
                                            <input type="tel" name="phone_numb" id="phone" pattern="(0|84)\d{9}" title="Phone number must start with 0 or 84 and contain 10 digits." required>
                                            <div id="phoneError" class="error-message"></div>
                                        </div>
                                    </div>

                                </form>
                            </div>

                            <footer class="popupFooter">
                                <button form="myForm" class="submitBtn">Submit</button>
                            </footer>
                        </div>
                    </div>

                    <div class="overlay" id="overlay"></div>

                    <div class="popup_del" id="deletePopup">
                        <div class="popup_del-content">
                            <h2><strong>Confirmation</strong></h2>
                            <p>Are you sure to delete?</p>
                            <button id="cancelDeleteBtn">Cancel</button>
                            <button id="confirmDeleteBtn">Delete</button>
                        </div>
                    </div>
                    <!--Popup Form-->

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
        <script src="js/Manage_Staff.js" type="text/javascript"></script>
        <script>
            document.getElementById('uploadimg').addEventListener('change', function (event) {
                const file = event.target.files[0];
                if (file) {
                    const reader = new FileReader();
                    reader.onload = function (e) {
                        document.getElementById('previewImg').src = e.target.result;
                    };
                    reader.readAsDataURL(file);
                }
            });
        </script>


        <script>
            document.getElementById('myForm').addEventListener('submit', function (event) {
                let valid = true;

                // Validate first name
                const firstname = document.getElementById('fName').value;
                const firstnameError = document.getElementById('firstnameError');
                const namePattern = /^[A-Za-z]+$/;
                if (!firstname || !namePattern.test(firstname)) {
                    firstnameError.textContent = 'First name is required and must contain only letters.';
                    valid = false;
                } else {
                    firstnameError.textContent = '';
                }

                // Validate last name
                const lastname = document.getElementById('lName').value;
                const lastnameError = document.getElementById('lastnameError');
                if (!lastname || !namePattern.test(lastname)) {
                    lastnameError.textContent = 'Last name is required and must contain only letters.';
                    valid = false;
                } else {
                    lastnameError.textContent = '';
                }

                // Validate phone number
                const phoneNumber = document.getElementById('phone').value;
                const phoneError = document.getElementById('phoneError');
                const phonePattern = /^0\d{9}$/;
                if (!phonePattern.test(phoneNumber)) {
                    phoneError.textContent = 'Phone number must be 10 digits starting with 0.';
                    valid = false;
                } else {
                    phoneError.textContent = '';
                }

                if (!valid) {
                    event.preventDefault();
                }
            });
        </script>

    </body>

</html>
