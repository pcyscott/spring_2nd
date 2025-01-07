package com.mysite.lect;

import com.mysite.lect.Question.Question;
import com.mysite.lect.Question.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class LectApplicationTests {

    @Autowired
    private QuestionRepository QuestionRepository;

    @Test
    void testJpa(){
        Question q1 = new Question();
        q1.setTitle("title1");
        q1.setContent("content1");
        q1.setCreateTime(LocalDateTime.now());
        QuestionRepository.save(q1);

        Question q2 = new Question();
        q2.setTitle("title2");
        q2.setContent("content2");
        q2.setCreateTime(LocalDateTime.now());
        QuestionRepository.save(q2);
    }

    @Test//findAll
    void test1(){
        List<Question> all =  this.QuestionRepository.findAll();
        assertEquals(2, all.size());

        Question q = all.get(0);
        assertEquals("title1", q.getTitle());

        Question q2 = all.get(1);
        assertEquals("title2", q2.getTitle());
    }

    @Test//finbyid
    void test2(){
        Optional<Question> oq = this.QuestionRepository.findById(1L);
        if(oq.isPresent()){
            Question q = oq.get();
            assertEquals("title1", q.getTitle());
        }
    }

    @Test//findbysubject
    void test3(){
        Question q = this.QuestionRepository.findByContent("content1");
        assertEquals(1L, q.getId());
    }

    @Test//findbycontentandtitle
    void test4(){
        Question q = this.QuestionRepository.findByContentAndTitle("content1", "title1");
        assertEquals(1L, q.getId());
    }

    @Test//findByTitleLike
    void test5(){
        List<Question> all = this.QuestionRepository.findByTitleLike("title%");
        Question q = all.get(0);
        assertEquals("title1", q.getTitle());
    }
}
