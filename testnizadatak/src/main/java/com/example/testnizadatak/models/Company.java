package com.example.testnizadatak.models;


import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID", nullable = false)
    private long id;

    @Column(name="NAME", nullable = false)
    private String name;

    @Column(name="BALANCE", nullable = false)
    private String balance;

    @Column(name = "MODIFIER", nullable = false)
    private Modifier modifier;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    @JsonBackReference
    private Set<UserAccount> userAccounts;

    public Company(long id, String name, String balance, Modifier modifier) {
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.modifier = modifier;
    }

    public Company() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public Modifier getModifier() {
        return modifier;
    }

    public void setModifier(Modifier modifier) {
        this.modifier = modifier;
    }

    public Set<UserAccount> getUserAccounts() {
        return userAccounts;
    }

    public void setUserAccounts(Set<UserAccount> userAccounts) {
        this.userAccounts = userAccounts;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", balance='" + balance + '\'' +
                ", modifier=" + modifier +
                ", userAccounts=" + userAccounts +
                '}';
    }
}
