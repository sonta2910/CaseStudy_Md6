package com.casestudymodule6.model.record;

import com.casestudymodule6.model.question.Category;
import com.casestudymodule6.model.user.User;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
public class PermaExam implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    private int time;

    @Range(min = 0, max = 100)
    private int passScore;

    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;



}

