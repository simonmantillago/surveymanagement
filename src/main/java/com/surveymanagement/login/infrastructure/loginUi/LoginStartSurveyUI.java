package com.surveymanagement.login.infrastructure.loginUi;
import com.surveymanagement.chapter.application.FindChapterBySurveyUseCase;
import com.surveymanagement.chapter.domain.entity.Chapter;
import com.surveymanagement.chapter.domain.service.ChapterService;
import com.surveymanagement.chapter.infrastructure.ChapterRepository;
import com.surveymanagement.question.application.FindQuestionByChapterUseCase;
import com.surveymanagement.question.domain.entity.Question;
import com.surveymanagement.question.domain.service.QuestionService;
import com.surveymanagement.question.infrastructure.QuestionRepository;
import com.surveymanagement.responseoption.application.FindResponseOptionByQuestionUseCase;
import com.surveymanagement.responseoption.domain.entity.ResponseOption;
import com.surveymanagement.responseoption.domain.service.ResponseOptionService;
import com.surveymanagement.responseoption.infrastructure.ResponseOptionRepository;
import com.surveymanagement.subresponseoption.application.FindSubResponseOptionByResponseOptionUseCase;
import com.surveymanagement.subresponseoption.domain.entity.SubResponseOption;
import com.surveymanagement.subresponseoption.domain.service.SubResponseOptionService;
import com.surveymanagement.subresponseoption.infrastructure.SubResponseOptionRepository;
import com.surveymanagement.survey.application.FindSurveyByCodeUseCase;
import com.surveymanagement.survey.domain.entity.Survey;
import com.surveymanagement.survey.domain.service.SurveyService;
import com.surveymanagement.survey.infrastructure.SurveyRepository;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class LoginStartSurveyUI extends JFrame {
    private JButton Next, Goback;
    private Map<Integer, JLabel> labelMap = new HashMap<>();
    private Map<Integer, JCheckBox> responseOptionCheckboxMap = new HashMap<>();
    private Map<Integer, List<JCheckBox>> subResponseOptionCheckboxMap = new HashMap<>();
    private JScrollPane scrollPane;

    public LoginStartSurveyUI() {
    }

    public void startSurvey(int surveyId){
        initComponents(surveyId);
        setVisible(true);
    }

    private void initComponents(int surveyId){
        SurveyService surveyService = new SurveyRepository();
        FindSurveyByCodeUseCase findSurveyByCodeUseCase = new FindSurveyByCodeUseCase(surveyService);
        Optional<Survey> foundSurvey = findSurveyByCodeUseCase.findSurveyByCode(surveyId);

        ChapterService chapterService = new ChapterRepository();
        FindChapterBySurveyUseCase findChapterBySurveyUseCase = new FindChapterBySurveyUseCase(chapterService);
        List<Chapter> chapters = findChapterBySurveyUseCase.findChapterBySurvey(surveyId);

        QuestionService questionService = new QuestionRepository();
        FindQuestionByChapterUseCase findQuestionByChapterUseCase = new FindQuestionByChapterUseCase(questionService);

        ResponseOptionService responseOptionService = new ResponseOptionRepository();
        FindResponseOptionByQuestionUseCase responseOptionByQuestionUseCase = new FindResponseOptionByQuestionUseCase(responseOptionService);

        SubResponseOptionService subResponseOptionService = new SubResponseOptionRepository();
        FindSubResponseOptionByResponseOptionUseCase findSubResponseOptionByResponseOptionUseCase = new FindSubResponseOptionByResponseOptionUseCase(subResponseOptionService);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Create Role");
        setSize(800, 600); // Tamaño inicial del JFrame
        setIconImage(new ImageIcon(getClass().getClassLoader().getResource("images/survey.png")).getImage());

        // Crear y configurar el título
        JLabel title = new JLabel(foundSurvey.get().getName());
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        // Crear el JPanel de contenido
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int row = 0;
        for (Chapter chapter : chapters) {
            JLabel label = new JLabel(chapter.getChapter_title());
            labelMap.put(chapter.getId(), label);
            gbc.gridy = row++;
            gbc.gridx = 0;
            contentPanel.add(label, gbc);
            List<Question> questions = findQuestionByChapterUseCase.findQuestionByChapter(chapter.getId());
            for (Question question : questions) {
                JLabel labelquestion = new JLabel(question.getQuestion_text());
                labelMap.put(question.getId(), labelquestion);
                gbc.gridy = row++;
                gbc.gridx = 1;
                contentPanel.add(labelquestion, gbc);
                if ("single_choice".equals(question.getResponse_type())){

                    ButtonGroup buttonGroup = new ButtonGroup(); // ButtonGroup para las opciones principales
                    List<ResponseOption> responseOptions = responseOptionByQuestionUseCase.execute(question.getId());
                
                    for (ResponseOption responseOption : responseOptions) {
                        if (responseOption.getParentResponseId() == 0) {  // Es una opción primaria
                            JRadioButton radioButton = new JRadioButton(responseOption.getOptionText());
                            gbc.gridy = row++;
                            gbc.gridx = 1;
                            contentPanel.add(radioButton, gbc);
                            buttonGroup.add(radioButton);
                
                            // Iterar sobre las opciones para encontrar subopciones
                            for (ResponseOption subOption : responseOptions) {
                                if (subOption.getParentResponseId() == responseOption.getId()) {  // Subopción encontrada
                                    JRadioButton subRadioButton = new JRadioButton(subOption.getOptionText());
                                    subRadioButton.setVisible(false);
                                    gbc.gridy = row++;
                                    gbc.gridx = 2;
                                    contentPanel.add(subRadioButton, gbc);
                                    
                                    // No agregar subRadioButton al mismo ButtonGroup para evitar deseleccionar el radioButton principal
                
                                    // Crear un nuevo ButtonGroup para las sub-subopciones
                                    ButtonGroup subButtonGroup = new ButtonGroup();
                
                                    List<SubResponseOption> subResponseOptions = findSubResponseOptionByResponseOptionUseCase.execute(subOption.getId());
                                    List<JRadioButton> subOptionButtons = new ArrayList<>();
                                    
                                    for (SubResponseOption subResponseOption : subResponseOptions) {
                                        JRadioButton subSubOptionButton = new JRadioButton(subResponseOption.getSubResponseText());
                                        subSubOptionButton.setVisible(false);
                                        gbc.gridy = row++;
                                        gbc.gridx = 3;
                                        contentPanel.add(subSubOptionButton, gbc);
                                        subButtonGroup.add(subSubOptionButton); // Agregar al ButtonGroup de sub-subopciones
                                        subOptionButtons.add(subSubOptionButton);
                                    }
                
                                    // Mostrar/ocultar subopciones dependiendo de la selección de la subopción
                                    subRadioButton.addItemListener(e -> {
                                        boolean selected = (e.getStateChange() == ItemEvent.SELECTED);
                                        for (JRadioButton subOptionButton : subOptionButtons) {
                                            subOptionButton.setVisible(selected);
                                        }
                                    });
                
                                    // Mostrar/ocultar la subopción cuando la opción primaria es seleccionada
                                    radioButton.addItemListener(e -> {
                                        boolean selected = (e.getStateChange() == ItemEvent.SELECTED);
                                        subRadioButton.setVisible(selected);
                                    });
                                }
                            }
                
                            // Si no hay subopciones directas, agregar las subresponses directamente a la opción primaria
                            List<SubResponseOption> subResponseOptions = findSubResponseOptionByResponseOptionUseCase.execute(responseOption.getId());
                            ButtonGroup subButtonGroup = new ButtonGroup(); // ButtonGroup para las subresponses directas
                            List<JRadioButton> subOptionButtons = new ArrayList<>();
                
                            for (SubResponseOption subResponseOption : subResponseOptions) {
                                JRadioButton subSubOptionButton = new JRadioButton(subResponseOption.getSubResponseText());
                                subSubOptionButton.setVisible(false);
                                gbc.gridy = row++;
                                gbc.gridx = 2;
                                contentPanel.add(subSubOptionButton, gbc);
                                subButtonGroup.add(subSubOptionButton); // Agregar al ButtonGroup
                                subOptionButtons.add(subSubOptionButton);
                            }
                
                            // Mostrar/ocultar subresponses dependiendo de la selección de la opción primaria
                            radioButton.addItemListener(e -> {
                                boolean selected = (e.getStateChange() == ItemEvent.SELECTED);
                                for (JRadioButton subOptionButton : subOptionButtons) {
                                    subOptionButton.setVisible(selected);
                                }
                            });
                        }
                    }
                    
                } else if("multiple_choice".equals(question.getResponse_type())){
                    List<ResponseOption> responseOptions = responseOptionByQuestionUseCase.execute(question.getId());
                    for (ResponseOption responseOption : responseOptions) {
                        JCheckBox checkBox = new JCheckBox(responseOption.getOptionText());
                        gbc.gridy = row++;
                        gbc.gridx = 1;
                        contentPanel.add(checkBox, gbc);
                        List<SubResponseOption> subResponseOptions = findSubResponseOptionByResponseOptionUseCase.execute(responseOption.getId());
                        for (SubResponseOption subresponseOption : subResponseOptions) {
                            JCheckBox checkBoxSubRes = new JCheckBox(subresponseOption.getSubResponseText());
                            gbc.gridy = row++;
                            gbc.gridx = 3;
                            contentPanel.add(checkBoxSubRes, gbc);
                        }
                    }

                } else{
                    
                        JTextField textField = new JTextField();
                        gbc.gridy = row++;
                        gbc.gridx = 1;
                        contentPanel.add(textField, gbc);
                    
                        
                    
                }
            }
        }

        // Crear el JScrollPane y agregar el contentPanel a él
        scrollPane = new JScrollPane(contentPanel);

        // Configurar el layout del JFrame
        setLayout(new BorderLayout());
        
        // Añadir el título en la parte superior
        add(title, BorderLayout.NORTH);
        
        // Añadir el JScrollPane en el centro
        add(scrollPane, BorderLayout.CENTER);
        
        // Crear y añadir el panel de botones en la parte inferior
        JPanel buttonPanel = new JPanel();
        Next = new JButton("Next");
        Goback = new JButton("🔙");
        Goback.addActionListener(e -> goback());
        buttonPanel.add(Next);
        buttonPanel.add(Goback);
        add(buttonPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
    }

    private void goback(){
        dispose();
        LoginUiController.openUserUi();
    }
}
