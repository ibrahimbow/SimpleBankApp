<%@ page import="java.io.PrintWriter" %>
<%@ page import="be.intecbrussel.controller.MyController" %>

<%
    MyController myController = new MyController();

    String update_bankAccountNumber = request.getParameter("param4");
    int search_BankAccount_for_update = Integer.parseInt(update_bankAccountNumber);

        if (myController.checkIfBankAccountNumberIsExist(search_BankAccount_for_update)!=null) {
            response.setContentType("text/html");
            PrintWriter outt2 = response.getWriter();
            outt2.print("");

            // Auto fill the information on page of the client that is existed on the text
            session.setAttribute("usernameUpdate",myController.findByBankAccountNumber(search_BankAccount_for_update).getClient().getUsername());
            session.setAttribute("f_nameUpdate",myController.findByBankAccountNumber(search_BankAccount_for_update).getClient().getFirst_name());
            session.setAttribute("l_nameUpdate",myController.findByBankAccountNumber(search_BankAccount_for_update).getClient().getLast_name());
            session.setAttribute("passwordUpdate",myController.findByBankAccountNumber(search_BankAccount_for_update).getClient().getPassword());
            session.setAttribute("emailUpdate",myController.findByBankAccountNumber(search_BankAccount_for_update).getClient().getEmail());
            session.setAttribute("amountUpdate",myController.findByBankAccountNumber(search_BankAccount_for_update).getCurrent_balance());

        }else{
            response.setContentType("text/html");
            PrintWriter outt = response.getWriter();
            outt.print("Not Exists");

            session.setAttribute("usernameUpdate","");
            session.setAttribute("f_nameUpdate","");
            session.setAttribute("l_nameUpdate","");
            session.setAttribute("passwordUpdate","");
            session.setAttribute("emailUpdate","");
            session.setAttribute("amountUpdate","");
        }

%>