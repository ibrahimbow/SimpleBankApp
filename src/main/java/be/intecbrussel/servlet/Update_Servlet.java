package be.intecbrussel.servlet;

import be.intecbrussel.controller.MyController;
import be.intecbrussel.dao_implementation.AdminDaoImpl;
import be.intecbrussel.entity.Account;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.channels.AcceptPendingException;

@WebServlet(name = "Update")
public class Update_Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        AdminDaoImpl adminDao = new AdminDaoImpl();
        MyController myController = new MyController();

        String update_bankAccountNumber = request.getParameter("findBankAccountNumberUpdate");

        int search_BankAccount_for_update = Integer.parseInt(update_bankAccountNumber);

        String userName = request.getParameter("usernameUpdate1");
        String firstName = request.getParameter("f_nameUpdate1");
        String lastName = request.getParameter("l_nameUpdate1");
        String pwd = request.getParameter("passwordUpdate1");
        String email = request.getParameter("emailUpdate1");
        String amountUpdate = request.getParameter("amountUpdate1");
        double updateBankAccount = Double.parseDouble(amountUpdate);


        if (myController.findByBankAccountNumber(search_BankAccount_for_update)!=null) {

            Account account = myController.findByBankAccountNumber(search_BankAccount_for_update);

            account.getClient().setUsername(userName);
            account.getClient().setFirst_name(firstName);
            account.getClient().setLast_name(lastName);
            account.getClient().setPassword(pwd);
            account.getClient().setEmail(email);
            account.setCurrent_balance(updateBankAccount);

            adminDao.update(account);

//            String empty = "";
//            HttpSession session = request.getSession();
//            session.setAttribute("usernameUpdate1", empty);
//            session.setAttribute("f_nameUpdate1", empty);
//            session.setAttribute("l_nameUpdate1", empty);
//            session.setAttribute("passwordUpdate1", empty);
//            session.setAttribute("emailUpdate1", empty);
//            session.setAttribute("amountUpdate1", empty);

            response.sendRedirect("adminUpdate.jsp");
        }

    }



}
