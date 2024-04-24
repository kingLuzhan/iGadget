<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Footer</title>
    
    <style> 
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: "Poppins", sans-serif;
        }

        h1 {
            font-size: 50px;
            line-height: 64px;
            color: #222;
        }

        h2 {
            font-size: 46px;
            line-height: 54px;
            color: #222;
        }

        h4 {
            font-size: 20px;
            color: #222;
        }

        h6 {
            font-size: 12px;
            font-weight: 700;
        }

        p {
            font-size: 16px;
            color: #000000;
            margin: 15px 0 20px 0;
        }

        .section-p1 {
            padding: 40px 80px;
        }

        .section-m1 {
            margin: 40px 0;
        }

        button.normal {
            font-size: 14px;
            font-weight: 600;
            padding: 15px 30px;
            color: black;
            background-color: white;
            border-radius: 4px;
            cursor: pointer;
            border: none;
            outline: none;
            transition: 0.2s;
        }

        #newsletter {
            display: flex;
            justify-content: space-between;
            flex-wrap: wrap;
            align-items: center;
            background-image: url("../img/banner/b14.png");
            background-repeat: no-repeat;
            background-position: 20% 30%;
            background-color: #041e42;
        }

        #newsletter h4 {
            font-size: 22px;
            font-weight: 700;
            color: white;
        }

        #newsletter p {
            font-size: 14px;
            font-weight: 600;
            color: #818ea0;
        }

        #newsletter p span {
            color: #ffbd27;
        }

        #newsletter form {
            display: flex;
            width: 100%;
        }

        #newsletter input {
            height: 3.125rem;
            padding: 0 1.25em;
            font-size: 14px;
            width: 100%;
            border: 1px solid transparent;
            border-radius: 4px;
            outline: none;
            border-top-right-radius: 0;
            border-bottom-right-radius: 0;
            border-top-left-radius: 0;
            border-bottom-right-radius: 0;
        }

        #newsletter button {
            background-color: #088178;
            color: white;
            white-space: nowrap;
        }

        footer {
            display: flex;
            flex-wrap: wrap;
            justify-content: space-between;
        }

        footer .col {
            display: flex;
            flex-direction: column;
            align-items: flex-start;
            margin-bottom: 20px;
        }

        footer .logo {
            margin-bottom: 30px;
        }

        footer h4 {
            font-size: 14px;
            padding-bottom: 20px;
        }

        footer p {
            font-size: 13px;
            margin: 0 0 8px 0;
            color: #1a1a1a;
        }

        footer a {
            font-size: 13px;
            text-decoration: none;
            color: black;
            margin-bottom: 10px;
        }

        footer .follow {
            margin-top: 20px;
        }

        footer .follow i {
            color: #465b52;
            padding-right: 4px;
            cursor: pointer;
        }

        footer .follow i:hover,
        footer a:hover {
            color: #088178;
        }

        footer .copyright {
            width: 2000px;
            text-align: center;
        }
    </style>

</head>
<body>
    
    <section id="newsletter" class="section-p1 section-m1">
        <div class="newstext">
            <h4>Sign Up for Newsletter</h4>
            <p>Get E-mail updates about our latest products and <span>special offers</span></p>
        </div>
        <div class="form">
            <form action="https://formspree.io/f/mayzylye" method="POST">
                <input type="email" name="email" placeholder="Your Email address">
                <button class="normal" name="Submit">Sign Up</button>
            </form>
        </div>
    </section>
    <footer class="section-p1">
        <div class="col">
            <img class="logo" src="logo.png" alt="" height="120">
            <h4>Contact</h4>
            <p><strong>Address:</strong> Pokhara, Nepal</p>
            <p><strong>Phone:</strong> +977 9829174533/ +977 9847730555</p>
            <p><strong>Email:</strong> igadget@gmail.com</p>
            <p><strong>Hours:</strong> 09:00AM - 09:00PM, Sun - Fri</p>
            <div class="follow">
                <h4>Follow us</h4>
                <div class="icon">
                  <!-- facebook icon -->
                  <a href="https://www.facebook.com/profile.php?id=100085433096061" target="_blank"> <i class="fa-brands fa-facebook-square"></i></a>
                  <!-- instagram icon -->
                  <a href="https://www.instagram.com/rachit__shrestha/" target="_blank"> <i class="fa-brands fa-instagram"></i></a>
                  <!-- instagram icon -->
                  <a href="https://www.instagram.com/achyut_tamang_/" target="_blank"> <i class="fa-brands fa-instagram"></i></a>
                  <!-- twitter icon -->
                  <a href="https://twitter.com/raj_ghale_" target="_blank"> <i class="fa-brands fa-twitter"></i></a>
                  <!-- pinterest icon -->
                  <a href="https://www.pinterest.com/rachitshrestha/" target="_blank"> <i class="fa-brands fa-pinterest"></i></a>
                </div>
              </div>
        </div>
        <div class="col">
            <h4>Quick Links</h4>
            <a href="products.html">Products</a>
            <a href="blog.html">Blog</a>
            <a href="about.html">About us</a>
            <a href="research.html">Research</a>
            <a href="loginPage.html">Log In</a>
        </div>
        <div class="col install">
            <h4>Install App</h4>
            <p>Coming Soon</p>
        </div>
        <div class="copyright">
            <p class="size"> ©2023 , iGadget </p>
        </div>
    </footer>
    <script src="script.js"></script>
</body>
</html>
