package com.mysite.lect;

import com.mysite.lect.Answer.Answer;
import com.mysite.lect.Answer.AnswerRepository;
import com.mysite.lect.Question.Question;
import com.mysite.lect.Question.QuestionRepository;
import com.mysite.lect.Question.QuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class LectApplicationTests {

    @Autowired
    private QuestionRepository QuestionRepository;
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private QuestionService qestionService;

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

    @Test//Edit
    void test6(){
        Optional<Question> oq = this.QuestionRepository.findById(1L);
        assertTrue(oq.isPresent());
        Question q = oq.get();
        q.setTitle("Edited title");
        this.QuestionRepository.save(q);
    }

    @Test//Delete
    void test7(){
        assertEquals(2,this.QuestionRepository.count());
        Optional<Question> oq = this.QuestionRepository.findById(1L);
        assertTrue(oq.isPresent());
        Question q = oq.get();
        this.QuestionRepository.delete(q);
        assertEquals(1, this.QuestionRepository.count());
    }

    @Test//saveAnswer
    void test8(){
       Optional<Question> oq = this.QuestionRepository.findById(2L);
       assertTrue(oq.isPresent());
       Question q = oq.get();

       Answer a = new Answer();
       a.setContent("Answer1");
       a.setQuestion(q);
       a.setCreateTime(LocalDateTime.now());
       this.answerRepository.save(a);

    }

    @Test//findAnswer
    void test9(){
        Optional<Answer> oa = this.answerRepository.findById(1L);
        assertTrue(oa.isPresent());
        Answer a = oa.get();
        assertEquals(2L,a.getQuestion().getId());
    }

    @Test//transactional
    @Transactional
    void test10(){
        Optional<Question> oq = this.QuestionRepository.findById(2L);
        assertTrue(oq.isPresent());
        Question q = oq.get();

        List<Answer> answerList = q.getAnswerList();

        assertEquals(1, answerList.size());
        assertEquals("Answer1", answerList.get(0).getContent());
    }

    @Test
    void testJpa11(){
        for(int i=1; i<=150; i++){
            String title = String.format("Test data version %d", i);
            String content = String.format("Test data: content %d", i);
            this.qestionService.create(title, content,null);
        }
    }
}
