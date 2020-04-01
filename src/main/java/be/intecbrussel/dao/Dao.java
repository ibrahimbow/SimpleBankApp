package be.intecbrussel.dao;

public interface Dao {

    void add(int id, String fisrtName, String lastName, String receiver);
    void show(int id);
    void delete(int id);
}
