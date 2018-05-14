package com.lively.LiveLy.model;

public class AuthenticatedUserResponse {

    private long id;
    private boolean authenticated;
    private boolean isAdmin;

    public AuthenticatedUserResponse(long id, boolean authenticated, boolean isAdmin) {
        this.id = id;
        this.authenticated = authenticated;
        this.isAdmin = isAdmin;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
