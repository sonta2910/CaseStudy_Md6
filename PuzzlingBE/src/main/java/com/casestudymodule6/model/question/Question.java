package com.casestudymodule6.model.question;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Data
public class Question {
    public enum Level{
        EASY(1),MEDIUM(2),HARD(5);
        private int score;

        Level(int score)
        {
            this.score = score;
        }

        public int getScore() {
            return score;
        }
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Level level;
    @NotBlank
    private String name;
    @Enumerated(EnumType.STRING)
    private QUESTIONTYPE questionType;

    public enum QUESTIONTYPE
    {
        ONE_CHOICE, MULTI_CHOICE
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "question_id")
    private Set<Option> options;


}
