package com.lively.LiveLy.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private User user;

    @Column(name = "amount")
    private float amount;

    @Column(name = "submittedOn")
    private LocalDateTime submittedOn;

    protected Payment() {

    }

    public Payment(User user, float amount, LocalDateTime submittedOn) {
        this.user = user;
        this.amount = amount;
        this.submittedOn = submittedOn;
    }

    public long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public LocalDateTime getSubmittedOn() {
        return submittedOn;
    }

    public void setSubmittedOn(LocalDateTime submittedOn) {
        this.submittedOn = submittedOn;
    }
}
