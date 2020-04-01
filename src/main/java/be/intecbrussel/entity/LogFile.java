package be.intecbrussel.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="logs")
public class LogFile {

    @Id
    @GeneratedValue
    private int id_log;

    @Column (name ="log_date_time")
    private LocalDate date;

    @ManyToOne (cascade=CascadeType.ALL)
    private Client client_log ;

    public int getId_log() {
        return id_log;
    }

    public void setId_log(int id_log) {
        this.id_log = id_log;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Client getClient_log() {
        return client_log;
    }

    public void setClient_log(Client client_log) {
        this.client_log = client_log;
    }
}
