package com.lively.LiveLy.model;

import javax.persistence.*;

@Entity
@Table(name = "service_requests")
public class ServiceRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private User user;

    @Column(name = "unit")
    private String unit;

    @Column(name = "contact")
    private String contact;

    @Column(name = "subject")
    private String subject;

    @Column(name = "description")
    private String description;

    @Column(name = "open")
    private boolean open;

    @Column(name = "admin_notes")
    private String admin_notes;

    protected ServiceRequest() {
    }

    public ServiceRequest(User user, String unit, String contact, String subject, String description, boolean open) {
        this.user = user;
        this.unit = unit;
        this.contact = contact;
        this.subject = subject;
        this.description = description;
        this.open = open;
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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public String getAdmin_notes() {
        return admin_notes;
    }

    public void setAdmin_notes(String admin_notes) {
        this.admin_notes = admin_notes;
    }
}
