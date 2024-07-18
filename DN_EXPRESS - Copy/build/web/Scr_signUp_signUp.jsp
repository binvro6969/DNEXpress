<%-- 
    Document   : Scr_signUp_signUp
    Created on : Jun 17, 2024, 11:18:57 PM
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

  <link rel="stylesheet" type="text/css" href="css/sign_up_style.css">
  <link rel="stylesheet" type="text/css" href="include/footer.css">
  <link rel="stylesheet" type="text/css" href="include/navbar_landing_page.css">


  <!-- script ================================================== -->
  <script src="js/modernizr.js"></script>
    <style>
        .error-message {
            color: red;
            font-size: 0.875em;
        }
    </style>
</head>

<body class="body_signUp" data-bs-spy="scroll" data-bs-target="#navbar-example2" tabindex="0">

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
        <section class=" sign_up_form col-md-6">
          <div class="card mt-5 border-0 shadow rounded-3">
            <div class="container">
              <div class="card-body my-2 col-md-10">
                <h1 class="card-title text-center ">Sign Up</h1>
                
                 <form id="signupForm" action="Authentication_SignUp_Servlet" method="post">
        <div class="row">
            <div class="col-md-6">
                <div class="form-floating mb-3">
                    <input type="text" name="fname" class="form-control" id="firstname" placeholder="myusername" required autofocus>
                    <label for="firstname">First Name <span class="required"></span></label>
                    <div id="firstnameError" class="error-message"></div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="form-floating mb-3">
                    <input type="text" name="lname" class="form-control" id="lastname" placeholder="myusername" required autofocus>
                    <label for="lastname">Last Name <span class="required"></span></label>
                    <div id="lastnameError" class="error-message"></div>
                </div>
            </div>
        </div>
        <div class="form-floating mb-3">
            <input type="tel" name="phone_numb" class="form-control" id="phoneNumber" placeholder="myusername" required autofocus>
            <label for="phoneNumber">Phone Number <span class="required"></span></label>
            <div id="phoneError" class="error-message"></div>
        </div>
        <div class="form-floating mb-3">
            <input type="email" name="email" class="form-control" id="floatingInputUsername" placeholder="myusername" required autofocus>
            <label for="floatingInputUsername">Email <span class="required"></span></label>
            <p class="text-danger">${message}</p>
        </div>
        <div class="form-floating mb-3">
            <input type="password" name="password" class="form-control" id="password" placeholder="myusername" required autofocus>
            <label for="password">Password <span class="required"></span></label>
            <div id="passwordError" class="error-message"></div>
        </div>
        <div class="form-floating mb-3">
            <input type="password" name="confirm_password" class="form-control" id="confirmPassword" placeholder="myusername" required autofocus>
            <label for="confirmPassword">Confirm password <span class="required"></span></label>
            <div id="confirmPasswordError" class="error-message"></div>
        </div>
        <div class="d-grid mb-2">
            <button class="btn btn-lg btn-primary btn-login fw-bold text-uppercase" type="submit">Sign Up</button>
        </div>
        <a class="d-block text-center mt-2 small" href="login.html">Already have account? Login now!</a>
        <br />
    </form>
    <script>
        document.getElementById('signupForm').addEventListener('submit', function(event) {
            let valid = true;

            // Validate first name
            const firstname = document.getElementById('firstname').value;
            const firstnameError = document.getElementById('firstnameError');
            const namePattern = /^[A-Za-z]+$/;
            if (!firstname || !namePattern.test(firstname)) {
                firstnameError.textContent = 'First name is required and must contain only letters.';
                valid = false;
            } else {
                firstnameError.textContent = '';
            }

            // Validate last name
            const lastname = document.getElementById('lastname').value;
            const lastnameError = document.getElementById('lastnameError');
            if (!lastname || !namePattern.test(lastname)) {
                lastnameError.textContent = 'Last name is required and must contain only letters.';
                valid = false;
            } else {
                lastnameError.textContent = '';
            }

            // Validate phone number
            const phoneNumber = document.getElementById('phoneNumber').value;
            const phoneError = document.getElementById('phoneError');
            const phonePattern = /^0\d{9}$/;
            if (!phonePattern.test(phoneNumber)) {
                phoneError.textContent = 'Phone number must be 10 digits starting with 0.';
                valid = false;
            } else {
                phoneError.textContent = '';
            }

            // Validate password
            const password = document.getElementById('password').value;
            const passwordError = document.getElementById('passwordError');
            if (password.length < 8) {
                passwordError.textContent = 'Password must be at least 8 characters long.';
                valid = false;
            } else {
                passwordError.textContent = '';
            }

            // Validate confirm password
            const confirmPassword = document.getElementById('confirmPassword').value;
            const confirmPasswordError = document.getElementById('confirmPasswordError');
            if (password !== confirmPassword) {
                confirmPasswordError.textContent = 'Passwords do not match.';
                valid = false;
            } else {
                confirmPasswordError.textContent = '';
            }

            if (!valid) {
                event.preventDefault();
            }
        });
    </script>
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

  <script src="js/sign_up.js"></script>
</body>

</html>
