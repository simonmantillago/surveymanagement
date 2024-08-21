package com.surveymanagement.login.infrastructure.loginUi;

import javax.swing.*;

import com.surveymanagement.categorycatalog.application.CreateCategoryCatalogUseCase;
import com.surveymanagement.categorycatalog.application.DeleteCategoryCatalogUseCase;
import com.surveymanagement.categorycatalog.application.FindAllCategoryCatalogUseCase;
import com.surveymanagement.categorycatalog.application.FindCategoryCatalogByCodeUseCase;
import com.surveymanagement.categorycatalog.application.UpdateCategoryCatalogUseCase;
import com.surveymanagement.categorycatalog.domain.service.CategoryCatalogService;
import com.surveymanagement.categorycatalog.infrastructure.CategoryCatalogRepository;
import com.surveymanagement.categorycatalog.infrastructure.categorycatalogui.CategoryCatalogUI;
import com.surveymanagement.chapter.application.CreateChapterUseCase;
import com.surveymanagement.chapter.application.DeleteChapterUseCase;
import com.surveymanagement.chapter.application.FindChapterByCodeUseCase;
import com.surveymanagement.chapter.application.UpdateChapterUseCase;
import com.surveymanagement.chapter.domain.service.ChapterService;
import com.surveymanagement.chapter.infrastructure.ChapterRepository;
import com.surveymanagement.chapter.infrastructure.chapterui.ChapterUI;
import com.surveymanagement.login.application.CheckUserRoleUseCase;
import com.surveymanagement.login.domain.entity.Login;
import com.surveymanagement.role.application.CreateRoleUseCase;
import com.surveymanagement.role.application.DeleteRoleUseCase;
import com.surveymanagement.role.application.FindRoleByNameUseCase;
import com.surveymanagement.role.application.UpdateRoleUseCase;
import com.surveymanagement.role.domain.service.RoleService;
import com.surveymanagement.role.infrastructure.RoleRepository;
import com.surveymanagement.role.infrastructure.roleUi.RoleUiController;
import com.surveymanagement.survey.application.CreateSurveyUseCase;
import com.surveymanagement.survey.application.DeleteSurveyUseCase;
import com.surveymanagement.survey.application.FindAllSurveyUseCase;
import com.surveymanagement.survey.application.FindSurveyByCodeUseCase;
import com.surveymanagement.survey.application.UpdateSurveyUseCase;
import com.surveymanagement.survey.domain.service.SurveyService;
import com.surveymanagement.survey.infrastructure.SurveyRepository;
import com.surveymanagement.survey.infrastructure.surveyui.SurveyUI;
import com.surveymanagement.user.application.CreateUserUseCase;
import com.surveymanagement.user.application.DeleteUserUseCase;
import com.surveymanagement.user.application.FindAllUserUseCase;
import com.surveymanagement.user.application.FindUserByIdUseCase;
import com.surveymanagement.user.application.UpdateUserUseCase;
import com.surveymanagement.user.domain.service.UserService;
import com.surveymanagement.user.infrastructure.UserRepository;
import com.surveymanagement.user.infrastructure.userUi.UserUiController;
import com.surveymanagement.userrole.application.CreateUserRoleUseCase;
import com.surveymanagement.userrole.application.DeleteUserRoleUseCase;
import com.surveymanagement.userrole.application.FindUserRoleByIdUseCase;
import com.surveymanagement.userrole.application.UpdateUserRoleUseCase;
import com.surveymanagement.userrole.domain.service.UserRoleService;
import com.surveymanagement.userrole.infrastructure.UserRoleRepository;
import com.surveymanagement.userrole.infrastructure.userRoleUi.UserRoleUiController;

import java.awt.*;
import java.util.Optional;

public class LoginUiController extends JFrame {

    private JTextField jTextField1; // Username
    private JPasswordField jTextField2; // Password

    private JButton createUser; // Register
    private JButton loginBtn; // Login
    private CreateUserUseCase createUserUseCase;
    private final CheckUserRoleUseCase checkUserRoleUseCase;

    public LoginUiController(CreateUserUseCase createUserUseCase, CheckUserRoleUseCase checkUserRoleUseCase) {
        this.createUserUseCase = createUserUseCase;
        this.checkUserRoleUseCase = checkUserRoleUseCase;
        
    }

    public void frmRegLogin() {
        initComponents();
        setVisible(true);
    }

    public void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login");
        setSize(500, 200);

        JLabel jLabel1 = new JLabel("Login");
        jLabel1.setFont(new Font("Segoe UI", Font.BOLD, 24));
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);

        jTextField1 = new JTextField();
        jTextField2 = new JPasswordField();

        createUser = new JButton("Register");
        loginBtn = new JButton("Login");

    
        createUser.addActionListener(e -> RegisterLoginUi.frmRegUser(createUserUseCase, this));
        createUser.addActionListener(e -> resetFields());
        loginBtn.addActionListener(e -> performLogin());

       
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); 
        gbc.fill = GridBagConstraints.HORIZONTAL;
    
    
        addComponent(jLabel1, 0, 0, 2);
        addComponent(new JLabel("Username:"), 1, 0);
        addComponent(jTextField1, 1, 1);
        addComponent(new JLabel("PassWord:"), 2, 0);
        addComponent(jTextField2, 2, 1);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(createUser);
        buttonPanel.add(loginBtn);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(buttonPanel, gbc);

        setLocationRelativeTo(null);
    }

    private void addComponent(Component component, int row, int col) {
        addComponent(component, row, col, 1);
    }

    private void addComponent(Component component, int row, int col, int width) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = col;
        gbc.gridy = row;
        gbc.gridwidth = width;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5); // Spacing between components
        add(component, gbc);
    }

    private void performLogin() {
        String username = jTextField1.getText();
        String password = new String(jTextField2.getPassword());
        Optional<Login> loginOptional = checkUserRoleUseCase.execute(username, password);

        if (loginOptional.isPresent()) {
            Login login = loginOptional.get();
            String roleName = login.getRolename();

            if ("Admin".equalsIgnoreCase(roleName)) {
                dispose();
                openAdminUi();
            } else if ("User".equalsIgnoreCase(roleName)) {
                openUserUi();
            } else {
                JOptionPane.showMessageDialog(null, "Unauthorized role or no Role Assigned", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Wrong Username or Password", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void openAdminUi() {
       
        createAndShowMainUI();
    }

    private void openUserUi() {
        JOptionPane.showMessageDialog(null, "Popo", "Error", JOptionPane.ERROR_MESSAGE);
    }

        
public static void createAndShowMainUI() {
        JFrame frame = new JFrame("Survey Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 400);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

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

        JButton btnUserRoles = createStyledButton("User Roles", buttonSize, buttonFont);
        btnUserRoles.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnUserRoles.addActionListener(e -> {
            frame.setVisible(false);
            openUserRoleUiController();
        });

        buttonPanel.add(btnUserRoles);
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
    private static void openUserRoleUiController() {
        UserRoleService userRoleService = new UserRoleRepository();

        CreateUserRoleUseCase createUserRoleUseCase = new CreateUserRoleUseCase(userRoleService);
        FindUserRoleByIdUseCase findUserRoleByIdUseCase = new FindUserRoleByIdUseCase(userRoleService);
        UpdateUserRoleUseCase updateUserRoleUseCase = new UpdateUserRoleUseCase(userRoleService);
        DeleteUserRoleUseCase deleteUserRoleUseCase = new DeleteUserRoleUseCase(userRoleService);
  


        UserRoleUiController userRoleUiController = new UserRoleUiController(createUserRoleUseCase, findUserRoleByIdUseCase, updateUserRoleUseCase, deleteUserRoleUseCase);
        userRoleUiController.showCrudOptions();
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
    FindChapterByCodeUseCase findChapterByCodeUseCase = new FindChapterByCodeUseCase(surveyService);
    UpdateChapterUseCase updateChapterUseCase = new UpdateChapterUseCase(surveyService);
    

    ChapterUI chapterUI = new ChapterUI(createChapterUseCase, deleteChapterUseCase, findChapterByCodeUseCase, updateChapterUseCase);
    chapterUI.showCrudOptions();
    }

    private static JButton createStyledButton(String text, Dimension size, Font font) {
        JButton button = new JButton(text);
        button.setPreferredSize(size);
        button.setMaximumSize(size);
        button.setFont(font);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        return button;
    }
    private void resetFields() {
        jTextField1.setText("");
        jTextField2.setText("");
    }

}