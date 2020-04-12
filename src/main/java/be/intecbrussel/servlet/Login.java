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


@WebServlet(name = "Login")
public class Login extends HttpServlet {



    public Login() {
        super();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MyController myController = new MyController();

        String username = request.getParameter("username");
        String pwd = request.getParameter("pwd");

            if (myController.checkForLoginAccount(username, pwd)!=null) {
                HttpSession session = request.getSession();
                session.setAttribute("username",myController.checkForLoginAccount(username,pwd).getClient().getUsername());
                session.setAttribute("amount",myController.checkForLoginAccount(username,pwd).getCurrent_balance());
                session.setAttribute("from_bankAccountNumber",myController.checkForLoginAccount(username,pwd).getAccount_number());
                int logClientId = myController.checkForLoginAccount(username,pwd).getClient().getId_client();
                myController.addLogsLogin(logClientId); // register login time for each time the client login

                response.sendRedirect("welcome.jsp");
            }else
                if(myController.checkForLoginAccountAdmin(username, pwd)!=null){
                HttpSession session = request.getSession();
                session.setAttribute("username",myController.checkForLoginAccountAdmin(username,pwd).getAdminUserName());

                response.sendRedirect("admincms.jsp");
            }else {

                    response.setContentType("text/html");
                    response.setHeader("Cache-Control", "no-cache");
                    response.setHeader("Pragma", "no-cache");
                    response.setCharacterEncoding("UTF-8");

                    PrintWriter outt = response.getWriter();
                    outt.print("Its not exists");

                    response.sendRedirect("index.jsp");
            }

    }

    }
