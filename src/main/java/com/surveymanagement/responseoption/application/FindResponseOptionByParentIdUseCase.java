package com.surveymanagement.responseoption.application;

import com.surveymanagement.responseoption.domain.entity.ResponseOption;
import com.surveymanagement.responseoption.domain.service.ResponseOptionService;

import java.util.List;

public class FindResponseOptionByParentIdUseCase {
private final ResponseOptionService responseOptionService;

    public FindResponseOptionByParentIdUseCase(ResponseOptionService responseOptionService) {
        this.responseOptionService = responseOptionService;
    }

    public List<ResponseOption> execute(int ParentId) {
        return responseOptionService.findResponseOptionByQuestion(ParentId);
    }
}
