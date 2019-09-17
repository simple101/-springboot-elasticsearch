package com.hl.model;


import java.util.List;

public class GenericError {
    private String code;

    private String developerCode;

    private String message;

    private long time;

    private List<String> errors;

    public GenericError(String code, String developerCode, String message, long time, List<String> errors) {
        this.code = code;
        this.developerCode = developerCode;
        this.message = message;
        this.time = time;
        this.errors = errors;
    }

    public GenericError() {
        //Empty
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getDeveloperCode() {
        return developerCode;
    }

    public void setDeveloperCode(String developerCode) {
        this.developerCode = developerCode;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
