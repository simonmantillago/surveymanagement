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
import com.surveymanagement.categorycatalog.application.FindCategoryCatalogByNameUseCase;
import com.surveymanagement.categorycatalog.application.UpdateCategoryCatalogUseCase;
import com.surveymanagement.categorycatalog.domain.service.CategoryCatalogService;
import com.surveymanagement.categorycatalog.infraestructure.CategoryCatalogRepository;
import com.surveymanagement.categorycatalog.infraestructure.catalogrepositoryui.CategoryCatalogUI;



import javax.swing.*;
import java.awt.*;

public class Main {


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createAndShowMainUI();
        });
    }

    private static void createAndShowMainUI() {
        JFrame frame = new JFrame("Survey Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
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

        JButton btnCategoryCatalog = createStyledButton("Category Catalog", buttonSize, buttonFont);
        btnCategoryCatalog.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnCategoryCatalog.addActionListener(e -> {
            frame.setVisible(false);
            openCategoryCatalogUI();
        });


        buttonPanel.add(btnRoles);
        buttonPanel.add(btnCategoryCatalog);
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
    FindCategoryCatalogByNameUseCase findCategoryCatalogByNameUseCase = new FindCategoryCatalogByNameUseCase(categorycatalogService);
    UpdateCategoryCatalogUseCase updateCategoryCatalogUseCase = new UpdateCategoryCatalogUseCase(categorycatalogService);
    

    CategoryCatalogUI categorycatalogUI = new CategoryCatalogUI(createCategoryCatalogUseCase, deleteCategoryCatalogUseCase, findAllCategoryCatalogUseCase, findCategoryCatalogByCodeUseCase, findCategoryCatalogByNameUseCase, updateCategoryCatalogUseCase);
    categorycatalogUI.showCrudOptions();
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
