package be.intecbrussel.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="clients")
public class Client {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id_client;

    @Column(name = "username", nullable = false , unique = true)
    @NotNull
    private String username;

    @Column(name = "first_name" , nullable = false)
    @NotNull
    private String first_name;

    @Column(name = "last_name" , nullable = false)
    @NotNull
    private String last_name;

    @Column(name = "email", nullable = false , unique = true)
    @NotNull
    private String email;

    @Column(name = "password" , nullable = false)
    @NotNull
    private String password;

    @Column(name = "dateOfJoin", nullable = false)
    @NotNull
    private LocalDate date_of_join;

    @OneToMany(mappedBy="client",cascade=CascadeType.ALL ,orphanRemoval = true)
    private List<Account> accountList = new ArrayList<>();

    @OneToMany(mappedBy="client_log",cascade=CascadeType.ALL)
    private List<LogFile> logFileArrayList = new ArrayList<>();

    public List<LogFile> getLogFileArrayList() {
        return logFileArrayList;
    }

    public void setLogFileArrayList(List<LogFile> logFileArrayList) {
        this.logFileArrayList = logFileArrayList;
    }

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

    public LocalDate getDate_of_join() {
        return date_of_join;
    }

    public void setDate_of_join(LocalDate date_of_join) {
        this.date_of_join = date_of_join;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;
        Client client = (Client) o;
        return getUsername().equals(client.getUsername()) &&
                getEmail().equals(client.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getEmail());
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
