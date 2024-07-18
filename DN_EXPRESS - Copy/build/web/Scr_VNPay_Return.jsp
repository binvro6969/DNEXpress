<%@page import="java.net.URLEncoder"%>
<%@page import="java.nio.charset.StandardCharsets"%>
<%@page import="vnpay_context.VNPayUtils"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <meta name="description" content="">
        <meta name="author" content="">
        <title>KẾT QUẢ THANH TOÁN</title>
        <!-- Bootstrap core CSS -->
        <link href="css/bootstrap.min.css" rel="stylesheet"/>
        <!-- Custom styles for this template -->
        <link href="css/jumbotron-narrow.css" rel="stylesheet"> 
        <script src="js/jquery-1.11.3.min.js"></script>

    </head>
    <body>
        <%
            //Begin process return from VNPAY
            Map fields = new HashMap();
            for (Enumeration params = request.getParameterNames(); params.hasMoreElements();) {
                String fieldName = URLEncoder.encode((String) params.nextElement(), StandardCharsets.US_ASCII.toString());
                String fieldValue = URLEncoder.encode(request.getParameter(fieldName), StandardCharsets.US_ASCII.toString());
                if ((fieldValue != null) && (fieldValue.length() > 0)) {
                    fields.put(fieldName, fieldValue);
                }
            }

            String vnp_SecureHash = request.getParameter("vnp_SecureHash");
            if (fields.containsKey("vnp_SecureHashType")) {
                fields.remove("vnp_SecureHashType");
            }
            if (fields.containsKey("vnp_SecureHash")) {
                fields.remove("vnp_SecureHash");
            }
            String signValue = VNPayUtils.hashAllFields(fields);

        %>
        <!--Begin display -->
        <div class="container">
            <div class="header clearfix">
                <h3 class="text-muted">KẾT QUẢ THANH TOÁN</h3>
            </div>
            <div class="table-responsive">
                <div class="form-group">
                    <label >Mã giao dịch thanh toán:</label>
                    <label><%=request.getParameter("vnp_TxnRef")%></label>
                </div>    
                <div class="form-group">
                    <label >Số tiền:</label>
                    <label><%=request.getParameter("vnp_Amount")%></label>
                </div>  
                <div class="form-group">
                    <label >Mô tả giao dịch:</label>
                    <label><%=request.getParameter("vnp_OrderInfo")%></label>
                </div> 
                <div class="form-group">
                    <label >Mã lỗi thanh toán:</label>
                    <label><%=request.getParameter("vnp_ResponseCode")%></label>
                </div> 
                <div class="form-group">
                    <label >Mã giao dịch tại CTT VNPAY-QR:</label>
                    <label><%=request.getParameter("vnp_TransactionNo")%></label>
                </div> 
                <div class="form-group">
                    <label >Mã ngân hàng thanh toán:</label>
                    <label><%=request.getParameter("vnp_BankCode")%></label>
                </div> 
                <div class="form-group">
                    <label >Thời gian thanh toán:</label>
                    <label><%=request.getParameter("vnp_PayDate")%></label>
                </div> 
                <div class="form-group">
                    <label >Tình trạng giao dịch:</label>
                    <label>
                        <%
                            if (signValue.equals(vnp_SecureHash)) {
                                if ("00".equals(request.getParameter("vnp_TransactionStatus"))) {
                                    out.print("Thành công");
                                } else {
                                    out.print("Không thành công");
                                }

                            } else {
                                out.print("invalid signature");
                            }
                        %></label>
                </div> 
            </div>
            <div>
                <input type="hidden" id="userType" value="driver">
                <!--<input type="hidden" id="userType" value="<%= session.getAttribute("userType")%>"> -->
                <a class="btn btn-primary fw-bold px-3 py-2" href="#" onclick="submitToPaymentReturn()"> Go Back</a>


            </div>
            <p>
                &nbsp;
            </p>
            <footer class="footer">
                <p>&copy; VNPAY 2020</p>
            </footer>
        </div>
        <script>
            console.log("URL params: " + currentUrl);
            console.log("Param array: " + paramArray);
            console.log("Key: " + key + ", Value: " + value);
            console.log("signValue: " + signValue);

        </script>     
        <!--   <script>
        function submitToPaymentReturn() {
            var form = document.createElement("form");
            form.setAttribute("method", "post");
            form.setAttribute("action", "Driver_VNPAYpaymentWalletReturn_Servlet");
        
            // Lấy các tham số từ URL hiện tại và thêm vào form
            var currentUrl = window.location.href;
            var params = currentUrl.split("?")[1]; // Lấy phần query parameters sau dấu ?
            if (params) {
                var paramArray = params.split("&");
        
                for (var i = 0; i < paramArray.length; i++) {
                    var keyValue = paramArray[i].split("=");
                    var key = decodeURIComponent(keyValue[0]);
                    var value = decodeURIComponent(keyValue[1] || ""); // Xử lý trường hợp value rỗng
        
                    // Kiểm tra nếu là driver_id thì thêm vào form
                    if (key === "driver_id") {
                        var input = document.createElement("input");
                        input.setAttribute("type", "hidden");
                        input.setAttribute("name", "driver_id");
                        input.setAttribute("value", value);
                        form.appendChild(input);
                    } else {
                        // Thêm các tham số khác vào form
                        var input = document.createElement("input");
                        input.setAttribute("type", "hidden");
                        input.setAttribute("name", key);
                        input.setAttribute("value", value);
                        form.appendChild(input);
                    }
                }
            }
        
            // Thêm form vào body và submit
            document.body.appendChild(form);
            form.submit();
        }
        </script>
        -->

        <script>
            function submitToPaymentReturn() {
                var form = document.createElement("form");
                form.setAttribute("method", "get");

                var userType = document.getElementById("userType").value;

                // Set the form action based on the user type
//                if (userType === "driver") {
//                    form.setAttribute("action", "Driver_VNPAYpaymentWalletReturn_Servlet");
//                } else if (userType === "customer") {
//                    form.setAttribute("action", "Customer_Bill_Servlet");
//                }
                form.setAttribute("action", "Customer_Bill_Servlet");
                var currentUrl = window.location.href;
                var params = currentUrl.split("?")[1];
                if (params) {
                    var paramArray = params.split("&");
                    for (var i = 0; i < paramArray.length; i++) {
                        var keyValue = paramArray[i].split("=");
                        var key = decodeURIComponent(keyValue[0]);
                        var value = decodeURIComponent(keyValue[1] || "");

                        var input = document.createElement("input");
                        input.setAttribute("type", "hidden");
                        input.setAttribute("name", key);
                        input.setAttribute("value", value);
                        form.appendChild(input);
                    }
                }

                document.body.appendChild(form);
                form.submit();
            }
        </script>

    </body>
</html>
