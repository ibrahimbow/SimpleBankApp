
package be.intecbrussel.servlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import be.intecbrussel.controller.MyController;
import java.io.PrintWriter;

@WebServlet(name = "Register")
public class Register extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {




            String userName = request.getParameter("username");
            String firstName = request.getParameter("fname");
            String lastName = request.getParameter("lname");
            String pwd = request.getParameter("password_register");
            String email = request.getParameter("email");

            MyController myController = new MyController();



        try {
                myController.add(userName, firstName, lastName, email, pwd);

        } catch (Exception e) {
            e.getMessage();
        }

        response.sendRedirect("index.jsp");

    }

}
