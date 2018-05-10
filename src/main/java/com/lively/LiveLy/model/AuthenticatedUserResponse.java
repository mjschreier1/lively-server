package com.lively.LiveLy.model;

public class AuthenticatedUserResponse {

    boolean authenticated;
    boolean isAdmin;

    public AuthenticatedUserResponse(boolean authenticated, boolean isAdmin) {
        this.authenticated = authenticated;
        this.isAdmin = isAdmin;
    }
}
