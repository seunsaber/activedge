package com.activedge.assessment.dtos;

public class ErrorResponse {
    private String code;
    private String description;

    public ErrorResponse(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
