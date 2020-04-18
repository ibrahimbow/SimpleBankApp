<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="be.intecbrussel.controller.MyController" %>
<%@ page import="be.intecbrussel.entity.Client" %>
<%@ page import="java.util.List" %>
<%@ page import="be.intecbrussel.entity.Account" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="be.intecbrussel.entity.TransactionsLog" %>
<%@ page import="be.intecbrussel.entity.LogFile" %>
<%@ page import="java.util.stream.Collectors" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Objects" %>
<%--
  Created by IntelliJ IDEA.
  User: HP6730b
  Date: 3/24/2020
  Time: 11:46 PM
  To change this template use File | Settings | File Templates.
--%>

<html>
<head>
    <title>Welcome to Administration</title>
    <%
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        if(session.getAttribute("username")==null){
            response.sendRedirect("index.jsp");
        }

        MyController myController = new MyController();

    %>


    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.0.10/css/all.css'>
    <link rel="stylesheet" href="contents/css/style_admin.css">
    <link rel="stylesheet" href="contents/css/style.css">
<%--    <link rel="stylesheet" href="contents/css/style_popup.css">--%>
<%--    <link rel="stylesheet" href="contents/css/styleAnimation.css">--%>
<%--    <link rel="stylesheet" href="contents/css/styleAnimation.css">--%>

<%--    <!--===============================================================================================-->--%>
<%--    <link rel="stylesheet" type="text/css" href="contents/vendor/perfect-scrollbar/perfect-scrollbar.css">--%>
<%--    <!--===============================================================================================-->--%>

</head>
<body>

<div class="tabContainer">
    <div class="header-html">
        <div class="logoAdmin"> logo </div>
        <div class="adminName" id="username">Admin ${username}

        </div>
    </div>

    <div class="left-html ">
        <div class="buttonContainer">
            <nav>
                <a onclick="showPanel(0,'#')" ><i class="fa fa-user"></i> Client Info</a>
                <a onclick="showPanel(1,'#')"><i class="fa fa-credit-card"></i>Add New Client</a>
                <a onclick="showPanel(2,'#')"><i class="fa fa-tv"></i>Edit & Update</a>
                <a onclick="showPanel(3,'#')"><i class="fa fa-tasks"></i> Transactions</a>
                <a onclick="showPanel(4,'#')"><i class="fa fa-cog"></i>Logs</a>
                <form action="logoutUser" >    <input type="submit" class="buttonTransaction" value="Logout"> </form>
            </nav>
        </div>

    </div>
    <div class="right_content-html">
        <%--Clients Information Section--%>
        <div class="tabPanel ">Clients information


                <br><br>

            <div class="tablefit">
                <table class="ea_table " >
                    <thead class="tableFixedBG">
                    <tr >
                        <th >Id</th>
                        <th>Account No</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Email</th>
                        <th>Join Date</th>
                        <th>Amount</th>
<%--                        <th>In-Out</th>--%>
<%--                        <th>Login</th>--%>

                    </tr>
                    </thead>
                    <tbody >

                    <%
                        List<Account> clientList = myController.showAllClientsInfo();

                        for (Account client : clientList) {
                            session.setAttribute("id", client.getClient().getId_client());
                            session.setAttribute("accountNumber", client.getAccount_number());
                            session.setAttribute("FirstName", client.getClient().getFirst_name());
                            session.setAttribute("lastName", client.getClient().getLast_name());
                            session.setAttribute("email", client.getClient().getEmail());
                            session.setAttribute("dateOfJoin", client.getClient().getDate_of_join());
                            session.setAttribute("amount", client.getCurrent_balance());
//                            session.setAttribute("Transactions", client.getAccount().getTransactionArrayList().stream().map(TransactionsLog::getAccount).count());
//                            session.setAttribute("logins", client.getAccount().getClient().getLogFileArrayList().stream().map(LogFile::getClient_log).count());
                    %>
                    <tr>
                        <td > ${id}</td>
                        <td >${accountNumber}</td>
                        <td >${FirstName}</td>
                        <td >${lastName}</td>
                        <td > ${email}</td>
                        <td >${dateOfJoin}</td>
                        <td >${amount}</td>
<%--                        <td >${Transactions}</td>--%>
<%--                        <td > ${logins}</td>--%>
                    </tr>
                    <%}%>
                    </tbody>
                </table>

            </div>

</div>

         <%-- Create New Client Section--%>
        <div class="tabPanel">Create
            <div class="login-form">
            <form id="myformReg">
                <div class="createAdminForm">
                    <div class="group">
                        <label for="myuserReg" class="label">Username</label>
                        <input id="myuserReg" type="text" class="input" name="username" onchange="javascript:return checkusername()">

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
                        <input id="password_register"  class="input"  name="password_register">
                    </div>
                    <div class="group">
                        <label for="email" class="label">Email Address</label>
                        <input id="email" type="email" class="input" name="email" placeholder="email@email.com"
                               onchange="javascript:return checkemail()" required />
                        <span id="result1" name="result1" ></span>
                    </div>
                    <div class="group">
                        <label for="amountCreate" class="label">Amount</label>
                        <input id="amountCreate" type="text" class="input" name="amountCreateN" placeholder="0.0"
                               onkeypress="return isNumber(event)">
                    </div>
                    <br>
                    <div class="group">

                        <input type="submit" class="button" value="Add" onclick=" return create_new_client()">
                    </div>
                </div>
            </form>
            </div>
        </div>

        <%--Edit and Delete Client Section--%>
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

                <input type="submit" class="button" value="update" onclick=" return update_client()">
                <br><br><br>
                <input type="submit" class="button" value="Delete"  onclick="return delete_client()">
            </form>

        </div>

        <%--Transaction section--%>
        <div class="tabPanel">Transactions Information



            <br><br>


            <div class="tablefit">
                <table class="ea_table " >
                    <thead class="tableFixedBG">
                    <tr>
                        <th>Id</th>
                        <th>Account No</th>
                        <th>Sender</th>
                        <th>Date Time</th>
                        <th>Receiver</th>
                        <th>Account No</th>
                        <th>Amount</th>
                        <%--                        <th>In-Out</th>--%>
                        <%--                        <th>Login</th>--%>

                    </tr>
                    </thead>
                    <tbody >

                    <%
                        String DATE_FORMATTER= "yyyy-MM-dd  HH:mm:ss";
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);

                        List<TransactionsLog> transactionList = myController.showAllClientsTransactionLog();

                        for (TransactionsLog client : transactionList) {
                            session.setAttribute("id", client.getAccount().getClient().getId_client());
                            session.setAttribute("accountNumber", client.getAccount().getAccount_number());
                            session.setAttribute("sender", client.getAccount().getClient().getFirst_name()
                                    + " " + client.getAccount().getClient().getLast_name());
                            session.setAttribute("dateTime", client.getTransaction_date_time().format(formatter));
                            session.setAttribute("receiver", myController
                                    .findByBankAccountNumber(client.getForm_TO_account_number())
                                    .getClient().getFirst_name());
                            session.setAttribute("accountNo", client.getForm_TO_account_number());
                            session.setAttribute("amount", client.getTransaction_amount());
                    %>
                    <tr>
                        <td > ${id}</td>
                        <td >${accountNumber}</td>
                        <td >${sender}</td>
                        <td >${dateTime}</td>
                        <td > ${receiver}</td>
                        <td >${accountNo}</td>
                        <td >${amount}</td>

                    </tr>
                    <%}%>
                    </tbody>
                </table>

            </div>

        </div>

         <%--Logs for Logins Section--%>
        <div class="tabPanel">Login Information


            <br><br>


            <div class="tablefit">
                <table class="ea_table " >
                    <thead class="tableFixedBG">
                    <tr>
                        <th style="width: 10px;">Id</th>
                        <th>Account No</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Email</th>
                        <th>Login Date</th>

                        <%--                        <th>In-Out</th>--%>
                        <%--                        <th>Login</th>--%>

                    </tr>
                    </thead>
                    <tbody >

                    <%
//                        String DATE_FORMATTERLog= "yyyy-MM-dd  HH:mm:ss";
//                        DateTimeFormatter formatterLog = DateTimeFormatter.ofPattern(DATE_FORMATTERLog);

                        List<Client> logins = myController.showAllLoginOfClients();

                        for (Client client : logins) {
                            session.setAttribute("id", client.getId_client());
                            session.setAttribute("accountNumber", client.getAccountList().get(0).getAccount_number());
                            session.setAttribute("FirstName", client.getFirst_name());
                            session.setAttribute("lastName", client.getLast_name());
                            session.setAttribute("email", client.getEmail());
                            session.setAttribute("loginDate", client.getLogFileArrayList().get(0).getDateTime().format(formatter));

                            // this below return java.util.stream.ReferencePipeline$3@12643df3
                            //session.setAttribute("loginDate", client.getClient().getLogFileArrayList().stream().map(LogFile::getDateTime));

                    %>
                    <tr>
                        <td style="width: 10px;"> ${id}</td>
                        <td >${accountNumber}</td>
                        <td >${FirstName}</td>
                        <td >${lastName}</td>
                        <td > ${email}</td>
                        <td >${loginDate}</td>
                    </tr>
                    <%}%>
                    </tbody>
                </table>

            </div>

        </div>

    </div>
</div>





<!--===============================================================================================-->


<script src="contents/js/main.js"></script>
<script src="contents/js/myScript.js"></script>
<%--<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>--%>
<%--<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>--%>
<script src="alert/dist/sweetalert-dev.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<%--<script src="http://code.jquery.com/jquery-latest.js "></script>--%>

<script>

    $(document).ready(function () {
        if (!$.browser.webkit) {
            $('.wrapper').html('<p>Sorry! Non webkit users. :(</p>');
        }
    });
</script>
<!--===============================================================================================-->

</body>
</html>



