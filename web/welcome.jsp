
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="be.intecbrussel.controller.MyController" %>
<%@ page import="be.intecbrussel.entity.TransactionsLog" %>
<%@ page import="java.util.List" %>
<%@ page import="java.time.format.DateTimeFormatter" %>


<%--
  Created by IntelliJ IDEA.
  User: HP6730b
  Date: 3/24/2020
  Time: 11:46 PM
  To change this template use File | Settings | File Templates.
--%>

<html>
<head>
    <title>Welcome to you Account</title>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <!--===============================================================================================-->
    <link rel="stylesheet" href="contents/css/style.css">
    <link rel="stylesheet" href="contents/css/styleAnimation.css">
    <link rel="stylesheet" href="contents/css/styleAnimation.css">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>

    <link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.0.10/css/all.css'>
    <!--===============================================================================================-->

    <link rel="stylesheet" type="text/css" href="contents/vendor/bootstrap/css/bootstrap.min.css">
<%--    <!--===============================================================================================-->--%>
    <link rel="stylesheet" type="text/css" href="contents/vendor/perfect-scrollbar/perfect-scrollbar.css">
    <!--===============================================================================================-->
<%--    <link rel="stylesheet" type="text/css" href="contents/css/util.css">--%>
    <link rel="stylesheet" type="text/css" href="contents/css/main.css">
    <!--    ===============================================================================================-->

    <%-- This to clear off the cashe  after the user logoff--%>
    <%
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        if(session.getAttribute("username")==null){
            response.sendRedirect("index.jsp");
        }

        int from_bankAccountNumber=0;
        double current_Amount_D=0;

        MyController myController = new MyController();

        try {
            from_bankAccountNumber = (Integer) request.getSession().getAttribute("from_bankAccountNumber");
            current_Amount_D = (Double) request.getSession().getAttribute("amount");
            // refresh the current amount when occur change in database

            if (current_Amount_D != myController.findByBankAccountNumber(from_bankAccountNumber).getCurrent_balance()) {
                session.setAttribute("amount", myController.findByBankAccountNumber(from_bankAccountNumber).getCurrent_balance());
            }
        }catch (Exception e){
            e.getStackTrace();
        }

    %>

    <%-- my TRICK:)-  REFRESHING the only part of amount to be able to get the newest amount from database--%>
    <script src="http://code.jquery.com/jquery-latest.js "></script>
    <script>
        setInterval("my_function();",1000);
        function my_function(){
            $('#refresh').load(location.href + ' #currentAmountNow');
        }

    </script>

</head>

<body>
<div id="up" class="login-wrap-welcome" >
    <center>
        <div class="login-html">
            <%--   this is to get the username from database via servlet also same as with amount--%>
            <div class="welcome"> WELCOME <br> ${username}
            </div>
                <hr style="line-break: auto; height: 0.2px; background-color: #b1b3b6 " />
                <div class="welcome1"  id="ac">
            <br><span> Your Account number : </span> ${from_bankAccountNumber} </div>

            <div id="refresh" class="welcome1">
                <div id="currentAmountNow" >
                    <%--   Finally i made it more than 11 hours to make auto-reload the data of new amount for receiver and sender as well   --%>
                        <div id="currentAmount" >
                        <span>Your current Amount : €   ${amount}  </span >
                        </div>
                    <%--Here is the Hidden (input). We get the current amount to compare it the transfer money --%>
                    <%--hidden (my tricky way)--%>
                    <input hidden id="currentmoney"  value="${amount}">
                        <input hidden id="bankAccountNumberIdToCheckIfItTheSame"  value="${from_bankAccountNumber}">
                </div>
            </div>

                <hr style="line-break: auto; height: 0.2px; background-color: #b1b3b6 " />

            <div class="welcome">Money Transaction</div>
            <div class="login-form">
<%--                <form action="transferMoney" method="post" name="sendMoney" onchange="manage(this)" onkeypress="return isNumber(event)">--%>
                    <form onchange="manage(this)" onkeypress="return isNumber(event)">

                                <label for="amountx" class="label">Please enter the Amount</label>
                        <div class ="inputWithIcon">
                            <i class="fas fa-euro-sign icon"></i>
                                <input id="amountx" type="text"  class="inputTransaction" name="money" onchange=" checkAmount()">
                                <br>
                        </div>
                                <label for="bankAccountNumberId" class="label">Enter the account number</label>
                        <div class ="inputWithIcon">
                                <i class="fa fa-credit-card"></i>
                                <input id="bankAccountNumberId" type="text"  class="inputTransaction"  name="bankAccountNumber" onchange="javascript:return checkAccountNumber()" >
                                <br>
                        </div>

                        <div class="group">
                        <button type="submit" class="buttonTransaction" id="submit" onclick="return transaction_money()" disabled>Transfer Money</button>
                    </div>
                </form>
                <form action="logoutUser">
                    <div class="group">
                        <button type="submit" class="buttonTransaction"> Logout</button>
                    </div>
                </form>
                <br>
                <div class="welcome2"><a href="#transferLog"  > Transaction Logs </a> </div>
            </div>


    </center>
</div>






<%--table of transactions--%>

        <div  id="refreshtransaction">
            <div id="transactions">

                <div class="container-table100" >
                    <div class="wrap-table100"  >
        <div class="table100 ver1 m-b-110" >
            <div class="table100-head" id="transferLog">
                <table>
                    <thead>
                    <tr class="row100 head">
                        <th class="cell100 column2">Account Number </th>
                        <th class="cell100 column3">Date&nbsp;&nbsp;  - &nbsp;&nbsp;&nbsp; Time </th>
                        <th class="cell100 column4">Amount</th>
                        <th class="cell100 column5">Transaction Type</th>
                    </tr>
                    </thead>
                </table>
            </div>

            <div class="table100-body js-pscroll" >
                <table>
                    <tbody >
                    <%
                        // Here we have to use the the list of all the transactions of the client has made, auto-reload from database
                            String DATE_FORMATTER = "yyyy-MM-dd ' at ' HH:mm:ss";
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
                      try {


                            List<TransactionsLog> pList = myController.showTransactionLog(myController.findByBankAccountNumber(from_bankAccountNumber).getId_account());

                        for (TransactionsLog transaction : pList) {
                            session.setAttribute("date_To", transaction.getTransaction_date_time().format(formatter));
                            session.setAttribute("accountNumber_To", transaction.getForm_TO_account_number());
                            session.setAttribute("money_To", transaction.getTransaction_amount());
                            session.setAttribute("type_To", transaction.getTransactionType().getTransactionType());
                    %>
                    <tr class="row100 body">
                        <td class="column2"> ${accountNumber_To}</td>
                        <td class="cell100 column3" >${date_To}</td>
                        <td class="cell100 column4" >${money_To}</td>
                        <td class="cell100 column5" >${type_To}</td>
                    </tr>
                    <%}
                    }catch (Exception e){
                          e.getStackTrace();
                    }
                    %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <center><a href="#up"class="button"> up </a></center>
</div>

            </div>
        </div>

<!--===============================================================================================-->
<script src="contents/vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
<script src="contents/vendor/perfect-scrollbar/perfect-scrollbar.min.js"></script>
<script>
    $('.js-pscroll').each(function(){
        var ps = new PerfectScrollbar(this);
        $(window).on('resize', function(){
            ps.update();
        })
    });
</script>
<!--===============================================================================================-->
<script src="contents/js/main.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="alert/dist/sweetalert-dev.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>



</body>
</html>




