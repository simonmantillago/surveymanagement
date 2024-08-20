package com.surveymanagement.categorycatalog.infraestructure.catalogrepositoryui;

import javax.swing.JFrame;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


import com.surveymanagement.categorycatalog.application.CreateCategoryCatalogUseCase;
import com.surveymanagement.categorycatalog.application.DeleteCategoryCatalogUseCase;
import com.surveymanagement.categorycatalog.application.FindAllCategoryCatalogUseCase;
import com.surveymanagement.categorycatalog.application.FindCategoryCatalogByCodeUseCase;
import com.surveymanagement.categorycatalog.application.FindCategoryCatalogByNameUseCase;
import com.surveymanagement.categorycatalog.application.UpdateCategoryCatalogUseCase;

import com.surveymanagement.categorycatalog.infraestructure.catalogrepositoryui.AddCategoryCatalogUI;
import com.surveymanagement.categorycatalog.infraestructure.catalogrepositoryui.DeleteCategoryCatalogUI;
import com.surveymanagement.categorycatalog.infraestructure.catalogrepositoryui.FindCategoryCatalogUI;
import com.surveymanagement.categorycatalog.infraestructure.catalogrepositoryui.UpdateCategoryCatalogUI;

public class CategoryCatalogUI {
    private final CreateCategoryCatalogUseCase createCategoryCatalogUseCase;
    private final DeleteCategoryCatalogUseCase deleteCategoryCatalogUseCase;
    private final FindAllCategoryCatalogUseCase findAllCategoryCatalogUseCase;
    private final FindCategoryCatalogByCodeUseCase findCategoryCatalogByCodeUseCase;
    private final FindCategoryCatalogByNameUseCase findCategoryCatalogByNameUseCase;
    private final UpdateCategoryCatalogUseCase updateCategoryCatalogUseCase;
    private JFrame frame;

    public CategoryCatalogUI(
            CreateCategoryCatalogUseCase createCategoryCatalogUseCase,
            DeleteCategoryCatalogUseCase deleteCategoryCatalogUseCase,
            FindAllCategoryCatalogUseCase findAllCategoryCatalogUseCase,
            FindCategoryCatalogByCodeUseCase findCategoryCatalogByCodeUseCase,
            FindCategoryCatalogByNameUseCase findCategoryCatalogByNameUseCase,
            UpdateCategoryCatalogUseCase updateCategoryCatalogUseCase) {
        this.createCategoryCatalogUseCase = createCategoryCatalogUseCase;
        this.deleteCategoryCatalogUseCase = deleteCategoryCatalogUseCase;
        this.findAllCategoryCatalogUseCase = findAllCategoryCatalogUseCase;
        this.findCategoryCatalogByCodeUseCase = findCategoryCatalogByCodeUseCase;
        this.findCategoryCatalogByNameUseCase = findCategoryCatalogByNameUseCase;
        this.updateCategoryCatalogUseCase = updateCategoryCatalogUseCase;
    }

    public void showCrudOptions(){

        frame = new JFrame("Category Catalogs");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setLocationRelativeTo(null);
    
                // Crear un panel principal con BoxLayout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Añadir título grande
        JLabel titleLabel = new JLabel("Countries");
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

        // Botón Create CategoryCatalog
        JButton btnCreate = createStyledButton("Create CategoryCatalog", buttonSize, buttonFont);
        btnCreate.addActionListener(e -> {
            AddCategoryCatalogUI addcategorycatalogUi = new AddCategoryCatalogUI(createCategoryCatalogUseCase, this);
            addcategorycatalogUi.frmRegCategoryCatalog();
            frame.setVisible(false);
        });
        buttonPanel.add(btnCreate);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        JButton btnUpdate = createStyledButton("Update CategoryCatalog", buttonSize, buttonFont);
        btnUpdate.addActionListener(e -> {
            UpdateCategoryCatalogUI updateCategoryCatalogUi = new UpdateCategoryCatalogUI(updateCategoryCatalogUseCase, findCategoryCatalogByNameUseCase, this);
            updateCategoryCatalogUi.frmUpdateCategoryCatalog();
            frame.setVisible(false);
        });
        buttonPanel.add(btnUpdate);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        JButton btnFind = createStyledButton("Find CategoryCatalog", buttonSize, buttonFont);
        btnFind.addActionListener(e -> {
            FindCategoryCatalogByNameUi findCategoryCatalogByNameUi = new FindCategoryCatalogByNameUi(findCategoryCatalogByNameUseCase, this);
            findCategoryCatalogByNameUi.showFindCategoryCatalog();
            frame.setVisible(false);
        });
        buttonPanel.add(btnFind);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        JButton btnDelete = createStyledButton("Delete Customer", buttonSize, buttonFont);
        btnDelete.addActionListener(e -> {
            DeleteCategoryCatalogUi deleteCustomerUi = new DeleteCategoryCatalogUi(deleteCategoryCatalogUseCase, this);
            deleteCustomerUi.showDeleteCustomer();
            frame.setVisible(false);
        });
        buttonPanel.add(btnDelete);

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

    

