package dev.michael.core.model;

public class ValidationRequest {

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ValidationRequest(String password) {
        this.password = password;
    }

    public ValidationRequest() {
    }
}
