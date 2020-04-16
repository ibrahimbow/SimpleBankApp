package be.intecbrussel.servlet;

import be.intecbrussel.controller.MyController;
import be.intecbrussel.custom_exception.BankTransactionException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;


@WebServlet(name = "transactionServlet")
public class Transaction_servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        MyController myController = new MyController();

        String amountSend = request.getParameter("amountx");
//        String amountSend = request.getParameter("money");
        String to_bankAccountNumber = request.getParameter("bankAccountNumberId");
        double result_Amount = Double.parseDouble(amountSend);
        int result_to_BankAccount = Integer.parseInt(to_bankAccountNumber);
        int from_bankAccountNumber = (Integer) request.getSession().getAttribute("from_bankAccountNumber");

        if (myController.findByBankAccountNumber(result_to_BankAccount)==null) {

            response.setContentType("text/html");
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Pragma", "no-cache");
            response.setCharacterEncoding("UTF-8");

            PrintWriter outt = response.getWriter();
            outt.print("Not Exists");


        }else {
            // we have to check the texts if they are empty and the amount is not less than what the client has
            // and the account number is correct
            double current_amount = myController.findByBankAccountNumber(from_bankAccountNumber).getCurrent_balance();
            if (current_amount >= result_Amount ) {
                try {

                    // here we use the method of transactio the money with trying to avoid what comes after 2 digit after comma
                    myController.sendMoney(from_bankAccountNumber, result_to_BankAccount, roundTwoDecimals(result_Amount));

                    double roundAmount  = roundTwoDecimals(myController.findByBankAccountNumber(from_bankAccountNumber).getCurrent_balance());

                    request.getSession().setAttribute("amount", roundAmount);

                    response.setContentType("text/html");
                    response.setHeader("Cache-Control", "no-cache");
                    response.setHeader("Pragma", "no-cache");
                    response.setCharacterEncoding("UTF-8");

                    PrintWriter outt = response.getWriter();
                    outt.print("Money is sent successfully..!");


                } catch (BankTransactionException e) {
                    e.printStackTrace();
                }
            } else {
                response.setContentType("text/html");
                response.setHeader("Cache-Control", "no-cache");
                response.setHeader("Pragma", "no-cache");
                response.setCharacterEncoding("UTF-8");

                PrintWriter outt = response.getWriter();
                outt.print("");


            }
        }

    }

    // This method to void more than two numbers after comma
    private double roundTwoDecimals(double d) {
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Double.parseDouble(twoDForm.format(d));
    }

}
