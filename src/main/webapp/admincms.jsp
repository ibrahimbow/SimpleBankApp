<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="be.intecbrussel.controller.MyController" %>
<%@ page import="be.intecbrussel.entity.Account" %>
<%@ page import="be.intecbrussel.entity.Client" %>
<%@ page import="be.intecbrussel.entity.TransactionsLog" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.util.List" %>
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
    <link rel='stylesheet' href='use.fontawesome.com/releases/v5.0.11/css/all.css'>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.7.2/animate.min.css">
    <link rel="stylesheet" href="contents/css/style_admin.css">
    <link rel="stylesheet" href="contents/css/style.css">

    <link rel="stylesheet" href="contents/css/style_popup.css">
    <link rel="stylesheet" href="contents/css/styleAnimation.css">
    <link rel="stylesheet" href="contents/css/styleAnimation.css">

<%--    <!--===============================================================================================-->--%>
<%--    <link rel="stylesheet" type="text/css" href="contents/vendor/perfect-scrollbar/perfect-scrollbar.css">--%>
<%--    <!--===============================================================================================-->--%>

</head>
<body>

<div class="tabContainer">
    <div class="header-html">
        <form action="logoutUser" >
        <div class="logoAdmin ">Bank webApp</div>
        <div class="adminName" id="username">Admin :   ${username}
              <input type="image" class="buttonLogout" value="Logout" src="contents/images/logout.png" alt="Logout">

        </div>
        </form>
    </div>

    <div class="left-html ">
        <div class="buttonContainer">
            <CENTER>
            <nav>


                <a  onclick="showPanel(0,'#2b9cd2')"><i class="fa fa-home fa-fw icon"></i><br>Home</a>
                <a  onclick="showPanel(1,'#2b9cd2')"><i class="fas fa-users icon"></i><br> Clients Info</a>
                <a onclick="showPanel(2,'#2b9cd2')"><i class=" fas fa-user-plus icon"></i><br>Add New Client</a>
                <a onclick="showPanel(3,'#2b9cd2')"><i class="fas fa-users-cog icon"></i><br>Edit & Delete</a>
                <a onclick="showPanel(4,'#2b9cd2')"><i class="fas fa-exchange-alt icon"></i><br> Transactions</a>
                <a onclick="showPanel(5,'#2b9cd2')"><i class="fas fa-history icon"></i><br>Logs</a>

            </nav>
            </CENTER>
        </div>

    </div>
    <div class="right_content-html">

        <%-- home section--%>
        <div class="tabPanel"> <br><br><br><br><br><Br> Welcome to control panel
            <br><br>

        <div class="textwelcome animated fadeIn ">Here you can view all the  clients information<br><br>
            You can add new client <br><br>
            you can Edit the information of the client or delete the client<br><br>
        You can view all the transactions of all clients<br><br>
        You can view all logins for all clients </div>

        </div>

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
        <div class="tabPanel">Add New Client
            <hr style=" width:80%; color: #586577 " />
            <br>
            <div class="login-form">
            <form id="myformReg">
                <div class="editDeleteAdminForm">
                    <div class="group">
                        <label for="myuserReg" class="label">Username</label>
                        <input id="myuserReg" type="text" class="inputupdatedel" name="username" onchange="javascript:return check_username()">

                    </div>
                    <div class="group">
                        <label for="fname" class="label">First Name</label>
                        <input id="fname" type="text" class="inputupdatedel" name="fname">
                    </div>
                    <div class="group">
                        <label for="lname" class="label">Last Name</label>
                        <input id="lname" type="text" class="inputupdatedel" name="lname">
                    </div>
                    <div class="group">
                        <label for="password_register" class="label">Password</label>
                        <input id="password_register"  class="inputupdatedel"  name="password_register">
                    </div>
                    <div class="group">
                        <label for="email" class="label">Email Address</label>
                        <input id="email" type="email" class="inputupdatedel" name="email" placeholder="email@email.com"
                               onchange="javascript:return check_email()" required />
                    </div>
                    <div class="group">
                        <label for="amountCreate" class="label">Amount</label>
                        <input id="amountCreate" type="text" class="inputupdatedel" name="amountCreateN" placeholder="0.0"
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
        <div class="tabPanel">Edit & delete
            <hr style="line-break: auto; width:80%;height: 0.1px; background-color: #586577 " />
            <div class="login-form">
                <div class="editDeleteAdminForm">
              <form id="myform_update_delete">

                <div  style="height: 20px;">
                    <input id="number1" type="text" name="findBankAccountNumberUpdate" class="inputTransaction" placeholder="Search Account Number"
                           onchange="return searchAccountNumber()"
                           onkeypress="return isNumber(event)"><br>
                </div>
                <br>

                <div id="refresh1" >
                    <div id="up1">
                        <div class="group">
                        <label for="myuserUD" class="label">Username</label>
                        <input id="myuserUD"  type="text" class="inputupdatedel" name="usernameUpdate1" value="${usernameUpdate}"
                        onchange="return check_username_update_delete()">
                    </div>
                </div>
                </div>

                <div id="refresh2" >
                    <div id="up2">
                        <div class="group">
                        <label for="fnameUpdate" class="label">First Name</label>
                        <input id="fnameUpdate" type="text" class="inputupdatedel" name="f_nameUpdate1" value="${f_nameUpdate}">

                        </div>
                    </div>
                </div>

                <div  id="refresh3" >
                    <div id="up3">
                        <div class="group">
                  <label for="lnameUpdate" class="label">Last Name</label>
                  <input id="lnameUpdate" type="text" class="inputupdatedel" name="l_nameUpdate1" value="${l_nameUpdate}" >
                    </div>
                </div>
                </div>

                <div id="refresh4" >
                    <div id="up4">
                        <div class="group">
                        <label for="pass1Update" class="label">Password</label>
                        <input id="pass1Update" type="text" class="inputupdatedel"  name="passwordUpdate1" value="${passwordUpdate}" >
                    </div>
                </div>
                </div>

                <div  id="refresh5" >
                    <div id="up5">
                        <div class="group">
                        <label for="emailUD" class="label">Email Address</label>
                        <input id="emailUD" type="text" class="inputupdatedel" name="emailUpdate1" placeholder="email@email.com"
                               value="${emailUpdate}" onchange="return check_email_update_delete()">
                    </div>
                </div>
                </div>
                <div id="refresh6" >
                    <div id="up6" >
                        <div class="group">
                        <label for="amountUpdate" class="label">Amount</label>
                        <input id="amountUpdate" type="text" class="inputupdatedel" data-type="float" name="amountUpdate1" value="${amountUpdate}"
                               placeholder="0.0" onkeypress="return isNumber(event)">
                    </div>
                </div>
                </div>
                  <div class="group">
                <input type="submit" class="button" value="update" onclick=" return update_client()">
                  </div>
                  <div class="group">
                <input type="button" class="buttondel" value="DELETE"  onclick="return delete_client()">
                  </div>
            </form>
                </div>
            </div>
        </div>

        <%--Transaction section--%>
        <div class="tabPanel">Transactions Information
            <br><br>

            <div class="tablefit">
                <table class="ea_table " >
                    <thead class="tableFixedBG">
                    <tr>
                        <th>Id</th>
                        <th>From </th>
                        <th>Sender</th>
                        <th>Date Time</th>
                        <th>Receiver</th>
                        <th>To</th>
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
<div class="copyright" >copyright©ibrahim Alolofi
</div>

</div>





<!--===============================================================================================-->


<script src="contents/js/main.js"></script>
<script src="contents/js/myScript.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="alert/dist/sweetalert-dev.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

<script src="http://code.jquery.com/jquery-latest.js "></script>

<script src='https://kit.fontawesome.com/a076d05399.js'></script>
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



