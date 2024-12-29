package com.enicar.soc.dto;


public class ErrorMessage {
    private String message;

    public ErrorMessage() {
    }

    public ErrorMessage(String message, int status, String path) {
        this.message = message;
        this.status = status;
        this.path = path;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    private int status;
    private String path;
}
