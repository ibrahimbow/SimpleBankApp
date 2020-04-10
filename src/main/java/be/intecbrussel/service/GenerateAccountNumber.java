package be.intecbrussel.service;

import be.intecbrussel.controller.MyController;
import be.intecbrussel.entity.Account;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import java.util.Random;

public class GenerateAccountNumber {
    private static EntityManagerFactory ENTITY_MANAGER_FACTORY
            = Persistence.createEntityManagerFactory("bank_accounts");

    private int accountNumber;
    private MyController myController = new MyController();



    public GenerateAccountNumber() {
        // This method will work as loop until it finds unique account number
        getGeneratedNumberAfterCheckedDuplicate();
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    private void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }


    // generate 7 digits
    private void generateAccountNumbers(){
        int generate = 0;
        Random value = new Random();

        int range = value.nextInt(1000);
        generate += range;
        int count = 0;
        int n = 0;
        for(int i =0; i < 12;i++)
        {
            if(count == 4)
            {
                count =0;
            }
            else
            n = value.nextInt(1000000);
            generate += n;
            count++;
        }

        setAccountNumber(generate);
//        this. accountNumber= generate;
    }

    // loop generating method until if finds unique bank account
    private void getGeneratedNumberAfterCheckedDuplicate(){
        generateAccountNumbers();
        while(true) {
                if (myController.checkIfBankAccountNumberIsExist(getAccountNumber())!=null) {
                generateAccountNumbers();
            } else {
                setAccountNumber(getAccountNumber());
                break;
            }
        }
    }

}

