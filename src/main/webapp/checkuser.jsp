<%@ page import="be.intecbrussel.controller.MyController" %>
<%@ page import="java.io.PrintWriter" %>

<%
    MyController myController = new MyController();
    String user = request.getParameter("param2");
    try {
        // check in the admin data users
        if( myController.checkAdminUserName(user)!=null){
            response.setContentType("text/html");
            PrintWriter outt = response.getWriter();
            outt.print("Already Exists");
        }
        // check in the client data users
         else if(myController.checkUserName(user)!=null) {
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