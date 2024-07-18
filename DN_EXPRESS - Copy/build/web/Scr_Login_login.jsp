<%-- 
    Document   : Scr_Login_login
    Created on : Jun 17, 2024, 2:51:47 PM
    Author     : haian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">

  <title>DN EXPRESS</title>

  <link rel="stylesheet" type="text/css" href="css/vendor.css">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.css" />
  <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>

  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">

  <link rel="stylesheet" type="text/css" href="css/login_style.css">  
  <link rel="stylesheet" type="text/css" href="css/navbar_landing_page.css">


  <!-- script ================================================== -->
  <script src="js/modernizr.js"></script>
</head>

<body class="body_login" data-bs-spy="scroll" data-bs-target="#navbar-example2" tabindex="0">

  <section class="confiq">

    <!-- CONTENT -->
    <section id="content">

      <!-- Navigation Div Starts -->

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
                <a href="landing_page.jsp" class="brand brand-normal">
                  <i class='bx bxs-package'></i>
                  <span class="text">DN Express</span>
                </a>
              </div>

              <ul class="navbar-nav align-items-center ">
                <li class="nav-list">
                  <a href="landing_page.jsp" class="nav-link px-2 navbar-text">
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
                  <a href="Authentication_SignUp_Servlet" class="nav-link ms-2 navbar-text">
                    <span>Sign Up</span>
                  </a>
                </div>
                <div class="bth_login">
                  <a href="Authentication_LoginMain_Servlet" class="nav-link ms-2 navbar-text">
                    <span>Login</span>
                  </a>
                </div>
              </div>
            </div>
      </nav>
      <!-- Navigation Div End -->
      
      <main class="login_page d-flex justify-content-center">
        <section class="login_form col-md-6">
          <div class="card mt-5 border-0 shadow rounded-3">
            <div class="container">
              <div class="card-body my-2 col-md-10">
                <h1 class="card-title text-center ">Login</h1>
                <form action="Authentication_LoginMain_Servlet"  method="post">
                    <select id="role" name="role" class="login-type">
                        <option value="1" mame="Customer">Customer</option>
                        <option value="2" mame="Driver">Driver</option>
                        <option value="3" mame="Staff">Staff</option>
                        <option value="4" mame="manager">Manager</option>
                    </select>
                  <br>
                  <p class="text-danger">${message}</p>
                  <div class="form-group form-floating mb-3">
                    <input type="text" name="phoneNumber" class="form-control" id="floatingInputUsername"
                      placeholder="myusername"> <label for="floatingInputUsername">Phone Number</label>
                  </div>

                  <div class="form-floating mb-3">
                    <input type="password" name="password" class="form-control" id="floatingPassword"
                      placeholder="Password">
                    <label for="floatingPassword">Password</label>
                  </div>
                  <div class="remember-forgot">
                    <div>
                      <input type="checkbox" id="remember_me" name="remember">
                      <label for="remember_me"> Remember Me</label>
                    </div>
                    <a class="forgot-password" href="Scr_ForgotPassword_EnterEmail.jsp">Forgot password?</a>
                  </div>
                  <div class="d-grid mb-2">
                    <button class="btn btn-lg btn-primary fw-bold text-uppercase" type="submit">Login</button>
                  </div>  
                </form>
                <a href="https://accounts.google.com/o/oauth2/auth?scope=email%20profile&redirect_uri=http://localhost:8080/DN_EXPRESS/LoginServlet&response_type=code
                                &client_id=439699209301-9iqj2l29punn6vaemn5k0tjr636b3gu5.apps.googleusercontent.com&approval_prompt=force">
                    <div class="d-grid mb-2">                   
                        <button class="btn btn-lg btn-primary google-login" >
                        <img src="https://developers.google.com/identity/images/g-logo.png" alt="Google Logo"
                            class="google-logo">
                            Login with Google
                    </button>                                    
                    </div>  
                </a>
                
                <a class="d-block text-center mt-2 small" href="signUp.html">Don't have account? Sign up now!</a>
                <br />
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

  <script src="js/login.js"></script>
</body>

</html>
