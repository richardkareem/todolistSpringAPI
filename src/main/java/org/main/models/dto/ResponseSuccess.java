package org.main.models.dto;

public class ResponseSuccess <T>{
    private String message;
    private int status;
    private T params;

    public ResponseSuccess(String message, int status, T params) {
        this.message = message;
        this.status = status;
        this.params = params;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getParams() {
        return params;
    }

    public void setParams(T params) {
        this.params = params;
    }
}
