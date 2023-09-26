package com.example.demov01.dto;

import lombok.Data;

import javax.persistence.*;
@Entity
@Table(name = "client", schema = "private")
@Data
public class ClientDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "identification")
    private String identification;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "deleted")
    private boolean deleted;
}
