<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>DN EXPRESS</title>


        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
        <script src="js/jquery-1.11.0.min.js"></script>


        <link rel="stylesheet" type="text/css" href="css/redirecting_style.css">
        <script src="js/vnpay_post.js"></script>



    </head>

    <body class="body_redirect">

        <!-- Navigation Div Starts -->
        <main>

        </main>
        <section class="confiq">

            <!-- CONTENT -->
            <section id="content">
                <!-- Navigation Div Starts -->

                <!-- Hero Section Starts -->
                <main class="forgot-pass-main d-flex justify-content-center">
                    <section class="forgot_pass_form col-md-5">
                        <div class="card mt-5 border-0 shadow rounded-3">
                            <div class="container">
                                <div class="card-body text-center">

                                    <div class="loader-wrapper">
                                        <span class="loader"><span class="loader-inner"></span></span>
                                    </div>

                                    <h2 class="card-title ">Redirecting...</h2>
                                    <p>Please wait a couple of seconds</p>
                                    <!-- Form to be automatically submitted -->
                                    <form id="frmCreateOrder" action="Svl_Payment_Servlet" method="post">
                                        <!-- Include your form fields here -->
                                        <input type="hidden" name="total-value" value="${totalMoney}">
                                        <!-- Other form fields -->
                                        <button type="submit" style="display: none;">Submit Order</button> <!-- Hide the submit button -->
                                    </form>


                                </div>
                            </div>

                        </div>
                    </section>

                </main>
            </section>
        </section>

        <!-- Footer Section Starts -->

        <!-- Scripts  Starts -->

    </body>

</html>