package com.example.testnizadatak.models;

import javax.persistence.*;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "log")
public class Log {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID", nullable = false)
    private long id;

    @Column(name="USERNAME", nullable = false)
    private String userName;

    @Column(name="DESCRIPTION", nullable = false)
    private String description;

    @Column(name="TIME", nullable = false)
    private Date time;


    public Log(String userName, String description, Date time) {
        this.userName = userName;
        this.description = description;
        this.time = time;
    }

    public Log() {
    }

    public long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "id = " + id +
                ", userName = '" + userName +
                ", description = " + description +
                ", time = " + time +
                '}';
    }
}
