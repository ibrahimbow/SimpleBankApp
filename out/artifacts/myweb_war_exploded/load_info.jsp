<%@ page import="be.intecbrussel.controller.MyController" %>
<%--
  Created by IntelliJ IDEA.
  User: HP6730b
  Date: 3/24/2020
  Time: 11:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
        session.setAttribute("currentAmount", myController.findByBankAccountNumber(from_bankAccountNumber).getCurrent_balance());
    }
%>