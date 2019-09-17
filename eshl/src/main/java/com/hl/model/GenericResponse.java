package com.hl.model;

public class GenericResponse<T extends Object> {
    private T result;

    private int status;

    private GenericError error;

    public GenericResponse(T result, int status, GenericError error) {
        this.result = result;
        this.status = status;
        this.error = error;
    }

    public GenericResponse() {
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public GenericError getError() {
        return error;
    }

    public void setError(GenericError error) {
        this.error = error;
    }
}
