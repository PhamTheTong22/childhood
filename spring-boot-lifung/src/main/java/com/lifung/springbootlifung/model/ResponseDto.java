package com.lifung.springbootlifung.model;

import java.io.Serializable;

public class ResponseDto implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    
    private final String jwttoken;

    public ResponseDto(String jwttoken) {
        this.jwttoken = jwttoken;
    }

    public String getToken() {
        return this.jwttoken;
    }
}
