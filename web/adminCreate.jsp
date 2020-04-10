<%@ page import="be.intecbrussel.controller.MyController" %>
<%@ page import="be.intecbrussel.entity.Client" %>
<%@ page import="java.util.List" %>
<%@ page import="be.intecbrussel.entity.Account" %>
<%--
  Created by IntelliJ IDEA.
  User: HP6730b
  Date: 3/24/2020
  Time: 11:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link rel="stylesheet" href="contents/css/style.css">
    <link rel="stylesheet" href="../css/styleAnimation.css">
    <link rel="stylesheet" href="../css/styleAnimation.css">
    <title>Welcome to Administration - CREATE </title>
    <%-- This to clear the cashe  after the user logoff--%>
    <%
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        if(session.getAttribute("username")==null){
            response.sendRedirect("index.jsp");
        }

    %>

</head>
<body>
<center>
    <div class="login-wrap">
        <div class="login-html">
            <div class="welcome"> WELCOME <br> <br> ${username} </div>
            <br>
            <label for="tab-1" class="tab ">
                <a id="tab-1" href="adminCreate.jsp">CREATE</a></label>
            <label for="tab-2" class="tab ">
                <a id="tab-2" href="adminUpdate.jsp">UPDATE</a></label>
            <label for="tab-3" class="tab ">
                <a id="tab-3" href="adminDelete.jsp">DELETE</a></label>
            <div class="login-form">
                <%-- Create section >--%>
                    <form action="create" method="post" >
                        <div class="create-htm" class="animated infinite pulse delay-3s">
                            <div class="group">
                                <label for="myuser" class="label">Username</label>
                                <input id="myuser" type="text" class="input" name="username"
                                       onchange="return checkusername()">
                            </div>
                            <div class="group">
                                <label for="fname" class="label">First Name</label>
                                <input id="fname" type="text" class="input" name="fname" >
                            </div>
                            <div class="group" class="fadeInRightBig">
                                <label for="lname" class="label">Last Name</label>
                                <input id="lname" type="text" class="input" name="lname" >
                            </div>

                            <div class="group">
                                <label for="pass1" class="label">Password</label>
                                <input id="pass1" type="password" class="input" data-type="double" name="password" >
                            </div>
                            <div class="group">
                                <label for="email" class="label">Email Address</label>
                                <input id="email" type="text"  placeholder="user@example.com"class="input" name="email"
                                       onchange="return checkemail()">
                            </div>
                            <div class="group">
                                <label for="amount" class="label" onkeypress="return isNumber(event)">Amount</label>
                                <input id="amount" type="text" class="input" data-type="float" name="amount">
                            </div>
                            <div class="group">
                                <input type="submit" class="button" value="create" >
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

    <form action="logout">
        <input type="submit" value="Logout">
    </form>
</center>

<%-- Show section >--%>

<script src="contents/js/main.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="alert/dist/sweetalert-dev.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script src="http://code.jquery.com/jquery-latest.js "></script>

</body>
</html>
