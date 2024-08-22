package com.surveymanagement.subresponseoption.application;

import com.surveymanagement.subresponseoption.domain.entity.SubResponseOption;
import com.surveymanagement.subresponseoption.domain.service.SubResponseOptionService;

import java.util.List;

public class FindSubResponseOptionByQuestionUseCase {
    private final SubResponseOptionService subResponseOptionService;

    public FindSubResponseOptionByQuestionUseCase(SubResponseOptionService subResponseOptionService) {
        this.subResponseOptionService = subResponseOptionService;
    }

    public List<SubResponseOption> execute(int subResponseOptionId) {
        return subResponseOptionService.findSubResponseOptionByResponseOption(subResponseOptionId);
    }
}
