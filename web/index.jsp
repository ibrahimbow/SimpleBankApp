<%--
  Created by IntelliJ IDEA.
  User: HP6730b
  Date: 3/24/2020
  Time: 11:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html" lang="">
<head>
    <title>Bank-Intecbrussel</title>

    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <link rel="stylesheet" href="contents/css/style.css">
    <link rel="stylesheet" href="contents/css/style_popup.css">
    <link rel="stylesheet" href="contents/css/styleAnimation.css">
    <link rel="stylesheet" href="contents/css/styleAnimation.css">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
    <link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.0.10/css/all.css'>

    <script src="contents/js/main.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="alert/dist/sweetalert-dev.js"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

</head>
<body>
    <div class="login-wrap">
        <div class="login-html">
            <input id="tab-1" type="radio" name="tab" class="sign-in" checked><label for="tab-1" class="tab"  >Sign In</label>
            <input id="tab-2" type="radio" name="tab" class="sign-up "><label for="tab-2" class="tab" >Sign Up</label>
            <div class="login-form animated fadeIn">
         <form action="login" id="loginId" >
                <div class="sign-in-htm">
                    <div class="group">
                        <br><br>
                        <label for="loginUser" class="label">Username</label>
                        <div class ="inputWithIconLogin">
                            <i class="fas fa-user icon"  ></i>
                        <input id="loginUser" type="text" class="inputLogin" name="username" onchange="return checkUserNameLogin()">
                        </div>
                    </div>
                    <div class="group">
                        <label for="password" class="label">Password</label>
                        <div class ="inputWithIconLogin">
                            <i class="fas fa-key icon"></i>
                        <input id="password" type="password" class="inputLogin" data-type="password" name="pwd">
                        </div>
                    </div>

                    <div class="group">
                        <br>
                        <button type="submit" class="buttonClick button"  >LOG IN</button>
                    </div>
                    <div class="hr"></div>
                    <div class="foot-lnk">
                        <span>EVERYONE IS RICH</span>
                    </div>
                </div>
         </form>

                <form id="myformReg">
                    <div class="sign-up-htm animated fadeIn">
                        <br><br>
                        <div class="group">
                            <label for="myuserReg" class="label">Username</label>
                            <input id="myuserReg" type="text" class="input" name="username"
                                   onchange="javascript: return check_username()">
                            <span id="resultReg"></span>
                        </div>
                        <div class="group">
                            <label for="fname" class="label">First Name</label>
                            <input id="fname" type="text" class="input" name="fname" required  />
                        </div>
                        <div class="group">
                            <label for="lname" class="label">Last Name</label>
                            <input id="lname" type="text" class="input" name="lname" required />
                        </div>
                        <div class="group">
                            <label for="password_register" class="label">Password</label>
                            <input id="password_register"  class="input"  name="password_register" required />
                        </div>
                        <div class="group">
                            <label for="email" class="label">Email Address</label>
                            <input id="email" type="email" class="input" name="email" placeholder="email@email.com"
                                   onchange="javascript:return check_email()" required />
                        </div>

                        <br>
                        <div class="group">
                            <input  id="registerId" type="submit" class="button" value="register" onclick=" return Register_new_client()" >
                        </div>
                    </div>
                </form>

            </div>
        </div>
    </div>


</body>
</html>

