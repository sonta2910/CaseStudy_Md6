package com.casestudymodule6.model.user;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Role {
    public enum RoleType {
        ADMIN, USER
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleType name;
}
