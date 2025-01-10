package com.mysite.lect.Question;

import com.mysite.lect.User.SiteUser;
import com.mysite.lect.Util.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    public Page<Question> getList(int page){
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createTime"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        return this.questionRepository.findAll(pageable);
    }

    public Question getQuestion(Long id){
        Optional<Question> question = this.questionRepository.findById(id);
        if(question.isPresent()){
            return question.get();
        } else{
            throw new DataNotFoundException("Question not found");
        }
    }

    public void create(String title, String content, SiteUser user){
        Question question = new Question();
        question.setTitle(title);
        question.setContent(content);
        question.setCreateTime(LocalDateTime.now());
        question.setAuthor(user);
        this.questionRepository.save(question);
    }
}
