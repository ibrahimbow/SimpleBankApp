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
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link rel="stylesheet" href="style.css">
    <title>ok</title>

    <script type="text/javascript">
        function validate()
        {
            if( document.getElementById("pass").value === document.getElementById("pass2").value )
            {
                JSalert();
                location.href="welcome.jsp";
            }
            else
            {
                alert( "validation failed" );

            }
        }
    </script>

    <%--created account success alert--%>
    <script src="alert/dist/sweetalert-dev.js"></script>

    <script type="text/javascript">
        function JSalert(){
            swal("Congrats!", ", Your account is created!", "success");
        }
    </script>

</head>
<body>



    <div class="login-wrap">
        <div class="login-html">
            <input id="tab-1" type="radio" name="tab" class="sign-in" checked><label for="tab-1" class="tab">Sign In</label>
            <input id="tab-2" type="radio" name="tab" class="sign-up"><label for="tab-2" class="tab">Sign Up</label>
            <div class="login-form">
         <form action="login">
                <div class="sign-in-htm">
                    <div class="group">
                        <label for="user" class="label">Username</label>
                        <input id="username" type="text" class="input" name="username">
                    </div>
                    <div class="group">
                        <label for="password" class="label">Password</label>
                        <input id="password" type="password" class="input" data-type="password" name="pwd">
                    </div>
                    <div class="group">
                        <input id="check" type="checkbox" class="check" checked>
                        <label for="check"><span class="icon"></span> Keep me Signed in</label>
                    </div>
                    <div class="group">
                        <input type="submit" class="button" value="Sign In" >
                        <!--					<a href="showCities">Show cities</a></input>-->
                    </div>
                    <div class="hr"></div>
                    <div class="foot-lnk">
                        <a href="#forgot">Forgot Password?</a>
                    </div>
                </div>
         </form>

         <form action="register" method="post">
        <div class="sign-up-htm">
            <div class="group">
                <label for="user" class="label">Username</label>
                <input id="user" type="text" class="input" name="username">
            </div>
            <div class="group">
                <label for="fname" class="label">First Name</label>
                <input id="fname" type="text" class="input" name="fname">
            </div>
            <div class="group">
                <label for="lname" class="label">Last Name</label>
                <input id="lname" type="text" class="input" name="lname">
            </div>

            <div class="group">
                <label for="pass1" class="label">Password</label>
                <input id="pass1" type="password" class="input" data-type="password" name="password">
            </div>
            <div class="group">
                <label for="pass2" class="label">Repeat Password</label>
                <input id="pass2" type="password" class="input" data-type="password" name="passR">
            </div>
            <div class="group">
                <label for="email" class="label">Email Address</label>
                <input id="email" type="text" class="input" name="email">
            </div>
            <div class="group">
                <input type="submit" class="button" value="Sign Up" onclick=" validate()">
            </div>
            <div class="hr"></div>
            <div class="foot-lnk">
<%--                <label for="tab-1">Already Member?</label>--%>
            </div>
        </div>

    </form>

            </div>
        </div>
    </div>


</body>
</html>
