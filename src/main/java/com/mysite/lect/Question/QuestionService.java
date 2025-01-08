package com.mysite.lect.Question;

import com.mysite.lect.Util.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    public List<Question> getList(){
        return this.questionRepository.findAll();
    }

    public Question getQuestion(Long id){
        Optional<Question> question = this.questionRepository.findById(id);
        if(question.isPresent()){
            return question.get();
        } else{
            throw new DataNotFoundException("Question not found");
        }
    }

    public void create(String title, String content){
        Question question = new Question();
        question.setTitle(title);
        question.setContent(content);
        question.setCreateTime(LocalDateTime.now());
        this.questionRepository.save(question);
    }
}
