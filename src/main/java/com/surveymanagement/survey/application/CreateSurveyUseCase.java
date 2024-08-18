package com.surveymanagement.survey.application;

import com.surveymanagement.survey.domain.entity.Survey;
import com.surveymanagement.survey.domain.service.SurveyService;

public class CreateSurveyUseCase {
    private final SurveyService modeAdministrationService;

    public CreateSurveyUseCase(SurveyService modeAdministrationService){
        this.modeAdministrationService = modeAdministrationService;
    }

    public void execute(Survey Survey){
        modeAdministrationService.createSurvey(Survey);
    }
}
