package com.lijojacob.mls.exception;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public @Data class FieldErrorResource {
    private String resource;
    private String field;
    private String code;
    private String message;

}
