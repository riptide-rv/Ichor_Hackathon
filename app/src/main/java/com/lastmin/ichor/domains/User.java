package com.lastmin.ichor.domains;

public class User {
    private String email;
    private int mode;
    public User(){

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public User(String email, int mode) {
        this.email = email;
        this.mode = mode;
    }
}
