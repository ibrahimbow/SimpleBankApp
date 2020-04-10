package be.intecbrussel.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "administrator")
public class Admin {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int admin_id;

    @Column(name = "admin_name",nullable =false ,unique = true)
    @NotNull
    private String adminUserName;

    @Column(name = "admin_password")
    @NotNull
    private String admin_password;

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    public String getAdminUserName() {
        return adminUserName;
    }

    public void setAdminUserName(String admin_name) {
        this.adminUserName = admin_name;
    }


    public String getAdmin_password() {
        return admin_password;
    }

    public void setAdmin_password(String admin_password) {
        this.admin_password = admin_password;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "admin_id=" + admin_id +
                ", adminUserName='" + adminUserName + '\'' +
                ", admin_password='" + admin_password + '\'' +
                '}';
    }
}
