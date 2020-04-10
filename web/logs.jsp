
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="be.intecbrussel.controller.MyController" %>
<%@ page import="be.intecbrussel.entity.TransactionsLog" %>
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
    <title>Welcome to you Account</title>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <!--===============================================================================================-->
    <link rel="stylesheet" href="contents/css/style.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="../vendor/bootstrap/css/bootstrap.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="../vendor/animate/animate.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="../vendor/select2/select2.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="../vendor/perfect-scrollbar/perfect-scrollbar.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="../css/util.css">
    <link rel="stylesheet" type="text/css" href="../css/main.css">
    <link rel="stylesheet" href="alert/dist/sweetalert.css">

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

        if (current_Amount_D != myController.findByBankAccountNumber(from_bankAccountNumber).getCurrent_balance()) {
            session.setAttribute("amount", myController.findByBankAccountNumber(from_bankAccountNumber).getCurrent_balance());
        }
    %>


    <%--disable the buttom until the field is filled--%>
    <script>
        function manage(txt) {
            var bt = document.getElementById('submit');
            var ele = document.getElementsByTagName('input');

            // LOOP THROUGH EACH ELEMENT.
            for (i = 0; i < ele.length; i++) {

                // CHECK THE ELEMENT TYPE.
                if (ele[i].type === 'text' && ele[i].value === '') {
                    bt.disabled = true;    // Disable the button.
                    return false;
                }
                else {
                    bt.disabled = false;   // Enable the button.
                }
            }
        }
    </script>

    <%--This script to allow only numbers/digits in TextBox--%>
    <SCRIPT >
        function isNumber(evt) {
            var iKeyCode = (evt.which) ? evt.which : evt.keyCode
            return !(iKeyCode !== 46 && iKeyCode > 31 && (iKeyCode < 48 || iKeyCode > 57));
        }
    </SCRIPT>
<%--  to update and reload the data from database  --%>
    <script src="http://code.jquery.com/jquery-latest.js "></script>
        <script>
            setInterval("my_function();",1000);
        function my_function(){
            $('#refresh').load(location.href + ' #currentAmountNow');
        }
    </script>

</head>
<body >

    <div class="login-wrap">
<center>
    <div class="login-html">

<%--   this is to get the username from database via servlet also same as with amount--%>

    <div class="welcome"> Welcome  <br> ${username} </div>
    <br><div class="fadeInUp" > Your Account number:${from_bankAccountNumber} </div>  <br>

        <div id="refresh">
            <div id="currentAmountNow" >
                <%--   Finally i made it more than 11 hours to make auto-reload the data of new amount for receiver and sender as well   --%>
                your current Amount is : ${amount}
            </div>
        </div>

    <hr style="line-break: auto"/>
    <div id="data"></div>
    <br> <br>
    <div class="welcome">Transaction money ${welcome} </div>
    <br> <br>
    <div class="login-form">
    <form action="transferMoney" method="post" name="sendMoney">
        please enter Amount
        <input type="text"  id="money" name="money" onchange="manage(this)" onkeypress="javascript:return isNumber(event)" >
        <br> <br>
        Enter the account number
        <input type="text"  id="bankAccountNumber" name="bankAccountNumber"  onchange="manage(this)" onkeypress="javascript:return isNumber(event)" ><br>
        <div class="group">
            <input type="submit" class="button" id="submit" value="Transfer Money" disabled />
        </div>
    </form>
    <form action="logout">
        <div class="group">
            <input type="submit" class="button" value="Logout">
        </div>
    </form>
    </div>
</center>
</div>
    </div>
    <div class="container-table100">
        <div class="wrap-table100">
            <div class="table100 ver1 m-b-110">
                <div class="table100-head">
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
    List<TransactionsLog> pList = myController.showTransactionLog(myController.findByBankAccountNumber(from_bankAccountNumber).getId_account());
    for (TransactionsLog transaction : pList) {
        session.setAttribute("date_To", transaction.getTransaction_date_time());
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
    </div>



<!--===============================================================================================-->
<script src="../vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
<script src="../vendor/bootstrap/js/popper.js"></script>
<script src="../vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
<script src="../vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
<script src="../vendor/perfect-scrollbar/perfect-scrollbar.min.js"></script>
<script>
    $('.js-pscroll').each(function(){
        var ps = new PerfectScrollbar(this);
        $(window).on('resize', function(){
            ps.update();
        })
    });


</script>
<!--===============================================================================================-->
<script src="../js/main.js"></script>



</body>
</html>




