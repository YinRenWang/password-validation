package dev.michael.core.model;


import java.util.List;

public class ValidationResponse {

    private boolean isValid;
    private List<String> errorMsg;

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public List<String> getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(List<String> errorMsg) {
        this.errorMsg = errorMsg;
    }

    public ValidationResponse() {
    }

    public ValidationResponse(boolean isValid) {
        this.isValid = isValid;
    }

    public ValidationResponse(boolean isValid, List<String> errorMsg) {
        this.isValid = isValid;
        this.errorMsg = errorMsg;
    }
}
