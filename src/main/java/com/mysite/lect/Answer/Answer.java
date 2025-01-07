package com.mysite.lect.Answer;

import com.mysite.lect.Question.Question;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String content;

    private String writer;

    private LocalDateTime createTime;

   @ManyToOne
    private Question question;
}
