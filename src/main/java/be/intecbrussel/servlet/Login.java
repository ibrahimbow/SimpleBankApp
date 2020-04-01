package be.intecbrussel.servlet;

import be.intecbrussel.controller.MyController;
import be.intecbrussel.entity.Transaction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Hashtable;
import java.util.List;


@WebServlet(name = "Login")
public class Login extends HttpServlet {



    public Login() {
        super();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {




        String username = request.getParameter("username");
        String pwd = request.getParameter("pwd");
        MyController myController = new MyController();


            if (myController.checkForLoginAccount(username, pwd)!=null) {
                HttpSession session = request.getSession();
                session.setAttribute("username",myController.checkForLoginAccount(username,pwd).getClient().getUsername());
                session.setAttribute("amount",myController.checkForLoginAccount(username,pwd).getCurrent_balance());
                session.setAttribute("from_bankAccountNumber",myController.checkForLoginAccount(username,pwd).getAccount_number());

//                session.setAttribute("amount",myController.checkForLoginAccount(username,pwd).getCurrent_balance());
//                session.setAttribute("yourBankAccountNumber",myController.checkForLoginAccount(username,pwd).getAccount_number());

                response.sendRedirect("welcome.jsp");
            }else {
                response.sendRedirect("index.jsp");
            }

    }

    }
