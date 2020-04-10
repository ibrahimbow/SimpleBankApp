
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
    <!--===============================================================================================-->
    <link rel="icon" type="image/png" href="images/icons/favicon.ico"/>
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="contents/vendor/bootstrap/css/bootstrap.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="contents/vendor/animate/animate.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="contents/vendor/select2/select2.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="contents/vendor/perfect-scrollbar/perfect-scrollbar.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="contents/css/util.css">
    <link rel="stylesheet" type="text/css" href="contents/css/main.css">


    <!--    ===============================================================================================-->

    <%-- This to clear the cashe  after the user logoff--%>
    <%
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        if(session.getAttribute("username")==null){
            response.sendRedirect("index.jsp");
        }

        MyController myController = new MyController();
        int from_bankAccountNumber = (Integer) request.getSession().getAttribute("from_bankAccountNumber");
        double current_Amount_D = (Double) request.getSession().getAttribute("amount");
        // refresh the current amount when occur change in database
        if (current_Amount_D != myController.findByBankAccountNumber(from_bankAccountNumber).getCurrent_balance()) {
            session.setAttribute("amount", myController.findByBankAccountNumber(from_bankAccountNumber).getCurrent_balance());
        }
    %>

    <%--  to update and reload the data from database  --%>
    <script src="http://code.jquery.com/jquery-latest.js "></script>
    <script>
        setInterval("my_function();",1000);
        function my_function(){
            $('#refresh').load(location.href + ' #currentAmountNow');
        }

    </script>

</head>


<div id="up" class="welcome-wrap" >
    <center>
        <div class="login-html">

            <%--   this is to get the username from database via servlet also same as with amount--%>
            <div class="welcome"> WELCOME <br> <br> ${username} </div>
            <br><span> Your Account number : </span><div class="number"  id="ac"> ${from_bankAccountNumber} </div>  <br>

            <div id="refresh">
                <div id="currentAmountNow" >
                    <%--   Finally i made it more than 11 hours to make auto-reload the data of new amount for receiver and sender as well   --%>
                    <span>Your current Amount : </span ><div class="number" id="currentAmount" > ${amount}</div>
                <%--Here (input)we get the current amount to compare it the transfer money --%>
                    <input hidden id="currentmoney"  value="${amount}">
                </div>
            </div>

            <hr style="line-break: auto; height: 3px; " />
            <div id="data"></div>
            <br>
            <div class="welcome">Transaction money </div>
            <br>
            <div class="login-form">
                <form action="transferMoney" method="post" name="sendMoney" onchange="manage(this)" onkeypress="return isNumber(event)">

                    <label for="amountx" class="label">Please enter the Amount</label>
                    <input id="amountx" type="text"  class="inputTransaction" name="money"
                    onchange=" checkAmount()">
                    <span id="enough"></span>
                    <br>
                    <label for="number" class="label">Enter the account number</label>
                    <input id="number" type="text"  class="inputTransaction"  name="bankAccountNumber"
                           onchange="javascript:return checkAccountNumber()" >
                    <span id="result4" name="result4"></span><br>
                    <div class="group">
                        <button type="submit" class="buttonTransaction" id="submit"  disabled>Transfer Money</button>
                    </div>
                </form>
                <form action="logout">
                    <div class="group">
                        <button type="submit" class="buttonTransaction"> Logout</button>
                    </div>
                </form>

            </div>
                <div class="welcome"><a href="#transferLog"> Transaction Logs </a> </div>
    </center>
</div>

<%--table of transactions--%>
<div class="container-table100">
    <div class="wrap-table100">
        <div class="table100 ver1 m-b-110">
            <div class="table100-head" id="transferLog">
                <table>
                    <thead>
                    <tr class="row100 head">
                        <th class="cell100 column2">Account Number </th>
                        <th class="cell100 column3">Date - Time </th>
                        <th class="cell100 column4">Amount</th>
                        <th class="cell100 column5">Transaction Type</th>
                    </tr>
                    </thead>
                </table>
            </div>

            <div class="table100-body js-pscroll">
                <table>
                    <tbody>

                    <%
                        String DATE_FORMATTER= "yyyy-MM-dd  HH:mm:ss";
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
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
                    <%}%>
                    </tbody>
                </table>
            </div>

        </div>

    </div>
    <center><a href="#up"class="button"> up </a></center>
</div>



<!--===============================================================================================-->
<script src="contents/vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
<script src="contents/vendor/bootstrap/js/popper.js"></script>
<script src="contents/vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
<script src="contents/vendor/select2/select2.min.js"></script>
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




