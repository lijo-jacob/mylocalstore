package com.lijojacob.mls.exception;

import java.util.List;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public @Data class ErrorResource {
    private String code;
    private String message;
    private List<FieldErrorResource> fieldErrors;

    public ErrorResource() { }

    public ErrorResource(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
