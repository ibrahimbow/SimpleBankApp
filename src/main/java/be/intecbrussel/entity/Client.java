package be.intecbrussel.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="clients")
public class Client {
    @Id
    //@Column(name = "id_client", updatable = false, nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id_client;

    @Column(name = "username")
    private String username;

    @Column(name = "first_name")
    private String first_name;

    @Column(name = "last_name")
    private String last_name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;


    @OneToMany(mappedBy="client",cascade=CascadeType.ALL)
    private List<Account> accountList = new ArrayList<>();

    @OneToMany(mappedBy="client_log",cascade=CascadeType.ALL)
    private List<LogFile> logFileArrayList = new ArrayList<>();


    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

    public Client() {
    }

    public int getId_client() {

        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }





    @Override
    public String toString() {
        return "Client{" +
                "id_client=" + id_client +
                ", username='" + username + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
