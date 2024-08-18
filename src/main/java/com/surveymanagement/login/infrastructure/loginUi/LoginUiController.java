package com.surveymanagement.login.infrastructure.loginUi;

import com.surveymanagement.login.infrastructure.loginUi.RegisterLoginUi;

import javax.swing.*;
import java.awt.*;

public class LoginUiController extends JFrame {

    private JTextField jTextField1; // Username
    private JPasswordField jTextField2; // Password

    private JButton createUser; // Login
    private JButton loginBtn; // Login
    

    public void frmRegLogin() {
        initComponents();
        setVisible(true);
    }

    public void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login");
        setSize(250, 500);

        JLabel jLabel1 = new JLabel("Login");
        jLabel1.setFont(new Font("Segoe UI", Font.BOLD, 24));
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);

        jTextField1 = new JTextField();
        jTextField2 = new JPasswordField();

        
        createUser = new JButton("Register");
        loginBtn = new JButton("Login");

        createUser.addActionListener(e ->RegisterLoginUi.frmRegUser() ); //Corregirrrrrrrr
    
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

    private void resetFields() {
        jTextField1.setText("");
        jTextField2.setText("");

    }

}
