package com.casestudymodule6.model.record;

import com.casestudymodule6.model.question.Exam;
import com.casestudymodule6.model.user.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
public class Record implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private LocalDateTime time;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "record_id")
    private Set<RecordDetail> recordDetail;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "exam_id")
    private PermaExam exam;

    private int userPoint;

    private int examPoint;


}
