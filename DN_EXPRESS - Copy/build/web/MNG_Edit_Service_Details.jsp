
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
                <h2 class="modalTitle">View Service</h2>
                
            </header>

            <div class="body">
                <form action="Manager_editService_Servlet" id="myForm" method="post">

                    <div class="inputFieldContainer">
                        
                         <div class="form_control">
                                <label for="serID">Service Type ID:</label>
                                <input value="${detailSer.service_type_id}"  type="text" name="serID" id="serID" readonly required>
                            </div>
                        
                         <div class="form_control">
                                <label for="serName">Service Type Name:</label>
                                <input value="${detailSer.service_type_value}" type="text" name="serName" id="serName" required>
                          </div>
                        
                        
                        
                            <c:forEach var="property" items="${serProperList}">
                                <div class="nameField">
                                    
                                <div class="form_control">
                                    <label for="proName">Service Name:</label>
                                    <input type="text" name="proName" id="proName" value="${property.getService_property_value()}" required>
                                </div>
                                <div class="form_control">
                                    <label for="proPrice">Service Price:</label>
                                    <input type="text" name="proPrice" id="proPrice" value="${property.getService_property_price()}" required>
                                </div>
                                <input type="hidden" name="proId" value="${property.getService_property_id()}">
                                 </div>
                            </c:forEach>
                       


                       
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
    
    <script src="js/Manage_Service.js" type="text/javascript"></script>
</body>

</html>