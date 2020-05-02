package org.dev.fhhf.SinglePageApp.model;

public class AppError {

    private String errorMsg;
    private Integer errorCode;
    private String supportContact;

    public AppError() {
    }

    public AppError(String errorMsg, Integer errorCode, String supportContact) {
        this.errorMsg = errorMsg;
        this.errorCode = errorCode;
        this.supportContact = supportContact;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getSupportContact() {
        return supportContact;
    }

    public void setSupportContact(String supportContact) {
        this.supportContact = supportContact;
    }

    @Override
    public String toString() {
        return "Error{" +
                "errorMsg='" + errorMsg + '\'' +
                ", errorCode='" + errorCode + '\'' +
                ", supportContact='" + supportContact + '\'' +
                '}';
    }
}
