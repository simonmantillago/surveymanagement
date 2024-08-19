package com.surveymanagement;

import com.surveymanagement.role.application.CreateRoleUseCase;
import com.surveymanagement.role.application.DeleteRoleUseCase;
import com.surveymanagement.role.application.FindRoleByNameUseCase;
import com.surveymanagement.role.application.UpdateRoleUseCase;
import com.surveymanagement.role.domain.service.RoleService;
import com.surveymanagement.role.infrastructure.RoleRepository;
import com.surveymanagement.role.infrastructure.roleUi.RoleUiController;

import com.surveymanagement.user.application.CreateUserUseCase;
import com.surveymanagement.user.application.DeleteUserUseCase;
import com.surveymanagement.user.application.FindAllUserUseCase;
import com.surveymanagement.user.application.FindUserByIdUseCase;
import com.surveymanagement.user.application.UpdateUserUseCase;
import com.surveymanagement.user.domain.service.UserService;
import com.surveymanagement.user.infrastructure.UserRepository;
import com.surveymanagement.user.infrastructure.userUi.UserUiController;



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


        JButton btnRoles = createStyledButton("Roles", buttonSize, buttonFont);
        btnRoles.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnRoles.addActionListener(e -> {
            frame.setVisible(false);
            openRoleUiController();
        });

        buttonPanel.add(btnRoles);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));


        JButton btnUsers = createStyledButton("Users", buttonSize, buttonFont);
        btnUsers.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnUsers.addActionListener(e -> {
            frame.setVisible(false);
            openUserUiController();
        });

        buttonPanel.add(btnUsers);
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

    private static void openUserUiController() {
    UserService userService = new UserRepository();

    CreateUserUseCase createUserUseCase = new CreateUserUseCase(userService);
    FindUserByIdUseCase findUserByIdUseCase = new FindUserByIdUseCase(userService);
    UpdateUserUseCase updateUserUseCase = new UpdateUserUseCase(userService);
    DeleteUserUseCase deleteUserUseCase = new DeleteUserUseCase(userService);
    FindAllUserUseCase findAllUserUseCase = new FindAllUserUseCase(userService);


    UserUiController userUiController = new UserUiController(createUserUseCase, findUserByIdUseCase, updateUserUseCase, deleteUserUseCase, findAllUserUseCase);
    userUiController.showCrudOptions();
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
