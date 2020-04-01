package be.intecbrussel.service;

import be.intecbrussel.application.RandomAccountNumber;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class GenerateAmount {

    private double generatedAmount;

    public GenerateAmount() {
        generatedAmount();
    }

    public double getGeneratedAmount() {
        return generatedAmount;
    }

    public void setGeneratedAmount(double generatedAmount) {
        this.generatedAmount = generatedAmount;
    }


    public double generatedAmount(){
        // here we use DecimalFormat to have 2 decimals after the comma from the Random
        return generatedAmount = Double.parseDouble(new DecimalFormat("#.##").format(ThreadLocalRandom.current().nextDouble(100)).replace(",","."));

    }
}

