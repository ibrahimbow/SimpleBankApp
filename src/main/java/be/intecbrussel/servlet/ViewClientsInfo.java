package be.intecbrussel.servlet;

import be.intecbrussel.controller.MyController;
import be.intecbrussel.custom_exception.BankTransactionException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ViewClientsInfo")
public class ViewClientsInfo extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        MyController myController = new MyController();

        String to_bankAccountNumber = request.getParameter("bankAccountNumber");

        int result_to_BankAccount = Integer.parseInt(to_bankAccountNumber);

        int from_bankAccountNumber = (Integer) request.getSession().getAttribute("from_bankAccountNumber");

        if (myController.findByBankAccountNumber(result_to_BankAccount)==null) {

            // show msg


        }else {

        }

    }


}
