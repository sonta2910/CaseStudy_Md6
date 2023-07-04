package com.casestudymodule6.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    String avatar;
    String name;
    @Column(unique=true)
    String email;
    @Column(unique=true)
    String phone;
    @Enumerated(EnumType.STRING)
    Gender gender;
    public enum Gender{
        MALE,FEMALE
    }
}
