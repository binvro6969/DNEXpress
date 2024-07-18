<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- CDN Font Awesome Link -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" 
              integrity="sha512-9usAa10IRO0HhonpyAIVpjrylPvoDwiPUiKdWk5t3PyolY1cOd4DSE0Ga+ri4AuTroPR5aQvXU9xC6qOPnzFeg==" 
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <!-- CSS Stylesheet Link -->
        <link href="css/rating_driver.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" type="text/css" href="css/footer.css">
        <title>DN EXPRESS</title>
    </head>
    <body>

        <section class="confiq">
            <!-- SIDEBAR -->
            <%@include file="/include/sidebar_cus.jsp" %>
            <!-- SIDEBAR -->
            <!-- CONTENT -->
            <section id="content">
                <!-- NAVBAR -->
                <nav>
                    <i class='bx bx-menu' ></i>
                    <a href="#" class="nav-link" ><strong>Rating Driver</strong></a>
                    <form action="#">
                        <div class="form-input">
                            <input type="search" placeholder="Enter driver Id">
                            <button type="submit" class="search-btn"><i class='bx bx-search' ></i></button>
                        </div>
                    </form>
                    <input type="checkbox" id="switch-mode" hidden>
                    <label for="switch-mode" class="switch-mode"></label>
                    <a href="#" class="notification">
                        <i class='bx bxs-bell' ></i>
                        <span class="num">8</span>
                    </a>
                    <a href="#" class="profile">
                        <img src="img/img2.jpg">
                    </a>
                </nav>
                <!-- NAVBAR -->

                <!-- MAIN -->
                <main class="table__body" id="customers_table">

                    <section class="container">
                        <div class="post">
                            <div class="text">Thanks for rating us!</div>

                        </div>
                        <!--                        <div class="image-container">
                                                    <img src="images/scooter.png" alt="Scooter Image"/>
                                                </div>-->
                        <div class="header">
                            <div class="text">Rating your driver</div>
                        </div>
                        <div class="star-widget">

                            <form action="Customer_RatingDriver_Servlet" method="post">
                                <input type="hidden" name="orderID" value="${param.orderID}">
                                <input type="radio" name="rate" id="rate-5" value="5">
                                <label for="rate-5" class="fas fa-star"></label>
                                <input type="radio" name="rate" id="rate-4" value="4">
                                <label for="rate-4" class="fas fa-star"></label>
                                <input type="radio" name="rate" id="rate-3" value="3">
                                <label for="rate-3" class="fas fa-star"></label>
                                <input type="radio" name="rate" id="rate-2" value="2">
                                <label for="rate-2" class="fas fa-star"></label>
                                <input type="radio" name="rate" id="rate-1" value="1">
                                <label for="rate-1" class="fas fa-star"></label>
                                <div class="textarea">
                                    <textarea cols="40" name="feedback" placeholder="Describe your experience"></textarea>
                                </div>
                                <div class="btn">
                                    <button type="submit">Rate</button>
                                </div>
                            </form>
<!--                            <p>${msg}</p>
                            <c:set var="msg" value="${null}" scope="session"></c:set>-->
                        </div>

                    </section>

                </main>
                <!-- MAIN -->
            </section>
        </section>
        <!-- CONTENT -->

        <%@include file="/include/footer.jsp" %>

        <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
        <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
        <script src="js/rating_driver.js" type="text/javascript"></script>

    </body>
</html>
