package be.intecbrussel.servlet;

import be.intecbrussel.controller.MyController;
import be.intecbrussel.dao_implementation.AdminDaoImpl;
import be.intecbrussel.entity.Account;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "updateServlet")
public class Update_Servlet extends HttpServlet {


    public Update_Servlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AdminDaoImpl adminDao = new AdminDaoImpl();
        MyController myController = new MyController();
        try {
        String update_bankAccountNumber = request.getParameter("accountNumber");
        String userName = request.getParameter("username");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String pwd = request.getParameter("password");
        String email = request.getParameter("email");
        String amountUpdate = request.getParameter("amountUpdate");
        double updateBankAccount = Double.parseDouble(amountUpdate);


            int search_BankAccount_for_update = Integer.parseInt(update_bankAccountNumber);

            if (myController.findByBankAccountNumber(search_BankAccount_for_update) != null) {

                Account account = myController.findByBankAccountNumber(search_BankAccount_for_update);

                account.getClient().setUsername(userName);
                account.getClient().setFirst_name(firstName);
                account.getClient().setLast_name(lastName);
                account.getClient().setPassword(pwd);
                account.getClient().setEmail(email);
                account.setCurrent_balance(updateBankAccount);

                adminDao.update(account);

                response.setContentType("text/html");
                response.setHeader("Cache-Control", "no-cache");
                response.setHeader("Pragma", "no-cache");
                response.setCharacterEncoding("UTF-8");

                PrintWriter outt = response.getWriter();
                outt.print("updated");

            } else {
                response.setContentType("text/html");
                PrintWriter outt = response.getWriter();
                outt.print("ok");

            }
        }catch (Exception e){
            e.getMessage();
        }
    }
}
