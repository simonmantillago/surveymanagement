package com.surveymanagement.subresponseoption.application;

import com.surveymanagement.responseoption.domain.entity.ResponseOption;
import com.surveymanagement.responseoption.domain.service.ResponseOptionService;

import java.util.Optional;

public class FindSubResponseOptionByIdUseCase {
    private final ResponseOptionService responseOptionService;

    public FindSubResponseOptionByIdUseCase(ResponseOptionService responseOptionService) {
        this.responseOptionService = responseOptionService;
    }

    public Optional<ResponseOption> execute(int questionId) {
        return responseOptionService.findResponseOptionById(questionId);
    }
}
