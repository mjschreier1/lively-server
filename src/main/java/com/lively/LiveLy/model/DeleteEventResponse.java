package com.lively.LiveLy.model;

public class DeleteEventResponse {

    private long id;
    private int status;
    private String message;

    public DeleteEventResponse(long id, int status, String message) {
        this.id = id;
        this.status = status;
        this.message = message;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
