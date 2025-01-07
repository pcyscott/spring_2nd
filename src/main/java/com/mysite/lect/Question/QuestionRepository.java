package com.mysite.lect.Question;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    Question findByContent(String content);

    Question findByContentAndTitle(String content, String title);

    List<Question> findByTitleLike(String title);
}
