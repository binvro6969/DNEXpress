
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
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
        <!-- My CSS -->
        <link href="css/Manage_Staff_3.css" rel="stylesheet" type="text/css"/>
        <link href="css/Manage_Service.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" type="text/css" href="footer.css">


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
                    <a href="listService" class="nav-link"><strong>Manage Services</strong></a>
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

                        <div class="popup active" >
                            <header>
                                <h2 class="modalTitle">Add Service</h2>

                            </header>

                            <div class="body"> 



                                <form action="Manager_addService_Servlet" id="myForm" method="post"  onsubmit="validateForm(event);">

                                    <div class="inputFieldContainer">

                                        <div class="form_control">
                                            <label for="serName">Service Type Name:</label>
                                            <input type="text" name="serName" id="serName" required>
                                            <h5 style="color:red">${requestScope.errorMessage}</h5>
                                        </div>

                                        <!-- Thông tin Service Property -->
                                        <div id="serviceProperties">
                                            <div class="nameField">
                                                <div class="form_control">
                                                    <label for="proName1">Service Property Name:</label>
                                                    <input type="text" name="proName" required>
                                                </div>
                                                <div class="form_control">
                                                    <label for="proPrice1">Service Property Price:</label>
                                                    <input type="text" name="proPrice"  oninput="clearErrorMessage();"  required>
                                                    <h5 id="error-message" class="error" style="color:red"></h5>
                                                </div>
                                            </div>
                                        </div>

                                        <button class="btn btn-primary" type="button" onclick="addProperty()">Add Another Property</button>



                                    </div>


                                </form>
                            </div>



                            <footer class="popupFooter">
                                <button class="btn btn-primary" form="myForm" class="submitBtn">Submit</button>
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

        <script src="js/Manage_Service.js" type="text/javascript"></script>

        <script>
                                        let propertyCount = 1;

                                        function addProperty() {
                                            propertyCount++;
                                            const div = document.createElement('div');
                                            div.className = 'property';
                                            div.innerHTML = `
                 <div class="nameField">
                    <div class="form_control">
                        <label for="proName${propertyCount}">Service Property Name:</label>
                        <input type="text" name="proName" required> 
                    </div>
                    <div class="form_control">
                        <label for="proPrice${propertyCount}">Service Property Price:</label>
                        <input type="text" name="proPrice" required>
                    </div>
                </div>
                `;
                                            document.getElementById('serviceProperties').appendChild(div);
                                        }
        </script>

        <script>
            function validateForm(event) {
                let isValid = true;
                let errorMessage = "";



                let propertyPrices = document.getElementsByName("proPrice");
                for (let i = 0; i < propertyPrices.length; i++) {
                    let price = propertyPrices[i].value;
                    if (isNaN(price) || price.trim() === "") {
                        isValid = false;
                        errorMessage = "Invalid price for property " + (i + 1) + ": " + price;
                        document.getElementById("error-message").innerText = errorMessage;
                        propertyPrices[i].focus();
                        propertyPrices[i].value = "";
                        break;
                    }
                }

                if (!isValid) {
                    event.preventDefault();
                }
            }

            function clearErrorMessage() {
                document.getElementById("error-message").innerText = "";
            }
        </script>
    </body>

</html>