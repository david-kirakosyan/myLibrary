<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Register</title>

    <!-- Font Icon -->
    <link rel="stylesheet" href="register/fonts/material-icon/css/material-design-iconic-font.min.css">

    <!-- Main css -->
    <link rel="stylesheet" href="register/css/style.css">
</head>
<body>
<% String msg = (String) request.getAttribute("msg"); %>
<div class="main">

    <!-- Sign up form -->
    <section class="signup">
        <div class="container">
            <div class="signup-content">
                <div class="signup-form">
                    <h2 class="form-title">Sign up</h2>
                    <form action="/register" method="post" class="register-form" id="register-form">
                        <% if (msg != null) {%>
                        <samp style="color: red"><%=msg%>
                        </samp>
                        <%}%>
                        <div class="form-group">
                            <label><i class="zmdi zmdi-account material-icons-name"></i></label>
                            <input type="text" name="name" placeholder="Your Name" required/>
                        </div>
                        <div class="form-group">
                            <label><i class="zmdi zmdi-account material-icons-name"></i></label>
                            <input type="text" name="surname" placeholder="Your Surname" required/>
                        </div>
                        <div class="form-group">
                            <label><i class="zmdi zmdi-email"></i></label>
                            <input type="email" name="email" placeholder="Your Email" required/>
                        </div>
                        <div class="form-group">
                            <label><i class="zmdi zmdi-lock"></i></label>
                            <input type="password" name="password" placeholder="Password" required/>
                        </div>
                        <div class="form-group">
                            <label for="user"><i class="zmdi zmdi-account material-icons-name">USER</i></label>
                            <input type="radio" id="user" name="type" value="USER">
                        </div>
                        <div class="form-group">
                            <label for="admin"><i class="zmdi zmdi-account material-icons-name">ADMIN</i></label>
                            <input type="radio" id="admin" name="type" value="ADMIN">
                        </div>
                        <div class="form-group form-button">
                            <input type="submit" name="signup" id="signup" class="form-submit" value="Register"/>
                        </div>
                    </form>
                    <div class="form-group">
                        <input type="checkbox" name="agree-term" id="agree-term" class="agree-term"/>
                        <label for="agree-term" class="label-agree-term"> <a href="/"
                                                                             class="term-service">Login</a></label>
                    </div>
                </div>
                <div class="signup-image">
                    <figure><img src="register/images/signup-image.jpg" alt="sing up image"></figure>
                </div>
            </div>
        </div>
    </section>
</div>
</body>
</html>