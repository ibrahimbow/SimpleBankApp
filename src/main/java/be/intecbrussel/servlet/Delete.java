package be.intecbrussel.servlet;

import be.intecbrussel.controller.MyController;
import be.intecbrussel.dao_implementation.AdminDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Delete")
public class Delete extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        MyController myController = new MyController();


        String delete_this_bankAccountNumber = request.getParameter("findBankAccountNumberToDeleteIt");
        int toBe_deleted_BankAccount = Integer.parseInt(delete_this_bankAccountNumber);


        if (myController.checkIfBankAccountNumberIsExist(toBe_deleted_BankAccount) != null) {
            int id_client = myController.findByBankAccountNumber(toBe_deleted_BankAccount).getClient().getId_client();

            myController.getAdminDao().delete(id_client);

            response.sendRedirect("adminDelete.jsp");
        }



    }


}
