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

@WebServlet(name = "Search")
public class Search extends HttpServlet {

    public Search() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        MyController myController = new MyController();

        String to_bankAccountNumber = request.getParameter("param4");
//        int result_to_BankAccount = Integer.parseInt(to_bankAccountNumber);
        int result_to_BankAccount = 4866756;

        if (myController.checkIfBankAccountNumberIsExist(result_to_BankAccount)!=null) {
            response.setContentType("text/html");
            PrintWriter outt2 = response.getWriter();
            outt2.print("");

            request.getSession().setAttribute("usernameUpdate", myController.findByBankAccountNumber(result_to_BankAccount).getClient().getUsername());
            request.getSession().setAttribute("f_nameUpdate", myController.findByBankAccountNumber(result_to_BankAccount).getClient().getFirst_name());
            request.getSession().setAttribute("l_nameUpdate", myController.findByBankAccountNumber(result_to_BankAccount).getClient().getLast_name());
            request.getSession().setAttribute("passwordUpdate", myController.findByBankAccountNumber(result_to_BankAccount).getClient().getPassword());
            request.getSession().setAttribute("emailUpdate", myController.findByBankAccountNumber(result_to_BankAccount).getClient().getEmail());
            request.getSession().setAttribute("amountUpdate", myController.findByBankAccountNumber(result_to_BankAccount).getCurrent_balance());

        }else{
            response.setContentType("text/html");
            PrintWriter outt = response.getWriter();
            outt.print("Not Exists");
        }

//                try {
//                    Thread.sleep(2000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//            response.sendRedirect("admin.jsp");
//
//        HttpSession session = request.getSession();
//
//        String update_bankAccountNumber = request.getParameter("findBankAccountNumber");
//        int search_BankAccount_for_update = Integer.parseInt(update_bankAccountNumber);
//
//        if (myController.findByBankAccountNumber(search_BankAccount_for_update)!=null) {
//
//            session.setAttribute("usernameUpdate",myController.findByBankAccountNumber(search_BankAccount_for_update).getClient().getUsername());
//            session.setAttribute("f_nameUpdate",myController.findByBankAccountNumber(search_BankAccount_for_update).getClient().getFirst_name());
//            session.setAttribute("l_nameUpdate",myController.findByBankAccountNumber(search_BankAccount_for_update).getClient().getLast_name());
//            session.setAttribute("passwordUpdate",myController.findByBankAccountNumber(search_BankAccount_for_update).getClient().getPassword());
//            session.setAttribute("emailUpdate",myController.findByBankAccountNumber(search_BankAccount_for_update).getClient().getEmail());
//            session.setAttribute("amountUpdate",myController.findByBankAccountNumber(search_BankAccount_for_update).getCurrent_balance());
//
//
//        }else{
//            session.setAttribute("usernameUpdate","");
//            session.setAttribute("f_nameUpdate","");
//            session.setAttribute("l_nameUpdate","");
//            session.setAttribute("passwordUpdate","");
//            session.setAttribute("emailUpdate","");
//            session.setAttribute("amountUpdate","");
//
//
//        }
//        response.sendRedirect("admin.jsp");
//

    }
}
