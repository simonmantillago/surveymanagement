package com.surveymanagement.question.application;

import java.util.Optional;

import com.surveymanagement.question.domain.entity.Question;
import com.surveymanagement.question.domain.service.QuestionService;

public class FindQuestionByNameUseCase {
    private final QuestionService questionService;

    public FindQuestionByNameUseCase(QuestionService questionService) {
        this.questionService = questionService;
    }

    public Optional<Question> findQuestionByName(String name, int survey_id) {
        return questionService.findQuestionByName(name, survey_id);
    }
}
