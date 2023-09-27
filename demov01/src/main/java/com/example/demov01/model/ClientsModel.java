package com.example.demov01.model;
import lombok.Data;

@Data
public class ClientsModel {
    private String id;
    private String identification;
    private String name;
    private String email;
    private String phone;
    private boolean deleted;
}
