
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>DN EXPRESS</title>

   
    <link href="https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css" integrity="sha512-MV7K8+y+gLIBoVD59lQIYicR65iaqukzvf/nwasF0nqhPay5w/9lJmVM2hMDcnK1OnMGCdVK+iQrJ7lzPJQd1w==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link href="css/mng_trackingDriver.css" rel="stylesheet" type="text/css"/>
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
                <a href="#" class="nav-link"><strong>Tracking Driver</strong></a>
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
                <a href="#" class="profile"><img src="img/img2.jpg" alt="Profile"></a>
            </nav>
            <!-- NAVBAR -->

            <!-- MAIN -->
            <main class="table" id="customers_table">
                
                <section class="table__body">
                    <table>
                        <header>
                            <div class="filterEntries">
                                <div class="addMemberBtn">
                                    <button style="visibility: hidden;">New Staff</button>
                                </div>
                                
                                <form action="mng_trackDriver" method="post"> 
                                    <div class="filter">
                                        <label for="search" style="color: #083c3e;"><strong>Search:</strong></label>
                                        <input type="search" id="search" placeholder="Enter here" name="searchIndex">
                                        <select class="filter" name="selected">
                                            <option value="" disabled selected>Search type</option>
                                            <option value="byId">By Id</option>
                                            <option value="byName">By Name</option>
                                            <option value="byPostOffice">By Post Office</option>
                                        </select>
                                    </div>
                                    <input type="submit" value="search" hidden>
                                </form>
                                   

                            </div>
                        </header>
                        
                            <thead>
                                <tr>
                                    <th> STT <span class="icon-arrow"></span></th>
                                    <th> Driver Name <span class="icon-arrow"></span></th>
                                    <th> Driver ID <span class="icon-arrow"></span></th>
                                    <th> Post Office <span class="icon-arrow"></span></th>
                                    <th> Status <span class="icon-arrow"></span></th>
                                    <th> Action <span class="icon-arrow"></span></th>
                                </tr>
                            </thead>
                        
                        <c:if test="${not empty driverList}">
                            <tbody>
                                 <c:forEach var="drv" items="${driverList}">
                                    <form action="mng_trackDriver_viewDetails" method="get">
                                            <tr>
                                                <td> 1 </td>
                                                <td class="td_avt">
                                                     <img src="${drv.account.avatar}" alt="">
                                                     ${drv.account.firstName} ${drv.account.lastName}
                                                </td>
                                                <td> ${drv.account.account_id} </td>
                                                <td> ${drv.post_office.city} </td>
                                                <td><p class="status ${drv.status ? 'Online' : 'Offline'}">${drv.status ? 'Online' : 'Offline'}</p></td>
                                                <td>
                                                    <button type="submit"><i class="viewInforBtn fa-regular fa-eye"></i></button>
                                                </td>
                                            </tr>
                                            
                                            <input type="hidden" name="driver_id" value="${drv.account.account_id}">
                                            <input type="hidden" name="latitude" value="${drv.latitude}">
                                            <input type="hidden" name="longitude" value="${drv.longitude}">
                                            
                                     </form>
                                 </c:forEach>
                            </tbody>
                        </c:if>
                                        
                        <c:if test="${empty driverList}">
                            <tr><td class="empty" colspan="11" align="center">${msg}</td></tr>
                        </c:if>
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

    <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
    <script src="js/mng_trackingDriver.js" type="text/javascript"></script>
</body>

</html>

