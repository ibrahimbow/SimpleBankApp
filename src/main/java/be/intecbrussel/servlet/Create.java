package be.intecbrussel.servlet;

import be.intecbrussel.controller.MyController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Create")
public class Create extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userName = request.getParameter("username");
        String firstName = request.getParameter("fname");
        String lastName = request.getParameter("lname");
        String pwd = request.getParameter("password");
        String email = request.getParameter("email");
        String amount = request.getParameter("amount");
        double newAmount = Double.parseDouble(amount);

        MyController myController = new MyController();
        myController.getAdminDao().createNewAccount(userName,firstName,lastName,email,pwd,newAmount);

        response.sendRedirect("adminCreate.jsp");

    }

}
