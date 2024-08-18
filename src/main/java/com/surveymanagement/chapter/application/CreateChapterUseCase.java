package com.surveymanagement.chapter.application;

import com.surveymanagement.chapter.domain.entity.Chapter;
import com.surveymanagement.chapter.domain.service.ChapterService;

public class CreateChapterUseCase {
    private final ChapterService modeAdministrationService;

    public CreateChapterUseCase(ChapterService modeAdministrationService){
        this.modeAdministrationService = modeAdministrationService;
    }

    public void execute(Chapter Chapter){
        modeAdministrationService.createChapter(Chapter);
    }
}
