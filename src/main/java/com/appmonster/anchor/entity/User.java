package com.appmonster.anchor.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "APP_USER")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID")
    private String userid;

    @Column(name = "USERNAME")
    private String username;

    //change to char[]
    @Column(name = "password")
    private String password;

    @Column(name = "ROLES")
    private String roles;

    @Column(name = "ACTIVE")
    private Boolean active;

}
