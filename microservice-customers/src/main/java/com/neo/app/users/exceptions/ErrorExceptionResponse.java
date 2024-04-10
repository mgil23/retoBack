package com.neo.app.users.exceptions;

import java.util.List;


public class ErrorExceptionResponse {
    private int status;
    private List<String> message;
    private String time;

    public ErrorExceptionResponse() {}

    public ErrorExceptionResponse(int status, List<String> message, String time) {
        this.status = status;
        this.message = message;
        this.time = time;
    }

    public int getStatus() {
        return status;
    }

    public List<String> getMessage() {
        return message;
    }

    public String getTime() {
        return time;
    }


}
