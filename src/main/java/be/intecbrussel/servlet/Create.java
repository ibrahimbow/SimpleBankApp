package be.intecbrussel.servlet;

import be.intecbrussel.controller.MyController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "create_client")
public class Create extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        MyController myController = new MyController();

        String userName = request.getParameter("myuser");
        String firstName = request.getParameter("fname");
        String lastName = request.getParameter("lname");
        String pwd = request.getParameter("password_register");
        String email = request.getParameter("email");
        String amount = request.getParameter("amountCreate");

        try {
            double newAmount = Double.parseDouble(amount);

            if(myController.checkEmail(email)==null && myController.checkUserName(userName)==null){

            myController.getAdminDao().createNewAccount(userName, firstName, lastName, email, pwd, newAmount);

            response.setContentType("text/html");
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Pragma", "no-cache");
            response.setCharacterEncoding("UTF-8");

            PrintWriter outt = response.getWriter();
            outt.print("updated");

        } else {
            response.setContentType("text/html");
            PrintWriter outt = response.getWriter();
            outt.print("");

        }




        }catch (Exception e){
            e.getMessage();
        }
    }

}
