package be.intecbrussel.dao;

import be.intecbrussel.entity.Account;

import java.util.List;

public interface Dao<T> {

    // create new client
    void createNewAccount(String userName, String first_name, String last_name, String email, String password,double amount);

    // update information of client
    void update(T t);
    // show customer  by id
    T getById(int id);

    List<T> getAll();

    void delete(int id);

}
