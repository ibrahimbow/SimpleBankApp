package be.intecbrussel.application;

import java.util.Random;

public class RandomAccountNumber {
    private String accountNumber;

    public RandomAccountNumber() {
     setAccountNumber(generateAccountNumber());
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    private String generateAccountNumber(){
        String start = "BE";
        Random value = new Random();

        //Generate two values to append to 'BE'
        int r1 = value.nextInt(10);
        int r2 = value.nextInt(10);
        start += Integer.toString(r1) + Integer.toString(r2) + " ";

        int count = 0;
        int n = 0;
        for(int i =0; i < 12;i++)
        {
            if(count == 4)
            {
                start += " ";
                count =0;
            }
            else
                n = value.nextInt(10);
            start += Integer.toString(n);
            count++;

        }
        //System.out.println(start);
        return start;
    }


    public boolean checkAccountNumber(){

        return accountNumber.equalsIgnoreCase(getAccountNumber());
    }


}
