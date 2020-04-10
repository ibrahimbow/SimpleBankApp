package be.intecbrussel.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name ="logs")
public class LogFile {

    @Id
    @GeneratedValue
    private int id_log;

    @Column (name ="log_date_time")
    private LocalDateTime dateTime;

    @ManyToOne (cascade=CascadeType.ALL)
    private Client client_log ;

    public int getId_log() {
        return id_log;
    }

    public void setId_log(int id_log) {
        this.id_log = id_log;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Client getClient_log() {
        return client_log;
    }

    public void setClient_log(Client client_log) {
        this.client_log = client_log;
    }
}
