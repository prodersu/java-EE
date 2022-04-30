package com.example.demo.dto;

import java.io.Serializable;

public class textResponse implements Serializable {

    private static final long serialVersionUID = 987654322L;
    private final String response;

    public textResponse(String response){
        this.response = response;
    }


}
