<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>DN EXPRESS</title>

    <!-- External CSS and Fonts -->
    <link href="https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css" integrity="sha512-MV7K8+y+gLIBoVD59lQIYicR65iaqukzvf/nwasF0nqhPay5w/9lJmVM2hMDcnK1OnMGCdVK+iQrJ7lzPJQd1w==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link href="css/mng_reviewTicket.css" rel="stylesheet" type="text/css"/>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA1mLLNrhFhndWA119ZTywc38Fyw9cyas0"></script>
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
                <a href="#" class="profile"><img src="img/img2.jpg" alt="Profile"></a>
            </nav>
            <!-- NAVBAR -->

            <!-- MAIN -->
            <main class="table" id="customers_table">
              
                <section class="table__body">
                    <c:if test="${not empty im_ex_ticket}">
                        <div class="reiceve_card" id="shipment_info">



                                <form action="mng_ReviewTicket" class="form" method="POST">

                                        <header class="header">
                                            <h1>Review Ticket</h1>
                                            <select class="filter_type" name="selected">
                                                        <option value="" disabled selected>${im_ex_ticket.ticketType}</option>
                                            </select>
                                        </header>


                                        <div class="card">
                                                <table class="aligned-table">
                                                        <tbody>
                                                            <tr>
                                                                <td><strong>Sender</strong></td>
                                                                <td><strong>:</strong></td>
                                                                <td>                                       
                                                                    ${im_ex_ticket.package_detail.shipment_order.customer.account.firstName} ${im_ex_ticket.package_detail.shipment_order.customer.account.lastName}                                                                    
                                                                </td>
                                                                <td><input type="checkbox" style="transform: scale(2);" name="checkbox" ${checkboxStatus[0]} disabled></td>
                                                                <td id="orderId" name="orderId" value="${im_ex_ticket.package_detail.shipment_order.order_id}"></td>
                                                            </tr>
                                                            <tr>
                                                                <td><strong>Phone:</strong></td>
                                                                <td><strong>:</strong></td>
                                                                <td>                                                                                    
                                                                   ${im_ex_ticket.package_detail.shipment_order.customer.account.phone_numb}
                                                                </td>
                                                                <td><input type="checkbox" style="transform: scale(2);"  name="checkbox" ${checkboxStatus[1]} disabled></td>
                                                            </tr>
                                                            <tr>
                                                                <td><strong>Address</strong></td>
                                                                <td><strong>:</strong></td>
                                                                <td>
                                                                    ${im_ex_ticket.package_detail.shipment_order.pick_up_apartment_number} ${im_ex_ticket.package_detail.shipment_order.pick_up_street_name}, ${im_ex_ticket.package_detail.shipment_order.pick_up_district},
                                                                    ${im_ex_ticket.package_detail.shipment_order.pick_up_ward}, ${im_ex_ticket.package_detail.shipment_order.pick_up_city}
                                                                </td>
                                                                <td><input type="checkbox" style="transform: scale(2);" name="checkbox" ${checkboxStatus[2]} disabled></td>
                                                            </tr>

                                                        </tbody>

                                                    </table>                                          
                                            </div>




                                            <div class="card">
                                                <table class="aligned-table">
                                                        <tbody>
                                                            <tr>
                                                                <td><strong>Receiver</strong></td>
                                                                <td><strong>:</strong></td>
                                                                <td>                                       
                                                                   ${im_ex_ticket.package_detail.shipment_order.final_receiver_name}                                                                  
                                                                </td>
                                                                <td><input type="checkbox" style="transform: scale(2);" name="checkbox" ${checkboxStatus[3]} disabled></td>
                                                                <input type="hidden" id="orderId" name="orderId" value="${im_ex_ticket.package_detail.shipment_order.order_id}">
                                                                
                                                            </tr>
                                                            <tr>
                                                                <td><strong>Phone:</strong></td>
                                                                <td><strong>:</strong></td>
                                                                <td>                                                                                    
                                                                   ${im_ex_ticket.package_detail.shipment_order.final_receiver_phone}
                                                                </td>
                                                                <td><input type="checkbox" style="transform: scale(2);" name="checkbox" ${checkboxStatus[4]} disabled></td>
                                                            </tr>
                                                            <tr>
                                                                <td><strong>Address</strong></td>
                                                                <td><strong>:</strong></td>
                                                                <td>
                                                                    ${im_ex_ticket.package_detail.shipment_order.final_apartment_number} ${im_ex_ticket.package_detail.shipment_order.final_street_name}, ${im_ex_ticket.package_detail.shipment_order.final_district}, 
                                                                              ${im_ex_ticket.package_detail.shipment_order.final_ward}, ${im_ex_ticket.package_detail.shipment_order.final_city}
                                                                </td>
                                                                <td><input type="checkbox" style="transform: scale(2);" name="checkbox" ${checkboxStatus[5]} disabled></td>
                                                            </tr>

                                                        </tbody>
                                                    </table>      
                                            </div>

                                             <div class="card"> 
                                                    <table class="aligned_table_isp">
                                                        <tbody>
                                                            <tr>
                                                                <td><strong>Integrity</strong></td>
                                                                <td><strong>:</strong></td>
                                                                <td>
                                                                    <select class="isp_status" name="integrity">
                                                                                <option value="" disabled selected>${im_ex_ticket.integrity}</option>
                                                                    </select>
                                                                </td>

                                                            </tr>
                                                            <tr>
                                                                <td><strong>Labeling</strong></td>
                                                                <td><strong>:</strong></td>
                                                                <td>
                                                                    <select class="isp_status" name="labeling">
                                                                                <option value="" disabled selected>${im_ex_ticket.labeling}</option>
                                                                                
                                                                    </select>
                                                                </td>

                                                            </tr>
                                                             <tr>
                                                                <td><strong>Quantity</strong></td>
                                                                <td><strong>:</strong></td>
                                                                <td><select class="isp_status" name="quantity">
                                                                                <option value="" disabled selected>${im_ex_ticket.quantity}</option>
                                                                                
                                                                    </select>
                                                                </td>

                                                            </tr>
                                                            <tr>
                                                                <td><strong>Documentation</strong></td>
                                                                <td><strong>:</strong></td>
                                                                <td><select class="isp_status" name="documentation">
                                                                                <option value="" disabled selected>${im_ex_ticket.documentation}</option>
                                                                                
                                                                    </select>
                                                                </td>
                                                            </tr>  
                                                        </tbody>

                                                    </table>
                                                </div>





                                                <div class="card">               
                                                    <table class="aligned-table">
                                                        <tbody>
                                                            <tr>
                                                                <td><strong>Packge Type</strong></td>
                                                                <td><strong>:</strong></td>
                                                                <td>

                                                                    ${im_ex_ticket.package_detail.package_type.package_type_value}
                                                                </td>
                                                                <td><input type="checkbox" style="transform: scale(2);" name="checkbox" ${checkboxStatus[6]} disabled></td>
                                                            </tr>
                                                            <tr>
                                                                <td><strong>Package Size</strong></td>
                                                                <td><strong>:</strong></td>
                                                                <td>

                                                                    ${im_ex_ticket.package_detail.package_size.package_size_value}
                                                                </td>
                                                                <td><input type="checkbox" style="transform: scale(2);" name="checkbox" ${checkboxStatus[7]} disabled></td>
                                                            </tr>
                                                            <tr>
                                                                <td><strong>Driver ID</strong></td>
                                                                <td><strong>:</strong></td>

                                                                <td>
                                                                    <c:choose>
                                                                        <c:when test="${order_Status.shipment_Order_driver.driver.account.account_id == null}">
                                                                            Waiting
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            ${order_Status.shipment_Order_driver.driver.account.account_id}
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </td>
                                                                <td><input type="checkbox" style="transform: scale(2);" name="checkbox" ${checkboxStatus[8]} disabled></td>
                                                            </tr>
                                                             <tr>
                                                                <td><strong>Package Weight</strong></td>
                                                                <td><strong>:</strong></td>
                                                                <td>${im_ex_ticket.package_detail.package_weight}</td>
                                                                <td><input type="checkbox" style="transform: scale(2);" name="checkbox" ${checkboxStatus[9]} disabled></td>
                                                            </tr>
                                                            <tr>
                                                                <td><strong>Value of Package</strong></td>
                                                                <td><strong>:</strong></td>
                                                                <td>${im_ex_ticket.package_detail.package_value}</td>
                                                                <td><input type="checkbox" style="transform: scale(2);" name="checkbox" ${checkboxStatus[10]} disabled></td>
                                                            </tr>
                                                              <tr>
                                                                <td><strong>Warranty</strong></td>
                                                                <td><strong>:</strong></td>
                                                                <td>Standard</td>
                                                                <td><input type="checkbox" style="transform: scale(2);" name="checkbox" ${checkboxStatus[11]} disabled></td>
                                                            </tr> 
                                                        </tbody>
                                                    </table>    
                                                </div>
                                                <div class="card"> 
                                                    <table class="aligned-table">
                                                        <tbody>
                                                            <tr>
                                                                <td><strong>Pick up date</strong></td>
                                                                <td><strong>:</strong></td>
                                                                <td>${im_ex_ticket.package_detail.shipment_order.order_date}</td>
                                                                <td><input type="checkbox" style="transform: scale(2);" name="checkbox" ${checkboxStatus[12]} disabled></td>
                                                            </tr>
                                                            <tr>
                                                                <td><strong>Receive date</strong></td>
                                                                <td><strong>:</strong></td>
                                                                <td>${im_ex_ticket.package_detail.requested_receiving_date}</td>
                                                                <td><input type="checkbox" style="transform: scale(2);" name="checkbox" ${checkboxStatus[13]} disabled></td>
                                                            </tr>
                                                             <tr>
                                                                <td><strong>Requested date</strong></td>
                                                                <td><strong>:</strong></td>
                                                                <td>${im_ex_ticket.package_detail.requested_receiving_date}</td>
                                                                <td><input type="checkbox" style="transform: scale(2);" name="checkbox" ${checkboxStatus[14]} disabled></td>
                                                            </tr>
                                                            <tr>
                                                                <td><strong>Note</strong></td>
                                                                <td><strong>:</strong></td>
                                                                <td>${im_ex_ticket.package_detail.package_note}</td>
                                                                <td><input type="checkbox" style="transform: scale(2);" name="checkbox" ${checkboxStatus[15]} disabled></td>
                                                            </tr>  
                                                        </tbody>

                                                    </table>
                                                </div>

                                                <div style="border-bottom: none" class="card">               
                                                    <table class="aligned-table">
                                                        <tbody>
                                                             <tr>
                                                                <td><strong>Amount</strong></td>
                                                                <td><strong>:</strong></td>
                                                                <td>
                                                                    ${invoice.total_amount} &#36
                                                                </td>
                                                                <td><input type="checkbox" style="transform: scale(2);" name="checkbox" ${checkboxStatus[16]} disabled></td>
                                                            </tr>
                                                        </tbody>
                                                    </table>    
                                                </div>
                                                <textarea name="ticketNote" class="ticketNote" disabled>${im_ex_ticket.note}</textarea>
                                                
                                                <div style="display: flex;justify-content: flex-end">           
                                                    <button type="submit" class="btn_acp_rej" value="2" style="color: #083c3e; background-color: whitesmoke; border: 1px solid #083c3e;">Reject</button> 
                                                    <button type="submit" class="btn_acp_rej" value="3" style="color: white">Accept</button> 
                                                </div>
                                                <input type="hidden" id="ticketStatusID" name="ticketStatusID" value="">
                                                <input type="hidden" id="ticketId" name="ticketId" value="${im_ex_ticket.import_export_ticket_id}">
                                </form>


                            </div>
                     </c:if>
                    
                    <c:if test="${empty im_ex_ticket && not empty mgs}">
                        <div class="reiceve_card" id="shipment_info" style="padding: 2em 2em 2em 2em;">
                            <div class="empty" align="center"><strong>${mgs}</strong></div>
                        </div>
                    </c:if>
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
    <script src="js/mng_reviewTicket.js" type="text/javascript"></script>
    <script>
    document.addEventListener("DOMContentLoaded", function() {
    var buttons = document.querySelectorAll(".btn_acp_rej");

    buttons.forEach(function(button) {
        button.addEventListener("click", function() {
            var value = this.getAttribute("value"); 
            document.getElementById("ticketStatusID").value = value; 
        });
    });
});
</script>
</body>

</html>

