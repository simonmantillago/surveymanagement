package com.surveymanagement.userrole.infrastructure.userRoleUi;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.surveymanagement.Main;
import com.surveymanagement.userrole.application.CreateUserRoleUseCase;
import com.surveymanagement.userrole.application.DeleteUserRoleUseCase;
import com.surveymanagement.userrole.application.FindUserRoleByIdUseCase;
import com.surveymanagement.userrole.application.UpdateUserRoleUseCase;

public class UserRoleUiController {
    private final CreateUserRoleUseCase createUserRoleUseCase;
    private final FindUserRoleByIdUseCase findUserRoleByIdUseCase;
    private final UpdateUserRoleUseCase updateUserRoleUseCase;
    private final DeleteUserRoleUseCase deleteUserRoleUseCase;
    private JFrame frame;

    



    public UserRoleUiController(CreateUserRoleUseCase createUserRoleUseCase, FindUserRoleByIdUseCase findUserRoleByIdUseCase,
            UpdateUserRoleUseCase updateUserRoleUseCase, DeleteUserRoleUseCase deleteUserRoleUseCase) {
        this.createUserRoleUseCase = createUserRoleUseCase;
        this.findUserRoleByIdUseCase = findUserRoleByIdUseCase;
        this.updateUserRoleUseCase = updateUserRoleUseCase;
        this.deleteUserRoleUseCase = deleteUserRoleUseCase;
    }

    public void showCrudOptions() {
        frame = new JFrame("UserRoles");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setLocationRelativeTo(null);

        // Crear un panel principal con BoxLayout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Añadir título grande
        JLabel titleLabel = new JLabel("UserRoles");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        // Crear un panel para los botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Estilo común para los botones
        Dimension buttonSize = new Dimension(250, 50);
        Font buttonFont = new Font("Arial", Font.BOLD, 18);

        // Botón Create UserRole
        JButton btnCreate = createStyledButton("Create UserRole", buttonSize, buttonFont);
        btnCreate.addActionListener(e -> {
            CreateUserRoleUi userRoleUi = new CreateUserRoleUi(createUserRoleUseCase, this);
            userRoleUi.frmRegUserRole();
            frame.setVisible(false);
        });
        buttonPanel.add(btnCreate);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        JButton btnUpdate = createStyledButton("Update UserRole", buttonSize, buttonFont);
        btnUpdate.addActionListener(e -> {
            UpdateUserRoleUi updateUserRoleUi = new UpdateUserRoleUi(updateUserRoleUseCase, findUserRoleByIdUseCase, this);
            updateUserRoleUi.frmUpdateUserRole();
            frame.setVisible(false);
        });
        buttonPanel.add(btnUpdate);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        JButton btnFind = createStyledButton("Find UserRole", buttonSize, buttonFont);
        btnFind.addActionListener(e -> {
            FindUserRoleByNameUi findUserRoleByNameUi = new FindUserRoleByNameUi(findUserRoleByIdUseCase, this);
            findUserRoleByNameUi.showFindUserRole();
            frame.setVisible(false);
        });
        buttonPanel.add(btnFind);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));


        JButton btnDelete = createStyledButton("Delete UserRoler", buttonSize, buttonFont);
        btnDelete.addActionListener(e -> {
            DeleteUserRoleUi deleteCustomerUi = new DeleteUserRoleUi(deleteUserRoleUseCase, this);
            deleteCustomerUi.showDeleteCustomer();
            frame.setVisible(false);
        });
        buttonPanel.add(btnDelete);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        JButton btnBackToMain = createStyledButton("Back to Main Menu", buttonSize, buttonFont);
        btnBackToMain.addActionListener(e -> {
            frame.dispose(); 
            Main.createAndShowMainUI(); 
        });
        buttonPanel.add(btnBackToMain);

        mainPanel.add(buttonPanel);
        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private JButton createStyledButton(String text, Dimension size, Font font) {
        JButton button = new JButton(text);
        button.setPreferredSize(size);
        button.setMaximumSize(size);
        button.setFont(font);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        return button;
    }
}
