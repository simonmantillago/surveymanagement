package com.surveymanagement;

import com.surveymanagement.role.application.CreateRoleUseCase;
import com.surveymanagement.role.application.DeleteRoleUseCase;
import com.surveymanagement.role.application.FindRoleByNameUseCase;
import com.surveymanagement.role.application.UpdateRoleUseCase;
import com.surveymanagement.role.domain.service.RoleService;
import com.surveymanagement.role.infrastructure.RoleRepository;
import com.surveymanagement.role.infrastructure.roleUi.RoleUiController;

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

        JButton btnManageRoles = new JButton("Manage Roles");
        btnManageRoles.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnManageRoles.addActionListener(e -> {
            frame.setVisible(false);
            openRoleUiController();
        });

        mainPanel.add(btnManageRoles);

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
}
