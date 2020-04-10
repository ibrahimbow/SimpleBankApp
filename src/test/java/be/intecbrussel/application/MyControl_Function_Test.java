package be.intecbrussel.application;

import be.intecbrussel.controller.MyController;
import be.intecbrussel.entity.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MyControl_Function_Test {
    @Test
    public void checkIfTheUserNameAndPasswordIsValid() {
        MyController myController = new MyController();
        Assertions.assertEquals( null,myController.checkForLoginAccount("w23","w23"));
    }

    @Test
    public void checkRegisterDuplicateUserAccount() {
        MyController myController = new MyController();
        Assertions.assertEquals( null,myController.checkUserName("w12qw"));
    }



}
