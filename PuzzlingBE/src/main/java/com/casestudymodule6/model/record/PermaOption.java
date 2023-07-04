package com.casestudymodule6.model.record;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class PermaOption
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String status;
}

