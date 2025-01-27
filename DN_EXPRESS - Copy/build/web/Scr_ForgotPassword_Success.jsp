<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">

  <title>DN EXPRESS</title>


  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">

  <link rel="stylesheet" type="text/css" href="css/forgot_pass_style.css">
    
  <link rel="stylesheet" type="text/css" href="include/navbar_landing_page.css">
  <!-- Boxicons -->
  <link href='https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css' rel='stylesheet'>
  <script src="https://kit.fontawesome.com/d60694e6d9.js" crossorigin="anonymous"></script>

  <link rel="stylesheet" type="text/css" href="include/footer.css">



</head>

<body class="body_forgot">

  <!-- Navigation Div Starts -->
  <main>

  </main>
  <section class="confiq">

    <!-- CONTENT -->
    <section id="content">
      <!-- Navigation Div Starts -->

      <nav class="navbar navbar-expand-lg navbar-light navbar-normal">

        <div class="navigation container-fluid d-flex align-items-center my-2 pe-5 ps-4 ">

       

          <button class="navbar-toggler" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasNavbar2"
            aria-controls="offcanvasNavbar2" aria-label="Toggle navigation"><ion-icon
              name="menu-outline"></ion-icon></button>

          <div class="offcanvas offcanvas-end" tabindex="-1" id="offcanvasNavbar2"
            aria-labelledby="offcanvasNavbar2Label">

            <div class="offcanvas-header">
              <h5 class="offcanvas-title" id="offcanvasNavbar2Label">Menu</h5>
              <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
            </div>

            <div class="offcanvas-body">

              <div class="brand-logo">
                <a href="/Landing_page/landing_page.html" class="brand brand-normal">
                  <i class='bx bxs-package'></i>
                  <span class="text">DN Express</span>
                </a>
              </div>

              <ul class="navbar-nav align-items-center ">
                <li class="nav-list">
                  <a href="result.html" class="nav-link px-2 navbar-text">
                    <span> Home </span>
                  </a>
                </li>
                <li class="nav-list">
                  <a href="result.html" class="nav-link px-2 navbar-text">
                    <span> About </span>
                  </a>
                </li>
                <li class="nav-list">
                  <a href="result.html" class="nav-link px-2 navbar-text">
                    <span> Service </span>
                  </a>
                </li>
                <li class="nav-list">
                  <a href="/LDP_Staff_Recuiment/Scr_staff_recuitment_staff_recuitment.html" class="nav-link px-2 navbar-text">
                    <span> Recruitment </span>
                  </a>
                </li>
              </ul>
                
              <div class="log_sign">
                <div class="btn_signin">
                  <a href="/LDP_Sign Up/Scr_signUp_signUp.html" class="nav-link ms-2 navbar-text">
                    <span>Sign Up</span>
                  </a>
                </div>
                <div class="bth_login">
                  <a href="/LDP_Login/Scr_Login_login.html" class="nav-link ms-2 navbar-text">
                    <span>Login</span>
                  </a>
                </div>
              </div>
            </div>
      </nav>
      <!-- Navigation Div End -->

      <!-- Hero Section Starts -->
      <main class="forgot-pass-main d-flex justify-content-center">
        <section class="forgot_pass_form col-md-5">
            <div class="card mt-5 border-0 shadow rounded-3">
              <div class="container">
                <div class="card-body text-center">
                  <i class="fa-solid fa-circle-check success"></i>
                  <h2 class="card-title ">Password reset successfully</h2>
                  <p>You can login again with new password</p>
                  
                  <div class="d-grid mb-2 justify-content-center">
                      <a class="btn_back btn-primary fw-bold" href="Authentication_LoginMain_Servlet">Back</a>
                </div> 
              </div>
              </div>

            </div>
        </section>

      </main>
    </section>
  </section>

  <!-- Footer Section Starts -->





  <footer class="footer">
    <div class="waves">
      <div class="wave" id="wave1"></div>
      <div class="wave" id="wave2"></div>
      <div class="wave" id="wave3"></div>
      <div class="wave" id="wave4"></div>
    </div>
    <ul class="social-icon">
      <li class="social-icon__item"><a class="social-icon__link" href="#">
          <ion-icon name="logo-facebook"></ion-icon>
        </a></li>
      <li class="social-icon__item"><a class="social-icon__link" href="#">
          <ion-icon name="logo-twitter"></ion-icon>
        </a></li>
      <li class="social-icon__item"><a class="social-icon__link" href="#">
          <ion-icon name="logo-linkedin"></ion-icon>
        </a></li>
      <li class="social-icon__item"><a class="social-icon__link" href="#">
          <ion-icon name="logo-instagram"></ion-icon>
        </a></li>
    </ul>
    <ul class="menu">
      <li class="menu__item"><a class="menu__link" href="#">Home</a></li>
      <li class="menu__item"><a class="menu__link" href="#">About</a></li>
      <li class="menu__item"><a class="menu__link" href="#">Services</a></li>
      <li class="menu__item"><a class="menu__link" href="#">Team</a></li>
      <li class="menu__item"><a class="menu__link" href="#">Contact</a></li>

    </ul>
    <p>&copy;2024 D04 R02 | All Rights Reserved</p>
  </footer>



  <!-- Scripts  Starts -->
  <script src="js/forgot_password.js"></script>

</body>

</html>