package com.example.testnizadatak.models;

public class TokenCryptoInfo {
    Token token;
    String key;

    public TokenCryptoInfo(Token token, String key){
        this.token = token;
        this.key = key;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
