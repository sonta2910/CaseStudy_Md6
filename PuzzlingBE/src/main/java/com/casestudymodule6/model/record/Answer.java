package com.casestudymodule6.model.record;

import com.casestudymodule6.model.question.Option;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class Answer implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "option_id")
    private PermaOption option;


    private String answerStatus;


}
