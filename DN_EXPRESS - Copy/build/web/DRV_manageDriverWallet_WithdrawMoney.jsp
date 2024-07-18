
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
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
  <link href="css/driver_wallet.css" rel="stylesheet" type="text/css"/>
  <link rel="stylesheet" type="text/css" href="/include/footer.css">

  <title>DN EXPRESS</title>
</head>

<body>

  <section class="confiq">
    
        <!-- SIDEBAR -->
         <%@include file="/include/sidebar_drv.jsp" %>
      <!-- SIDEBAR -->



    <!-- CONTENT -->
    <section id="content">
      <!-- NAVBAR -->
      <nav>
        <i class='bx bx-menu'></i>
        <a href="#" class="nav-link"><strong>Manage Notification</strong></a>
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
          <img src="DRV_ManageWallet/img/img2.jpg">
        </a>
      </nav>
      <!-- NAVBAR -->
      <!-- Hero Section Starts -->

      <main id="edit-profile" class="d-flex justify-content-center">

        <section class="col-md-10 ">
          <div class="row">

            <div class="col-md-3">
              <div class="form-container left-bar">
                <div class="avatar">
                  <label for="image-upload">
                    <img src="DRV_ManageWallet/img/img2.jpg" alt="avatar" class=" img-fluid">
                  </label>
                  <input type="file" accept="image/*" id="image-upload" hidden>
                </div>
                <h4>John Doe</h4>
                <span>@johndoe123</span>
                <hr>
                <ul>
                  <li><a href="Scr_EditProfile_EditProfileDriver.html">Edit Profile</a></li>
                  <li><a href="Scr_EditProfile_ChangePass.html">Change password</a></li>
                  <li><a href="Scr_EditProfile_EditPaymentCustomer.html">Manage Payment</a></li>
                </ul>

              </div>
            </div>

            <div class="col-md-9">
              <div class="form-container right-bar">
                <header class="py-3 text-center">
                  <h1>Withdraw Money</h1>
                </header>

                <div class="container">
                  <div class="col-md-10">

                    <div class="money-content-container">
                      <div class="totalMoney">
                        <h4>Total Money:</h4>
                        <h3>đ <%= session.getAttribute("balance") %></h3>
                      </div>
                      <div class="money-content">
                        <span>Enter the amount </span>
                        <div class="money-input">
                            <input name="#" type="tel" placeholder="200.000">
                            <span>VND</span>
                        </div>
  
                        <div class="paymentMethod-container">
                          <span>Payment Method</span>
                            <label class="paymentMethod col-md-3">
                                <input type="radio" name="payment-method" value="vnpay">
                                <img src="img/vnpay-payment-gateway.jpg" alt="VNPAY">
                                <p>VNPAY</p>
                            </label>
                        </div>
                                                  
                        <div class="form-group">
                          <input type="submit" class="btn btn-primary fw-bold  py-2" id="submit" value="Withdraw">
                        </div>
                    </div>
                    </div>
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
  <script src="js/driver_wallet.js" type="text/javascript"></script>


</body>

</html>