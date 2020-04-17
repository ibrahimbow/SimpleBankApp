
package be.intecbrussel.servlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import be.intecbrussel.controller.MyController;
import java.io.PrintWriter;

@WebServlet(name = "register_client")
public class Register extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



        String userName = request.getParameter("myuserReg");
        String firstName = request.getParameter("fname");
        String lastName = request.getParameter("lname");
        String pwd = request.getParameter("password_register");
        String email = request.getParameter("email");

        MyController myController = new MyController();

        try{
            if(myController.checkEmail(email)==null && myController.checkUserName(userName)==null) {

                myController.registerByClient(userName, firstName, lastName, email, pwd);

                response.setContentType("text/html");
                response.setHeader("Cache-Control", "no-cache");
                response.setHeader("Pragma", "no-cache");
                response.setCharacterEncoding("UTF-8");

                PrintWriter outt = response.getWriter();
                outt.print("created");


                response.sendRedirect("welcome.jsp");

            } else {
                response.setContentType("text/html");
                PrintWriter outt = response.getWriter();
                outt.print("");
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }


}
