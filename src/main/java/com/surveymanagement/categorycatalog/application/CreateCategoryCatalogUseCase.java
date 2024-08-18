package com.surveymanagement.categorycatalog.application;

import com.surveymanagement.categorycatalog.domain.entity.CategoryCatalog;
import com.surveymanagement.categorycatalog.domain.service.CategoryCatalogService;

public class CreateCategoryCatalogUseCase {
    private final CategoryCatalogService modeAdministrationService;

    public CreateCategoryCatalogUseCase(CategoryCatalogService modeAdministrationService){
        this.modeAdministrationService = modeAdministrationService;
    }

    public void execute(CategoryCatalog CategoryCatalog){
        modeAdministrationService.createCategoryCatalog(CategoryCatalog);
    }
}
