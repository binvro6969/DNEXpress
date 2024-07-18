<%-- 
    Document   : Manage_Staff
    Created on : 23-Jun-2024, 14:25:23
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
        <link href="css/Manage_Staff.css" rel="stylesheet" type="text/css"/>
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
                    <a href="getListStaffInternal" class="nav-link"><strong>Manage Staff</strong></a>
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
                <main class="table"  id="mainContent" >


                    <section class="table__body" id="driverTable">
                        <table>
                            <header>
                                <div class="filterEntries">
                                    <div class="addMemberBtn">
                                        <button onclick="window.location.href = 'MNG_Create_Staff_Internal.jsp'">
                                            New Staff</button>
                                    </div>

                                    <form action="Manager_searchStaffInternal_Servlet" method="post">
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
                                    <th> Staff Name <span class="icon-arrow"></span></th>
                                    <th> Staff ID <span class="icon-arrow"></span></th>
                                    <th> Phone <span class="icon-arrow"></span></th>
                                    <th> Status <span class="icon-arrow"></span></th>
                                    <th> Action <span class="icon-arrow"></span></th>
                                </tr>
                            </thead>
                            <tbody>
                                <!-- <tr><td class="empty" colspan="11" align="center">No data available in table</td></tr> -->

                                <c:forEach var="searchStaff" items="${searchResult}" varStatus="loop">
                                    <tr>
                                        <td>${loop.index + 1}</td>
                                        <td>${searchStaff.account.getFirstName()} ${searchStaff.account.getLastName()}</td>
                                        <td>${searchStaff.account.getAccount_id()}</td>
                                        <td>${searchStaff.account.getPhone_numb()}</td>
                                        <td>${searchStaff.account.isStatus() ? "Active" : "Inactive"}</td>
                                        <td>
                                            <button class="action-btn view-btn" data-id="${searchStaff.account.getAccount_id()}" ><i class="fa-regular fa-eye"></i></button>
                                            <button class="action-btn edit-btn" 
                                                    onclick="window.location.href = 'Manger_editStaffDetails_Servlet?account_id=${searchStaff.account.getAccount_id()}'">
                                                <i class="fa-regular fa-pen-to-square"></i></button>
                                            <button class="action-btn delete-btn" 
                                                    onclick="showDeletePopup(${searchStaff.account.getAccount_id()})"><i class="fa-regular fa-trash-can"></i></button>

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

        <div class="dark_bg">

            <div class="popup">
                <header>
                    <h2 class="modalTitle">Fill the Form</h2>
                    <button class="closeBtn">&times;</button>
                </header>

                <div class="body">
                    <form action="#" id="myForm" method="post">
                        <div class="imgholder">
                            <label for="uploading" class="upload">
                                <input type="file" name="" id="uploadimg" class="picture">
                                <i class="fa-solid fa-plus"></i>
                            </label>
                            <img src="" width="150" height="150" class="img" id="driverAvatar">
                        </div>

                        <div class="inputFieldContainer">
                            <div class="nameField">
                                <div class="form_control">
                                    <label for="fName">First Name:</label>
                                    <input type="text" id="fName" required>
                                </div>
                                <div class="form_control">
                                    <label for="lName">Last Name:</label>
                                    <input type="text" id="lName" required>
                                </div>
                            </div>
                            <div class="identify">
                                <div class="form_control">
                                    <label for="account_id">Account ID:</label>
                                    <input type="text" id="account_id" required>
                                </div>
                                <div class="form_control">
                                    <label for="gender">Gender:</label>
                                    <input type="text" id="gender" required>
                                </div>
                            </div>
                            <div class="location">
                                <div class="form_control">
                                    <label for="license">License Number:</label>
                                    <input type="text" id="license" required>
                                </div>
                                <div class="form_control">
                                    <label for="cid">Citizen Identity:</label>
                                    <input type="text" id="cid" required>
                                </div>
                            </div>

                            <div class="location">
                                <div class="form_control">
                                    <label for="post_officeID">Post Office ID:</label>
                                    <input type="text" id="post_officeID" required>
                                </div>
                            </div>


                            <div class="form_control">
                                <label for="dob">Date of Birth:</label>
                                <input type="date" id="dob" required>
                            </div>
                            <div class="form_control">
                                <label for="email">Email:</label>
                                <input type="email" id="email" required>
                            </div>
                            <div class="form_control">
                                <label for="phone">Phone:</label>
                                <input type="number" id="phone" required>
                            </div>
                        </div>

                    </form>
                </div>



                <footer class="popupFooter">
                    <button form="myForm" class="submitBtn" >Submit</button>
                </footer>


            </div>
        </div>

        <div class="overlay" id="overlay" ></div>

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
        <script src="ManageStaff/Manage_Staff.js" type="text/javascript"></script>


        <script>document.addEventListener('DOMContentLoaded', function () {
                                                            const showDriver = document.getElementById('showDriver');
                                                            const showStaff = document.getElementById('showStaff');
                                                            const driverTable = document.getElementById('driverTable');
                                                            const staffTable = document.getElementById('staffTable');

                                                            showDriver.addEventListener('click', function () {
                                                                driverTable.style.display = 'block';
                                                                staffTable.style.display = 'none';
                                                            });

                                                            showStaff.addEventListener('click', function (event) {
                                                                event.preventDefault();
                                                                staffTable.style.display = 'block';
                                                                driverTable.style.display = 'none';
                                                            });
                                                        });
        </script>

        <script>
            function showDeletePopup(accountId) {
                const deletePopup = document.getElementById('deletePopup');
                const confirmDeleteBtn = document.getElementById('confirmDeleteBtn');

                deletePopup.style.display = 'block';

                confirmDeleteBtn.onclick = function () {
                    window.location.href = 'Manager_deleteStaffInternal_Servlet?account_id=' + accountId;
                };

                document.getElementById('cancelDeleteBtn').onclick = function () {
                    deletePopup.style.display = 'none';
                };
            }
        </script>


        <script>
            // Đoạn mã JavaScript để xử lý sự kiện click vào nút "view-btn"
            document.querySelectorAll('.view-btn').forEach(button => {
                button.addEventListener('click', function () {
                    // Lấy accountId từ thuộc tính data-id của nút
                    var accountId = this.getAttribute('data-id');

                    // Gửi yêu cầu AJAX tới Servlet để lấy thông tin Driver
                    var xhr = new XMLHttpRequest();
                    xhr.open('GET', 'Manager_getStaffDetails_Servlet?accountId=' + encodeURIComponent(accountId), true);
                    xhr.onload = function () {
                        if (xhr.status >= 200 && xhr.status < 400) {
                            // Xử lý phản hồi từ Servlet
                            var data = JSON.parse(xhr.responseText);
                            // Hiển thị thông tin Driver lấy được vào pop-up hoặc bất kỳ thành phần nào trên trang của bạn
                            console.log(data); // Để debug, bạn có thể thay đổi cách hiển thị dữ liệu ở đây
                        } else {
                            console.error('Failed to fetch data: ' + xhr.status);
                        }
                    };
                    xhr.onerror = function () {
                        console.error('Request failed');
                    };
                    xhr.send();
                });
            });
        </script>
        <script>
            document.addEventListener('DOMContentLoaded', function () {
                var viewBtns = document.querySelectorAll('.view-btn');
                var overlay = document.querySelector('.dark_bg');
                var popup = document.querySelector('.popup');

                viewBtns.forEach(function (btn) {
                    btn.addEventListener('click', function () {
                        // Lấy accountId từ thuộc tính data-id của nút
                        var accountId = btn.getAttribute('data-id');

                        // Gửi yêu cầu lấy thông tin driver bằng AJAX
                        var xhr = new XMLHttpRequest();
                        xhr.onreadystatechange = function () {
                            if (xhr.readyState === XMLHttpRequest.DONE) {
                                if (xhr.status === 200) {
                                    var staff = JSON.parse(xhr.responseText);

                                    // Hiển thị thông tin driver lên popup
                                    document.getElementById('driverAvatar').src = staff.staffAvatar;
                                    document.getElementById('fName').value = staff.firstName;
                                    document.getElementById('lName').value = staff.lastName;
                                    document.getElementById('account_id').value = staff.accountId;
                                    document.getElementById('gender').value = staff.gender;
                                    document.getElementById('cid').value = staff.cccdNumber;
                                    document.getElementById('dob').value = staff.dob;
                                    document.getElementById('email').value = staff.email;
                                    document.getElementById('phone').value = staff.phone;
                                    document.getElementById('post_officeID').value = staff.postOfficeID;
                                    //document.getElementById('post_officeAddress').value = staff.postOfficeID;



                                    // Hiển thị overlay và popup
                                    overlay.style.display = 'flex'; // Sử dụng flex để căn giữa nội dung
                                    popup.style.display = 'block';
                                } else {
                                    console.error('Failed to fetch staff information.');
                                }
                            }
                        };

                        xhr.open('GET', 'Manager_getStaffDetails_Servlet?accountId=' + accountId);
                        xhr.send();
                    });
                });

                // Đóng popup khi nhấn vào nút closeBtn hoặc overlay
                document.querySelector('.closeBtn').addEventListener('click', function () {
                    overlay.style.display = 'none';
                    popup.style.display = 'none';
                });



                // Tắt overlay và popup khi click vào vị trí khác trên trang
                document.addEventListener('click', function (event) {
                    if (!popup.contains(event.target) && !viewBtns.contains(event.target)) {
                        overlay.style.display = 'none';
                        popup.style.display = 'none';
                    }
                });
            });

        </script>


        <script>
            xhr.onreadystatechange = function () {
                if (xhr.readyState === XMLHttpRequest.DONE) {
                    if (xhr.status === 200) {
                        try {
                            var data = JSON.parse(xhr.responseText);
                            // Xử lý dữ liệu JSON ở đây
                        } catch (e) {
                            console.error('Error parsing JSON:', e);
                        }
                    } else {
                        console.error('Failed to fetch data. Status:', xhr.status);
                    }
                }
            };

        </script>





        <script>
            xhr.onreadystatechange = function () {
                if (xhr.readyState === XMLHttpRequest.DONE) {
                    if (xhr.status === 200) {
                        try {
                            var staff = JSON.parse(xhr.responseText);
                            // Xử lý dữ liệu staff ở đây
                        } catch (e) {
                            console.error('Error parsing JSON:', e);
                            // Xử lý khi có lỗi xảy ra trong việc phân tích JSON
                        }
                    } else {
                        console.error('Failed to fetch staff information. Status:', xhr.status);
                        // Xử lý khi có lỗi trong việc gọi AJAX
                    }
                }
            };

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
                    window.location.href = 'Manager_searchStaffInternal_Servlet?searchCriteria=' + encodeURIComponent(searchTerm);
                }
            }
        </script>

    </body>
</html>
