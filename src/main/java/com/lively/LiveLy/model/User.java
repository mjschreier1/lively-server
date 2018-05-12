package com.lively.LiveLy.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id = 1;

    @Column(name = "first")
    private String first;

    @Column(name = "last")
    private String last;

    @Column(name = "pin")
    private int pin;

    @Column(name = "admin")
    private boolean admin;

    @Column(name = "email")
    private String email;

    protected User() {
    }

    public User(String first, String last, int pin, boolean admin, String email) {
        this.first = first;
        this.last = last;
        this.pin = pin;
        this.admin = admin;
        this.email = email;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", first='" + first + '\'' +
                ", last='" + last + '\'' +
                ", pin=" + pin +
                ", admin=" + admin +
                ", email='" + email + '\'' +
                '}';
    }
}
