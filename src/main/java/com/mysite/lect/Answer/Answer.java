package com.mysite.lect.Answer;

import com.mysite.lect.Question.Question;
import com.mysite.lect.User.SiteUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

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

   @ManyToOne
    private SiteUser author;

    private LocalDateTime modifyTime;

    @ManyToMany
    Set<SiteUser> voter;
}
