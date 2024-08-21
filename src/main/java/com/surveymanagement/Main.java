package com.surveymanagement;

import com.surveymanagement.role.application.CreateRoleUseCase;
import com.surveymanagement.role.application.DeleteRoleUseCase;
import com.surveymanagement.role.application.FindRoleByNameUseCase;
import com.surveymanagement.role.application.UpdateRoleUseCase;
import com.surveymanagement.role.domain.service.RoleService;
import com.surveymanagement.role.infrastructure.RoleRepository;
import com.surveymanagement.role.infrastructure.roleUi.RoleUiController;

import com.surveymanagement.categorycatalog.application.CreateCategoryCatalogUseCase;
import com.surveymanagement.categorycatalog.application.DeleteCategoryCatalogUseCase;
import com.surveymanagement.categorycatalog.application.FindAllCategoryCatalogUseCase;
import com.surveymanagement.categorycatalog.application.FindCategoryCatalogByCodeUseCase;
import com.surveymanagement.categorycatalog.application.UpdateCategoryCatalogUseCase;
import com.surveymanagement.categorycatalog.domain.service.CategoryCatalogService;
import com.surveymanagement.categorycatalog.infrastructure.CategoryCatalogRepository;
import com.surveymanagement.categorycatalog.infrastructure.categorycatalogui.CategoryCatalogUI;
import com.surveymanagement.survey.application.CreateSurveyUseCase;
import com.surveymanagement.survey.application.DeleteSurveyUseCase;
import com.surveymanagement.survey.application.FindAllSurveyUseCase;
import com.surveymanagement.survey.application.FindSurveyByCodeUseCase;
import com.surveymanagement.survey.application.UpdateSurveyUseCase;
import com.surveymanagement.survey.domain.service.SurveyService;
import com.surveymanagement.survey.infrastructure.SurveyRepository;
import com.surveymanagement.survey.infrastructure.surveyui.SurveyUI;
import com.surveymanagement.chapter.domain.service.ChapterService;
import com.surveymanagement.chapter.infrastructure.ChapterRepository;
import com.surveymanagement.chapter.infrastructure.chapterui.ChapterUI;
import com.surveymanagement.chapter.application.CreateChapterUseCase;
import com.surveymanagement.chapter.application.DeleteChapterUseCase;
import com.surveymanagement.chapter.application.FindAllChapterUseCase;
import com.surveymanagement.chapter.application.FindChapterByCodeUseCase;
import com.surveymanagement.chapter.application.UpdateChapterUseCase;

import javax.swing.*;
import java.awt.*;

public class Main {


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createAndShowMainUI();
        });
    }

    public static void createAndShowMainUI() {
        JFrame frame = new JFrame("Survey Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Estilo común para los botones
        Dimension buttonSize = new Dimension(250, 50);
        Font buttonFont = new Font("Arial", Font.BOLD, 18);


        JButton btnRoles = createStyledButton("Create Role", buttonSize, buttonFont);
        btnRoles.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnRoles.addActionListener(e -> {
            frame.setVisible(false);
            openRoleUiController();
        });
        buttonPanel.add(btnRoles);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        JButton btnCategoryCatalog = createStyledButton("Category Catalog", buttonSize, buttonFont);
        btnCategoryCatalog.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnCategoryCatalog.addActionListener(e -> {
            frame.setVisible(false);
            openCategoryCatalogUI();
        });
        buttonPanel.add(btnCategoryCatalog);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        JButton btnSurvey = createStyledButton("Surveys", buttonSize, buttonFont);
        btnSurvey.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnSurvey.addActionListener(e -> {
            frame.setVisible(false);
            openSurveyUI();
        });
        buttonPanel.add(btnSurvey);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        JButton btnChapter = createStyledButton("Chapter", buttonSize, buttonFont);
        btnChapter.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnChapter.addActionListener(e -> {
            frame.setVisible(false);
            openChapterUI();
        });
        buttonPanel.add(btnChapter);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        mainPanel.add(buttonPanel);
        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private static void openRoleUiController() {
    RoleService roleService = new RoleRepository();

    CreateRoleUseCase createRoleUseCase = new CreateRoleUseCase(roleService);
    FindRoleByNameUseCase findRoleByNameUseCase = new FindRoleByNameUseCase(roleService);
    UpdateRoleUseCase updateRoleUseCase = new UpdateRoleUseCase(roleService);
    DeleteRoleUseCase deleteRoleUseCase = new DeleteRoleUseCase(roleService);


    RoleUiController roleUiController = new RoleUiController(createRoleUseCase, findRoleByNameUseCase, updateRoleUseCase, deleteRoleUseCase);
    roleUiController.showCrudOptions();
    }

    private static void openCategoryCatalogUI() {
    CategoryCatalogService categorycatalogService = new CategoryCatalogRepository();

    CreateCategoryCatalogUseCase createCategoryCatalogUseCase = new CreateCategoryCatalogUseCase(categorycatalogService);
    DeleteCategoryCatalogUseCase deleteCategoryCatalogUseCase = new DeleteCategoryCatalogUseCase(categorycatalogService);
    FindAllCategoryCatalogUseCase findAllCategoryCatalogUseCase = new FindAllCategoryCatalogUseCase(categorycatalogService);
    FindCategoryCatalogByCodeUseCase findCategoryCatalogByCodeUseCase = new FindCategoryCatalogByCodeUseCase(categorycatalogService);
    UpdateCategoryCatalogUseCase updateCategoryCatalogUseCase = new UpdateCategoryCatalogUseCase(categorycatalogService);
    

    CategoryCatalogUI categorycatalogUI = new CategoryCatalogUI(createCategoryCatalogUseCase, deleteCategoryCatalogUseCase, findAllCategoryCatalogUseCase, findCategoryCatalogByCodeUseCase, updateCategoryCatalogUseCase);
    categorycatalogUI.showCrudOptions();
    }

    private static void openSurveyUI() {
    SurveyService surveyService = new SurveyRepository();

    CreateSurveyUseCase createSurveyUseCase = new CreateSurveyUseCase(surveyService);
    DeleteSurveyUseCase deleteSurveyUseCase = new DeleteSurveyUseCase(surveyService);
    FindAllSurveyUseCase findAllSurveyUseCase = new FindAllSurveyUseCase(surveyService);
    FindSurveyByCodeUseCase findSurveyByCodeUseCase = new FindSurveyByCodeUseCase(surveyService);
    UpdateSurveyUseCase updateSurveyUseCase = new UpdateSurveyUseCase(surveyService);
    

    SurveyUI surveyUI = new SurveyUI(createSurveyUseCase, deleteSurveyUseCase, findAllSurveyUseCase, findSurveyByCodeUseCase, updateSurveyUseCase);
    surveyUI.showCrudOptions();
    }

    private static void openChapterUI() {
    ChapterService surveyService = new ChapterRepository();

    CreateChapterUseCase createChapterUseCase = new CreateChapterUseCase(surveyService);
    DeleteChapterUseCase deleteChapterUseCase = new DeleteChapterUseCase(surveyService);
    FindAllChapterUseCase findAllChapterUseCase = new FindAllChapterUseCase(surveyService);
    FindChapterByCodeUseCase findChapterByCodeUseCase = new FindChapterByCodeUseCase(surveyService);
    UpdateChapterUseCase updateChapterUseCase = new UpdateChapterUseCase(surveyService);
    

    ChapterUI surveyUI = new ChapterUI(createChapterUseCase, deleteChapterUseCase, findAllChapterUseCase, findChapterByCodeUseCase, updateChapterUseCase);
    surveyUI.showCrudOptions();
    }

    private static JButton createStyledButton(String text, Dimension size, Font font) {
        JButton button = new JButton(text);
        button.setPreferredSize(size);
        button.setMaximumSize(size);
        button.setFont(font);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        return button;
    }


}
