package be.intecbrussel.application;

import be.intecbrussel.controller.MyController;
import be.intecbrussel.custom_exception.BankTransactionException;
import be.intecbrussel.dao_implementation.AdminDaoImpl;
import be.intecbrussel.entity.Account;
import be.intecbrussel.entity.Client;
import be.intecbrussel.entity.LogFile;
import be.intecbrussel.entity.TransactionsLog;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class MyApplication {

    public static void main(String[] args) throws BankTransactionException {

        AdminDaoImpl adminDao = new AdminDaoImpl();
        MyController m = new MyController();


//m.add("ibrahimbow","bbbg","bbg","bbg@bbg.com","bbg");
//
//
//        List<TransactionsLog> accountList = m.showAllTransactionLog();
//        System.out.println(" username \t\t firstname \t\t email \t\t date of join \t \t accountNumber\t \t amount \t number of transactions");
//        m.showAllTransactionLog().stream().map(TransactionsLog::getAccount).distinct()
//                .map(client -> client.getClient().getUsername() + " \t\t  "+
//                client.getClient().getFirst_name() + " \t\t" +
//                client.getClient().getEmail() + " \t\t" +
//                 client.getClient().getDate_of_join() + " \t\t" +
//                client.getAccount_number() + " \t\t" +
//                client.getCurrent_balance() + " \t\t" +
//                client.getTransactionArrayList().stream().map(TransactionsLog::getAccount).count()).forEach(System.out::println);
//
//
//        m.showAllClientsTransactionLog().forEach(System.out::println);


//        for (TransactionsLog t : accountList){
//            System.out.println(t.getAccount().getClient().getUsername()+ "\t "
//                    +t.getAccount().getClient().getLast_name() + "    \t"
//                    + t.getAccount().getClient().getEmail() + "\t "
//                    + t.getAccount().getAccount_number());
//            System.out.println("\n\n");
//        }

        // System.out.println(m.checkAdminUserName("as"));

//        List<Client> logins = m.showAllLoginOfClients();
//        for (Client client : logins) {
//            System.out.println(client.getLogFileArrayList()
//                    .stream()
////                    .filter(logFile -> logFile.getClient_log().getId_client() == client.getId_client())
//                    .map(LogFile::getDateTime).collect(Collectors.toList()));
//        }
//
//        List<LogFile> logFiles = m.ShowAllLogin();
//        for( LogFile logFile : logFiles){
//
//
//            System.out.println(logFile.getClient_log().getFirst_name());
//            System.out.println(logFile.getClient_log().getAccountList().get(0).getAccount_number());
//
//
//        }



//        TransactionsLog transaction = new TransactionsLog();
//
//        String DATE_FORMATTER= "yyyy-MM-dd HH:mm:ss";
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
//        String formatDateTime = transaction.getTransaction_date_time().format(formatter);
//
//        System.out.println(formatDateTime);


//        adminDao.delete(4);
//        adminDao.delete(8);
//        adminDao.delete(12);
//
//        m.add("q","q","qd","aaq","2q4asaser");


//        Account account = m.findByBankAccountNumber(6039569);
//        account.getClient().setFirst_name("alolofi");
//        m.getAdminDao().update(account);



        adminDao.createNewAccount("z","asd","sdsdfa","kpolk@x.com","adsdsdfad",123);


//

//
//        System.out.println(adminDao.getById(1).getClient().getFirst_name() );
//        System.out.println("----------");
//
//        adminDao.getAll().forEach(System.out::println);

//        adminDao.delete(6);
//        System.out.println("///");
     //   adminDao.getAll().stream().map(account1 -> account.getClient().getFirst_name()+ " "+ account.getClient().getLast_name()).forEach(System.out::println);

//        System.out.println("----------");
//        System.out.println(adminDao.getById(6));
//        System.out.println("----------");


//        BankAccount ibrahim = new BankAccount("ibrahim");
//        ibrahim.deposit(500);
//
//        BankAccount ahlam = new BankAccount(" ahlam");
//        ahlam.deposit(200);
//        ibrahim.transfer(100,ahlam);
//        System.out.println(ahlam.getBalance());




//        MyController controller = new MyController();

//
//        controller.showAll();

//        String receiver="";
//        RandomAccountNumber randomAccountNumber = new RandomAccountNumber();
//        System.out.println(randomAccountNumber.getAccountNumber());

//        Scanner key = new Scanner(System.in);
//        receiver=key.next();
//
//        System.out.println(randomAccountNumber.checkAccountNumber());
//        System.out.println(randomAccountNumber.getAccountNumber());
//
//

//        System.out.println(g.generatedAmount());

//        MyController controller = new MyController();
////        System.out.println(controller.checkForLoginAccount("9", "9").getAmount());
//
//
////        System.out.println(generateAccountNumber.getAccountNumber());
//
//         MyController myController = new MyController();
//        MyController_Backup myController_backup = new MyController_Backup();
//
////       System.out.println(myController.checkForLoginAccount("s", "s").getCurrent_balance());
//
//        String  amount = "10";
//        String from_bankAccountNumber= "5535636";
//        String to_bankAccountNumber  = "6158765";
//        double result_Amount =Double.parseDouble(amount);
//        int result_To_BankAccount =Integer.parseInt(to_bankAccountNumber);
//        int result_From_BankAccount =Integer.parseInt(from_bankAccountNumber);
//
////        myController.sendMoney(result_From_BankAccount,result_To_BankAccount,result_Amount);
////        myController_backup.showTransactionLog(myController_backup.findById(result_From_BankAccount).getId_account());
////        myController_backup.transactionMoney(result_From_BankAccount,result_To_BankAccount,result_Amount);
//        myController.sendMoney(result_From_BankAccount,result_To_BankAccount,result_Amount);
////        myController_backup.sendMoney(result_From_BankAccount,result_To_BankAccount,result_Amount);
//
//        //System.out.println(myController.checkIfBankAccountNumberIsExist(10));



    }
}

