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

</head>
<body>
<center><br> <br>
    <h2>
        Welcome<h2 > ${username}</h2>
    </h2>

    <div class="login-wrap">
        <div class="login-html">
            <label for="tab-1" class="tab ">
                <a id="tab-1" href="adminCreate.jsp">CREATE</a></label>
            <label for="tab-2" class="tab ">
                <a id="tab-2" href="adminUpdate.jsp">UPDATE</a></label>
            <label for="tab-3" class="tab ">
                <a id="tab-3" href="adminDelete.jsp">DELETE</a></label>
            <div class="admin-form">

                <%-- Delete section >--%>
                <form action="delete" method="get">
                    <div class="delete-htm">
                        <div class="group">
                            <label for="bankAccountNumberDelete" class="label">Bank Account Number :</label>
                            <input id="bankAccountNumberDelete" type="text" class="input" name="findBankAccountNumberToDeleteIt">
                        </div>
                        <div class="button"><input type="submit" value="Delete"></div>
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
