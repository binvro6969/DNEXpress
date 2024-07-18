<%-- 
    Document   : landing_page
    Created on : Jun 17, 2024, 2:50:48 PM
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

  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.css" />
  <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>

  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">

  <link rel="stylesheet" type="text/css" href="css/landing_page_style.css">
  <link rel="stylesheet" type="text/css" href="css/footer.css" >
  <link rel="stylesheet" type="text/css" href="css/navbar_landing_page.css">

  <link href="https://fonts.googleapis.com/css2?family=Jost&family=Roboto:wght@400;700&display=swap" rel="stylesheet">
  <script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
  <script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>


</head>

<body data-bs-spy="scroll" data-bs-target="#navbar-example2" tabindex="0">

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

      <main id="home">
        <!-- Hero Section Starts -->
        <div class="carousel">
          <div class="list">
            <div class="item">
              <img class="img_carousel" src="img/bg.jpg" alt="">
              <div class="content">
                <div class="title">
                  DN EXPRESS
                </div>
                <div class="topic">
                  Fast and Safe
                </div>
              </div>
            </div>
          </div>
        </div>


        <section class="sec-1 show-animate" id="hero">
          <div class="hero container">
            <div class="col-md-10 d-flex align-items-center">
              <div class="hero_title col-md-7 ">
                <h1 class="animate">Extensive transportation throughout 63 provinces <br> and cities</h1>
                <ul class="animate list-unstyled my-5">
                  <li class="my-2">
                    <h5>1. Easily</h5>
                  </li>
                  <li class="my-2">
                    <h5>2. Safely</h5>
                  </li>
                  <li class="my-2">
                    <h5>3. Fastly</h5>
                  </li>
                </ul>
              </div>
              <div class="div_img col-md-5 ">
                <img src="img/logopage1.png" class="animate d-block mx-lg-auto img-fluid" alt="Bootstrap Themes"
                  width="300" height="300" loading="lazy">
              </div>
            </div>

          </div>
        </section>

        <!-- Services Section Starts -->
        <section id="services" class="services sec-2 container">
          <div class="service_title ">
            <h1 class="animate text-center my-5">Types of services</h1>
            <div class="service_component row py-5">
              <div class="animate-1 col-md-3">
                <div class="service-post py-5 px-5">
                  <i class="fa-solid fa-box-open"></i>
                  <h3>Create order</h3>

                  <a href="services-single.html" class="icon-link">More info </a>
                </div>
              </div>
              <div class="animate-2 col-md-3">
                <div class="service-post py-5 px-5">
                  <i class="fa-solid fa-chart-column"></i>
                  <h3>Purchase History</h3>

                  <a href="services-single.html" class="icon-link">More info </a>
                </div>
              </div>
              <div class="animate-3 col-md-3">
                <div class="service-post py-5 px-5">
                  <i class="fa-solid fa-magnifying-glass"></i>
                  <h3>Check order</h3>

                  <a href="services-single.html" class="icon-link">More info </a>
                </div>
              </div>
              <div class="animate-4 col-md-3">
                <div class="service-post py-5 px-5">
                  <i class="fa-solid fa-star-half-stroke"></i>
                  <h3>Feedback services</h3>

                  <a href="services-single.html" class="icon-link">More info </a>
                </div>
              </div>
            </div>
            <div class="animate text-center">
              <a href="services.html" class="btn btn-primary explore-service py-3 px-4 my-5">Explore Services</a>
            </div>

          </div>
        </section>

        <!-- Action Section Starts -->
        <section id="action" class="action">
          <div id="contact" class="container py-5 my-5">

            <div class="row row-cols-1 row-cols-md-2 align-items-md-center g-5 py-5 ">
              <div class="col-md-5 action-column1">
                <div class="row flex-column py-5 px-5">
                  <div class=" ms-3 mb-5 mt-5 ">
                    <h1 class="mt-2">2043</h1>
                    <h5>Active Users</h5>
                  </div>

                  <div class=" ms-3 mb-5 ">
                    <h1>3298</h1>
                    <h5>Orders in 1 month</h5>
                  </div>

                  <div class=" ms-3 mb-5 ">
                    <h1>5 min</h1>
                    <h5 class="mb-2">To create an order</h5>
                  </div>

                </div>
              </div>

              <div class="col-md-7 action-column2 d-flex flex-column align-items-center py-5 px-5">
                <h1 class="action-heading text-center mt-5 py-2 px-5 ">Are you ready to use our service?
                </h1>
                <p class="action-paragraph text-center my-5 px-5 ">Create account now!</p>
                <button type="button" class="btn btn-primary action-button  my-5" data-bs-toggle="modal"
                  data-bs-target="#exampleModal2">Sign Up</button>
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
  <script src="js/jquery-1.11.0.min.js"></script>
  <script src="js/plugins.js"></script>
  <script src="js/script.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
    crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/iconify-icon@1.0.7/dist/iconify-icon.min.js"></script>
  <script src="js/landing_page.js"></script>
  <script src="https://kit.fontawesome.com/d60694e6d9.js" crossorigin="anonymous"></script>
</body>

</html>
