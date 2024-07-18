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
    <link href="css/mng_managePostOffice.css" rel="stylesheet" type="text/css"/>

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
                <a href="#" class="nav-link"><strong>Manage Post Office</strong></a>
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
            <main>
		

                <ul class="box-info">
                    <li>
                        <i class='bx bx-import'></i>
                        <span class="text">
                            <h3>${numbOfImportTicket}</h3>
                            <p>Import</p>
                        </span>
                    </li>
                    <li>
                        <i class='bx bx-export' ></i>
                        <span class="text">
                            <h3>${numbOfExportTicket}</h3>
                            <p>Export</p>
                        </span>
                    </li>
                    <li>
                        <i class='bx bx-store'></i>
                        <span class="text">
                            <h3>${numOfStore}</h3>
                            <p>Inventory</p>
                        </span>
                    </li>
                   
                </ul>
    
    
                <div class="table-data">
                    <div class="order">
                        <div class="head">
                            <h3>Recent</h3>
                            <i class='bx bx-search' ></i>
                            <i class='bx bx-filter' ></i>
                        </div>
                        <table>
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Date</th>
                                    <th>Reviewer Id</th>
                                    <th>Type</th>
                                    <th>Status</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:if test="${not empty ticketList}">
                                    <c:forEach var="ticket" items="${ticketList}">
                                        <c:if test="${ticket.ticket_status.ticket_status_id == 2 || ticket.ticket_status.ticket_status_id == 3}">
                                            <tr>
                                                <td>
                                                    <p>${ticket.package_detail.shipment_order.order_id}</p>
                                                </td>
                                                <td>${ticket.create_date}</td>
                                                <td>${ticket.manager.account.account_id}</td>
                                                <td><span class="status ${ticket.ticketType}">${ticket.ticketType}</span></td>
                                                <td><span class="status ${ticket.ticket_status.ticket_status_value}">${ticket.ticket_status.ticket_status_value}</span></td>
                                            </tr>
                                        </c:if>

                                   </c:forEach>
                                </c:if>
                                
                                <c:if test="${empty ticketList}">
                                    <tr><td class="empty" colspan="11" align="center">${msg}</td></tr>
                                </c:if>

                            </tbody>
                        </table>
                    </div>
                    
                    <div class="request_line">
                        <div class="head">
                            <h3>Waiting to review</h3>
                           

                            
                        </div>
                        
                        <ul class="rq-list">
                            <c:if test="${not empty ticketList}">
                                    <c:forEach var="ticket" items="${ticketList}">
                                        <c:if test="${ticket.ticket_status.ticket_status_id == 1}">
                                            <li class="completed">
                                                <p>Ticket ID: ${ticket.import_export_ticket_id}</p>
                                                <button><i class="viewInforBtn fa-regular fa-pen-to-square" data-ticket-id="${ticket.import_export_ticket_id}" ></i></button>
                                            </li>
                                        </c:if>

                                   </c:forEach>
                            </c:if>
                                
                            <c:if test="${empty ticketList}">
                                    <tr><td class="empty" colspan="11" align="center">${msg}</td></tr>
                            </c:if>
                                                                    
                        </ul>   
                    </div>
                </div>
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
    <script src="js/mng_managePostOffice.js" type="text/javascript"></script>
   
</body>

</html>
