package be.intecbrussel.servlet;

import be.intecbrussel.controller.MyController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "create_client")
public class Create extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        MyController myController = new MyController();
        // these parameter send from the javascript via  XMLHttpRequest send
        // these parameters have values as text datatype because it comes from html text input

        /* XMLHttpRequest is an API in the form of an object
         whose methods transfer data between a web browser and a web server. */

        String userName = request.getParameter("myuser");
        String firstName = request.getParameter("fname");
        String lastName = request.getParameter("lname");
        String pwd = request.getParameter("password_register");
        String email = request.getParameter("email");
        String amount = request.getParameter("amountCreate");
        String addNewAdmin = request.getParameter("myCheck");


        try {
            double newAmount = Double.parseDouble(amount);

            // This condition works only if the client want to register new account

            if(myController.checkEmail(email)==null && myController.checkUserName(userName)==null) {
                if(addNewAdmin!="") {

                    // Create new Client
                    myController.getAdminDao().createNewAccount(userName, firstName, lastName, email, pwd, newAmount);

                    response.setContentType("text/html");
                    response.setHeader("Cache-Control", "no-cache");
                    response.setHeader("Pragma", "no-cache");
                    response.setCharacterEncoding("UTF-8");

                    PrintWriter outt = response.getWriter();
                    outt.print("created");
                }else{
                    // Create new Admin


                    response.setContentType("text/html");
                    response.setHeader("Cache-Control", "no-cache");
                    response.setHeader("Pragma", "no-cache");
                    response.setCharacterEncoding("UTF-8");

                    PrintWriter outt = response.getWriter();
                    outt.print("created");
                }

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
