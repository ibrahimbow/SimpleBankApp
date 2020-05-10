<%@ page import="be.intecbrussel.controller.MyController" %>
<%@ page import="java.io.PrintWriter" %>

<%
    MyController myController = new MyController();

    String to_bankAccountNumber = request.getParameter("param4");
    int result_to_BankAccount = Integer.parseInt(to_bankAccountNumber);
    if (myController.checkIfBankAccountNumberIsExist(result_to_BankAccount)!=null) {
        response.setContentType("text/html");
        PrintWriter outt2 = response.getWriter();
        outt2.print("");

    }else{
        response.setContentType("text/html");
        PrintWriter outt = response.getWriter();
        outt.print("Not Exists");
    }


%>