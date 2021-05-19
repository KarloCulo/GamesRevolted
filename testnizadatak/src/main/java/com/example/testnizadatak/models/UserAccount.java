package com.example.testnizadatak.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "useraccount")
public class UserAccount {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID", nullable = false)
    private long id;

    @Column(name="NAME", nullable = false)
    private String name;

    @Column(name="PASSWORD", nullable = false)
    private String password;

    @Column(name="ROLE", nullable = false)
    private String role;

    @Column(name="BALANCE", nullable = false)
    private float balance;

    @Column(name="REQUESTSAVAILABLE", nullable = false)
    private int requestsAvailable;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    @JsonBackReference
    private Set<Token> tokens;

    @ManyToOne(fetch = FetchType.EAGER)
    private Company company;


    public UserAccount(long id, String name, String password, String role, float balance, int requestsAvailable) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.role = role;
        this.balance = balance;
        this.requestsAvailable = requestsAvailable;
    }

    public UserAccount() {
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public int getRequestsAvailable() {
        return requestsAvailable;
    }

    public void setRequestsAvailable(int requestsAvailable) {
        this.requestsAvailable = requestsAvailable;
    }

    public Set<Token> getTokens() {
        return tokens;
    }

    public void setTokens(Set<Token> tokens) {
        this.tokens = tokens;
    }

    public void addToken(Token token) {
        tokens.add(token);
        token.setOwner(this);
    }

    public void removeToken(Token token) {
        tokens.remove(token);
        token.setOwner(null);
    }

    public int hashCode() {
        return Objects.hash(id);
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "id = " + id +
                ", name = '" + name + '\'' +
                ", password = " + password +
                ", role = " + role +
                ", balance = " + balance +
                ", requestsAvailable = " + requestsAvailable +
                '}';
    }


}
