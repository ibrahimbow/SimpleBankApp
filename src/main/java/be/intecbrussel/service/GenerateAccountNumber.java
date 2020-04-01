package be.intecbrussel.service;

import java.util.Random;

public class GenerateAccountNumber {

    private int accountNumber;

    public GenerateAccountNumber() {
        generateAccountNumbers();
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    private void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    // generate 7 digits
    public void generateAccountNumbers(){
        int start = 0;
        Random value = new Random();

        int r1 = value.nextInt(1000);
        start += r1;
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
            start += n;
            count++;
        }

        setAccountNumber(start);
    }

}
