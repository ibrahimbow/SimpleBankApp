<%@ page import="be.intecbrussel.controller.MyController" %>
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
    <link rel="stylesheet" href="style.css">
    <title>Welcome to you Account</title>
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
<body>


<center>
    <br>
    <br>

<h2>
<%--   this is to get the username from database via servlet also same as with amount--%>


    welcome back ${username}
    <br>    <br> Your Account number:${from_bankAccountNumber}   <br>
    <br><br>
    <div id="refresh">
        <div id="currentAmountNow">
<%--   Finally i made it more than 11 hours to make auto-reload the data of new amount for receiver and sender as well   --%>
            your current Amount is : ${amount}
        </div>
    </div>

    <br><br> <br>
    <div id="data"></div>
    <br> <br>
    Tranfer money ${welcome}
    <br> <br>
    <form action="transferMoney" method="post" name="sendMoney">
    please enter Amount
        <input type="text" id="money" name="money" onchange="manage(this)" onkeypress="isNumber(event)" >
        <br> <br>
    Enter the account number
        <input type="text"  id="bankAccountNumber" name="bankAccountNumber"  onchange="manage(this)" onkeypress="isNumber(event)" ><br>
        <input type="submit" id="submit" value="Transfer Money" disabled />


        <br>
        <br>

    </form>
</h2>
    <form action="logout">
        <input type="submit" value="Logout">
    </form>
</center>

</body>
</html>
