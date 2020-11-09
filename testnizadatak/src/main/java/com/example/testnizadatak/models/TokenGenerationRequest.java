package com.example.testnizadatak.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tokengenerationrequest")
public class TokenGenerationRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID", nullable = false)
    private long id;

    @Column(name="USERID", nullable = false)
    private long userId;

    @Column(name="USERNAME", nullable = false)
    private String userName;

    public TokenGenerationRequest(long userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public TokenGenerationRequest() {
    }

    public long getId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "TokenGenerationRequest{" +
                "id = " + id +
                '}';
    }
}
