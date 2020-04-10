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
    <title>Welcome to Administration</title>
    <%-- This to clear the cashe  after the user logoff--%>
    <%
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        if(session.getAttribute("username")==null){
            response.sendRedirect("index.jsp");
        }
        MyController myController = new MyController();
        int search_BankAccount_for_update=0;

        if( session.getAttribute("findBankAccountNumber")!=null){
            search_BankAccount_for_update = (Integer) request.getSession().getAttribute("findBankAccountNumber");
            request.getSession().setAttribute("l_nameUpdate", myController.findByBankAccountNumber(search_BankAccount_for_update).getClient().getUsername());
        }



    %>

    <script>
        setInterval("my_function();",1000);
        function my_function(){
            $('#refresh').load(location.href + ' #currentAmountNow');
        }

    </script>




</head>
<body>
<center><br> <br>
    <h2>
        Welcome<h2 > ${username}</h2>
    </h2>
    <span id="message"></span>



    <div class="login-wrap" >
        <div class="login-html">
            <div class="admin-html">
                <input id="tab-1" type="radio" name="tab" class="create"><label for="tab-1" class="tab ">CREATE</label>
                <input id="tab-2" type="radio" name="tab" class="update"><label for="tab-2" class="tab">update</label>
                <input id="tab-3" type="radio" name="tab" class="delete"><label for="tab-3" class="tab ">delete</label>

                <div class="admin-form">

                    <%-- Create section >--%>
                    <form action="create" method="post" >
                        <div class="create-htm" class="animated infinite pulse delay-3s">
                            <div class="group">
                                <label for="user" class="label">Username</label>
                                <input id="user" type="text" class="input" name="username">
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
                                <input id="email" type="text"  placeholder="user@example.com"class="input" name="email">
                            </div>
                            <div class="group">
                                <label for="amount" class="label">Amount</label>
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


                    <%-- Update section >--%>





                        <form >
                            <div class="update-htm">
<%--                                <form name="updateuser"  onkeypress="return isNumber(event)">--%>
                                    <div class="update-htm" style="height: 20px;">
                                        <input id="number1" type="text" name="findBankAccountNumberUpdate" class="inputUpdate"
                                               onchange="return searchAccountNumber()"
                                               onkeypress="return isNumber(event)"><br>
<%--                                        <input type="submit" class="buttonTransaction" value="search">--%>
                                    </div>

                                <br><br><br><br>
                                <div class="group" id="refresh1">
                                        <div id="up1" >
                                    <label for="username_Update" class="label">Username</label>
                                    <input id="username_Update" type="text" class="input" name="usernameUpdate1" value="${usernameUpdate}" >
                                </div>
                                </div>

                                <div class="group" id="refresh2">
                                    <div id="up2" >
                                    <label for="fnameUpdate" class="label">First Name</label>
                                    <input id="fnameUpdate" type="text" class="input" name="f_nameUpdate1" value="${f_nameUpdate}">
                                </div>
                                </div>


                                <div class="group" id="refresh3">
                                    <div id="up3" >
                                    <label for="lnameUpdate" class="label">Last Name</label>
                                    <input id="lnameUpdate" type="text" class="input" name="l_nameUpdate1" value="${l_nameUpdate}" >
                                </div>
                                </div>


                                <div class="group" id="refresh4">
                                    <div id="up4" >
                                    <label for="pass1Update" class="label">Password</label>
                                    <input id="pass1Update" type="text" class="input" data-type="password" name="passwordUpdate1" value="${passwordUpdate}" >
                                </div>
                                </div>

                                <div class="group" id="refresh5">
                                    <div id="up5" >
                                    <label for="emailUpdate" class="label">Email Address</label>
                                    <input id="emailUpdate" type="text" class="input" name="emailUpdate1"  value="${emailUpdate}">
                                </div>
                                </div>

                                <div class="group" id="refresh6">
                                    <div id="up6" >
                                    <label for="amountUpdate" class="label">Amount</label>
                                    <input id="amountUpdate" type="text" class="input" data-type="float" name="amountUpdate1" value="${amountUpdate}">
                                </div>
                                </div>
                                <input type="submit" class="button" value="update" onclick="return clearAfterUpdate()">
                                <div class="hr"></div>
                                <div class="foot-lnk">
                                    <%--                <label for="tab-1">Already Member?</label>--%>
                                </div>
                            </div>
                        </form>


                    <%-- Delete section >--%>
                    <form action="delete" method="get">
                        <div class="delete-htm">
                            <div class="group">
                                <label for="bankAccountNumberDelete" class="label">Bank Account Number :</label>
                                <input id="bankAccountNumberDelete" type="text" class="input" name="findBankAccountNumberToDeleteIt">
                                your account is <h2 class="animated bounceInDown delay-3s"> ${deleted}</h2>
                            </div>
                            <div class="button"><input type="submit" value="Delete"></div>
                        </div>

                    </form>

                </div>
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
