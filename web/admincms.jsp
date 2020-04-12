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


<%--    <link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.0.10/css/all.css'>--%>




    <link rel="stylesheet" href="contents/css/style_admin.css">
    <link rel="stylesheet" href="contents/css/style.css">
    <link rel="stylesheet" href="contents/css/style_popup.css">
    <link rel="stylesheet" href="contents/css/styleAnimation.css">
    <link rel="stylesheet" href="contents/css/styleAnimation.css">


    <title>Welcome to Administration</title>
</head>
<body>

<div class="tabContainer">
    <div class="header-html">
        <div class="logoAdmin"> logo</div>
        <div class="adminName">Admin ${username} </div>
    </div>

        <div class="left-html ">
            <div class="buttonContainer">
                <nav>
                    <a onclick="showPanel(0,'#4caf50')" ><i class="fa fa-user"></i> Client Info</a>
                    <a onclick="showPanel(1,'#2196f3')"><i class="fa fa-credit-card"></i>Add New Client</a>
                    <a onclick="showPanel(2,'#ff5722')"><i class="fa fa-tv"></i>Edit & Update</a>
                    <a onclick="showPanel(3,'#f44336')"><i class="fa fa-tasks"></i> Transactions</a>
                    <a onclick="showPanel(4,'#123')"><i class="fa fa-cog"></i>Logs</a>
                </nav>
            </div>

        </div>
        <div class="right_content-html">
            <div class="tabPanel">Tab 1:Content</div>
            <div class="tabPanel">Create

                <form>
                    <div class="sign-up-htm">
                        <div class="group">
                            <label for="myuser" class="label">Username</label>
                            <input id="myuser" type="text" class="input" name="username" onchange="javascript:return checkusername()"><span id="result" name="result"></span>
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
                            <label for="password_register" class="label">Password</label>
                            <input id="password_register" type="password" class="input" data-type="password" name="password_register">
                        </div>
                        <div class="group" >
                            <label for="confirm_password" class="label">Repeat Password</label>
                            <input id="confirm_password" type="password" class="input" data-type="password" name="confirm_password"/>
                            <%--                <span class="popuptext" id='messageTrue'> Match</span>--%>
                            <%--                <span class="popuptext" id='messageFalse'> Not Match</span>--%>
                        </div>
                        <div class="group">
                            <label for="email" class="label">Email Address</label>
                            <input id="email" type="text" class="input" name="email" placeholder="email@email.com"
                                   onchange="javascript:return checkemail()">
                            <span id="result1" name="result1" ></span>
                        </div>
                        <div class="group">
                            <label for="amountCreate" class="label">Email Address</label>
                            <input id="amountCreate" type="text" class="input" name="amountCreateN" placeholder="0.0"
                                   onchange="javascript:return checkemail()">
                        </div>
                        <br>
                        <div class="group">
                            <input type="submit" class="button" value="Add" onclick=" return create_new_client()">
                        </div>
                    </div>
                </form>

            </div>

            <div class="tabPanel">EDIT & DELETE

                <form >
                        <div  style="height: 20px;">
                            <input id="number1" type="text" name="findBankAccountNumberUpdate" class="inputUpdate"
                                   onchange="return searchAccountNumber()"
                                   onkeypress="return isNumber(event)"><br>
                        </div>

                        <br><br><br><br>
                            <div  id="refresh1">
                            <div id="up1" >
                                <label for="username_Update" class="label">Username</label>
                                <input id="username_Update" type="text" class="input" name="usernameUpdate1" value="${usernameUpdate}" >
                            </div>
                        </div>
                            <div  id="refresh2">
                            <div id="up2" >
                                <label for="fnameUpdate" class="label">First Name</label>
                                <input id="fnameUpdate" type="text" class="input" name="f_nameUpdate1" value="${f_nameUpdate}">
                            </div>
                        </div>

                            <div  id="refresh3">
                            <div id="up3" >
                                <label for="lnameUpdate" class="label">Last Name</label>
                                <input id="lnameUpdate" type="text" class="input" name="l_nameUpdate1" value="${l_nameUpdate}" >
                            </div>
                        </div>

                            <div id="refresh4">
                            <div id="up4" >
                                <label for="pass1Update" class="label">Password</label>
                                <input id="pass1Update" type="text" class="input" data-type="password" name="passwordUpdate1" value="${passwordUpdate}" >
                            </div>
                        </div>

                            <div  id="refresh5">
                            <div id="up5" >
                                <label for="emailUpdate" class="label">Email Address</label>
                                <input id="emailUpdate" type="text" class="input" name="emailUpdate1"  value="${emailUpdate}">
                            </div>
                        </div>

                            <div id="refresh6">
                            <div id="up6" >
                                <label for="amountUpdate" class="label">Amount</label>
                                <input id="amountUpdate" type="text" class="input" data-type="float" name="amountUpdate1" value="${amountUpdate}">
                            </div>
                        </div>

                            <input type="submit" class="button" value="update" onclick=" return update_delete_client()">
                </form>

            </div>

            <div class="tabPanel">Tab 4:Content</div>
            <div class="tabPanel">Tab 5:Content</div>

        </div>
</div>


<%-- Show section >--%>

<script src="contents/js/main.js"></script>
<script src="contents/js/myScript.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="alert/dist/sweetalert-dev.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<%--<script src="http://code.jquery.com/jquery-latest.js "></script>--%>





</body>
</html>


