package dev.michael.util.rule;

public class ValidationResult {

    private boolean isValid;

    private String errorMsg;

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public ValidationResult() {
    }

    public ValidationResult(boolean isValid) {
        this.isValid = isValid;
    }

    public ValidationResult(boolean isValid, String errorMsg) {
        this.isValid = isValid;
        this.errorMsg = errorMsg;
    }
}
