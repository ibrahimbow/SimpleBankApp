<%@ page import="be.intecbrussel.controller.MyController" %>
<%@ page import="java.io.PrintWriter" %>
<%


    MyController myController = new MyController();

    String email = request.getParameter("param3");
    try {
        if (myController.checkEmail(email)!=null) {
            response.setContentType("text/html");
            PrintWriter outt = response.getWriter();
            outt.print("Already Exists");

        }else{
            response.setContentType("text/html");
            PrintWriter outt2 = response.getWriter();
            outt2.print("");
        }
    } catch (Exception b) {
        b.getMessage();
    }

%>