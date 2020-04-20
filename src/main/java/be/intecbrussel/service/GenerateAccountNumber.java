package be.intecbrussel.service;

import be.intecbrussel.controller.MyController;

import java.util.Random;

public class GenerateAccountNumber {

    private int accountNumber;
    private MyController myController =new MyController();




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
    }

    // loop generating method until it finds unique bank account not the same in the database
    private void getGeneratedNumberAfterCheckedDuplicate(){
        generateAccountNumbers();
        while(true) {
                if (this.myController.checkIfBankAccountNumberIsExist(getAccountNumber())!=null) {
                generateAccountNumbers();
            } else {
                setAccountNumber(getAccountNumber());
                break;
            }
        }
    }

}

