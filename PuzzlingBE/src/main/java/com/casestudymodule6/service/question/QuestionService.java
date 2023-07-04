package com.casestudymodule6.service.question;

import com.casestudymodule6.model.question.Question;
import com.casestudymodule6.model.question.SearchQuestion;
import com.casestudymodule6.repository.IQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService implements IQuestionService
{

    @Autowired
    private IQuestionRepository questionRepository;
    @Override
    public Iterable<Question> findAll()
    {
        return questionRepository.findAll();
    }

    @Override
    public Optional<Question> findById(Long id) {
        return questionRepository.findById(id);
    }

    @Override
    public Question save(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public void remove(Long id)
    {
        questionRepository.deleteById(id);
    }

        public List<Question>searchAllFields(SearchQuestion searchQuestion) {
            String name = searchQuestion.getName();
            String category = searchQuestion.getCategory();
            String questionType = searchQuestion.getQuestionType();
            String level = searchQuestion.getLevel();
            if (name != null && name.isEmpty()) {
                name = null;
            }
            if (category != null && category.isEmpty()) {
                category = null;
            }
            if (questionType != null && questionType.isEmpty()) {
                questionType = null;
            }
            if (level != null && level.isEmpty()) {
                level = null;
            }
            if (name == null && category == null && questionType == null && level == null) {
                return questionRepository.findAll();
            }
            return questionRepository.searchQuestions(name, category, questionType, level);

        }
}
