package be.intecbrussel.servlet;

import be.intecbrussel.controller.MyController;
import be.intecbrussel.custom_exception.BankTransactionException;
import be.intecbrussel.entity.Transaction;
import org.hibernate.Criteria;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.List;

@WebServlet(name = "Transaction_servlet")
public class Transaction_servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        MyController myController = new MyController();

        String amountSend = request.getParameter("money");
        String to_bankAccountNumber = request.getParameter("bankAccountNumber");
        double result_Amount = Double.parseDouble(amountSend);
        int result_to_BankAccount = Integer.parseInt(to_bankAccountNumber);
        int from_bankAccountNumber = (Integer) request.getSession().getAttribute("from_bankAccountNumber");

        if (myController.checkIfBankAccountNumberIsExist(result_to_BankAccount)==null) {
            response.sendRedirect("welcome.jsp");
        }else {
            // we have to check the texts if they are empty and the amount is not less than what the client has
            // and the account number is correct
            double current_amount = myController.findByBankAccountNumber(from_bankAccountNumber).getCurrent_balance();
            if (current_amount >= result_Amount ) {
                try {
                    myController.sendMoney(from_bankAccountNumber, result_to_BankAccount, result_Amount);
                    double roundAmount  = roundTwoDecimals(myController.findByBankAccountNumber(from_bankAccountNumber).getCurrent_balance());
                    request.getSession().setAttribute("amount", roundAmount);
                    response.sendRedirect("welcome.jsp");

                } catch (BankTransactionException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println(" you have not enough money ");
                response.sendRedirect("welcome.jsp");
            }
        }

    }

    private double roundTwoDecimals(double d) {
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Double.parseDouble(twoDForm.format(d));
    }

}
