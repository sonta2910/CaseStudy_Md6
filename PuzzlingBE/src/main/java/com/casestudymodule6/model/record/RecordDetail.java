package com.casestudymodule6.model.record;

import com.casestudymodule6.model.question.Question;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
public class RecordDetail implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "question_id")
    private PermaQuestion question;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "recordDetail_id")
    private Set<Answer> answers;

}
