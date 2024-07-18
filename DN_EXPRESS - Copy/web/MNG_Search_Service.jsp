<%-- 
    Document   : Manage_Service
    Created on : 28-Jun-2024, 10:37:11
    Author     : DuongPhan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- Boxicons -->
        <link href='https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css' rel='stylesheet'>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css"
              integrity="sha512-MV7K8+y+gLIBoVD59lQIYicR65iaqukzvf/nwasF0nqhPay5w/9lJmVM2hMDcnK1OnMGCdVK+iQrJ7lzPJQd1w=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <!-- My CSS -->
        <link href="css/Manage_Service.css" rel="stylesheet" type="text/css"/>

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
                    <a href="listService" class="nav-link"><strong>Manage Services</strong></a>
                    <form action="#">
                        <div class="form-input">
                            <input type="text" id="searchInput" placeholder="Search" onkeypress="handleKeyPress(event)">
                            <button type="button" class="search-btn" onclick="search()">Search</button>
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


                    <section class="table__body">
                        <table>
                            <header>
                                <div class="filterEntries">
                                    <div class="addMemberBtn">
                                        <button onclick="window.location.href = 'Add_Service_Details.jsp'">New Service</button>
                                    </div>
                                    <form action="searchService" method="post">
                                        <div class="filter" >
                                            <label for="searchInput" style="color: #083c3e;"><strong>Search:</strong></label>
                                            <input name="searchCriteria" type="text" id="searchInput" placeholder="Enter Here" onkeypress="handleKeyPress(event)">
                                            <input class="searchButton" type="submit" name="btnGo" value="Search">
                                        </div>
                                    </form>

                                </div>

                            </header>
                            <thead>

                                <tr>
                                    <th> STT <span class="icon-arrow"></span></th>
                                    <th> Service <span class="icon-arrow"></span></th>
                                    <th> Service ID <span class="icon-arrow"></span></th>
                                    <th> Action <span class="icon-arrow"></span></th>
                                </tr>
                            </thead>
                            <tbody>
                                <!-- <tr><td class="empty" colspan="11" align="center">No data available in table</td></tr> -->
                                <c:forEach var="search" items="${searchResult}" varStatus="loop">
                                    <tr>
                                        <td>${loop.index + 1}</td>
                                        <td>${search.service_type_value}</td>
                                        <td>${search.service_type_id}</td>
                                        <td>
                                            <button class="action-btn view-btn"  
                                                    onclick="window.location.href = 'getServiceDetails?service_type_id=${search.service_type_id}'">
                                                <i class="fa-regular fa-eye"></i></button>
                                            <button class="action-btn edit-btn" 
                                                    onclick="window.location.href = 'editServiceDetails?service_type_id=${search.service_type_id}'">
                                                <i class="fa-regular fa-pen-to-square"></i>
                                            </button>
                                            <button class="action-btn delete-btn"
                                                    onclick="showDeletePopup(${search.service_type_id})">
                                                <i class="fa-regular fa-trash-can"></i></button>
                                        </td>
                                    </tr>
                                </c:forEach>

                            </tbody>
                        </table>
                    </section>
                </main>
                <!-- MAIN -->
            </section>
        </section>
        <!-- CONTENT -->

        <!-- FOOTER -->
        <%@include file="/include/footer.jsp" %>
        <!-- FOOTER -->

        <!--Popup Form-->

        <!--    <div class="dark_bg">
        
                <div class="popup">
                    <header>
                        <h2 class="modalTitle">Fill the Form</h2>
                        <button class="closeBtn">&times;</button>
                    </header>
        
                    <div class="body">
                        <form action="#" id="myForm">
        
                            <div class="inputFieldContainer">
        
                                <div class="nameField">
                                    <div class="form_control">
                                        <label for="serName">Service Name:</label>
                                        <input type="text" name="" id="serName" required>
                                    </div>
        
                                    <div class="form_control">
                                        <label for="serPrice">Service price</label>
                                        <input type="text" name="" id="serPrice" required>
                                    </div>
                                </div>
        
                                <div class="form_control">
                                    <label for="description">Description</label>
                                    <textarea id="description" rows="10" cols="50" required></textarea>
                                </div>
                            </div>
                            
                            
                        </form>
                    </div>
        
        
        
                    <footer class="popupFooter">
                        <button form="myForm" class="submitBtn">Submit</button>
                    </footer>
                </div>
            </div>-->

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


        <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
        <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
        <script src="js/Manage_Service.js" type="text/javascript"></script>



        <script>
                                                        function showDeletePopup(accountId) {
                                                            const deletePopup = document.getElementById('deletePopup');
                                                            const confirmDeleteBtn = document.getElementById('confirmDeleteBtn');

                                                            deletePopup.style.display = 'block';

                                                            confirmDeleteBtn.onclick = function () {
                                                                window.location.href = 'deleteService?service_type_id=' + accountId;
                                                            };

                                                            document.getElementById('cancelDeleteBtn').onclick = function () {
                                                                deletePopup.style.display = 'none';
                                                            };
                                                        }



        </script>


        <script>
            function handleKeyPress(event) {
                if (event.key === 'Enter') {
                    event.preventDefault();
                    search();
                }
            }

            function search() {
                let searchTerm = document.getElementById('searchInput').value.trim();
                if (searchTerm !== '') {
                    // Chuyển hướng đến trang hiển thị kết quả tìm kiếm
                    window.location.href = 'listService?search=' + encodeURIComponent(searchTerm);
                }
            }
        </script>












    </body>

</html>
