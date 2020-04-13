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
import java.io.PrintWriter;

@WebServlet(name = "delete_client")
public class Delete extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        MyController myController = new MyController();

        String delete_this_bankAccountNumber = request.getParameter("accountNumber");

        try {

            int toBe_deleted_BankAccount = Integer.parseInt(delete_this_bankAccountNumber);

            if (myController.checkIfBankAccountNumberIsExist(toBe_deleted_BankAccount) != null) {
                int id_client = myController.findByBankAccountNumber(toBe_deleted_BankAccount).getClient().getId_client();

                myController.getAdminDao().delete(id_client);

                response.setContentType("text/html");
                response.setHeader("Cache-Control", "no-cache");
                response.setHeader("Pragma", "no-cache");
                response.setCharacterEncoding("UTF-8");

                PrintWriter outt = response.getWriter();
                outt.print("Deleted");
            }else{
                PrintWriter outt = response.getWriter();
                outt.print("");

            }


        }catch (Exception e){
            e.getMessage();
        }
    }



}
