package com.surveymanagement.responseoption.infrastructure.responseoptionui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import com.surveymanagement.responseoption.application.FindQuestionByIdUseCase;
import com.surveymanagement.chapter.application.FindChapterByCodeUseCase;
import com.surveymanagement.chapter.application.FindChapterBySurveyUseCase;
import com.surveymanagement.chapter.domain.entity.Chapter;
import com.surveymanagement.chapter.domain.service.ChapterService;
import com.surveymanagement.chapter.infrastructure.ChapterRepository;
import com.surveymanagement.responseoption.application.FindQuestionByChapterUseCase;
import com.surveymanagement.responseoption.application.UpdateQuestionUseCase;
import com.surveymanagement.responseoption.domain.entity.Question;
import com.surveymanagement.responseoption.domain.service.QuestionService;
import com.surveymanagement.responseoption.infrastructure.QuestionRepository;
import com.surveymanagement.survey.application.FindAllSurveyUseCase;
import com.surveymanagement.survey.application.FindSurveyByCodeUseCase;
import com.surveymanagement.survey.domain.entity.Survey;
import com.surveymanagement.survey.domain.service.SurveyService;
import com.surveymanagement.survey.infrastructure.SurveyRepository;

public class UpdateResponseOptionUI extends JFrame{
    private final UpdateQuestionUseCase updateQuestionUseCase;
        private final FindQuestionByIdUseCase findQuestionByCodeUseCase;
        private final QuestionUI responseoptionUI;
    
        private JComboBox<String> responseoptionBox, surveyBox, chapterBox, toupdatechapterBox, response_type;
        private JTextField responseoption_number, comment_responseoption, responseoption_text; //
        private JButton jButton1; // Save
        private JButton jButton2; // Save
        private JButton jButton3; // Go back
        private JButton jButton4; // Find
        private int surveyID, responseoptionID, chapterID;
        private String surveyName, chapterName;
    
        public UpdateResponseOptionUI(UpdateQuestionUseCase updateQuestionUseCase, FindQuestionByIdUseCase findQuestionByCodeUseCase, QuestionUI responseoptionUI) {
            this.updateQuestionUseCase = updateQuestionUseCase;
            this.findQuestionByCodeUseCase = findQuestionByCodeUseCase;
            this.responseoptionUI = responseoptionUI;
        }
    
        public void frmUpdateQuestion() {
            initComponents();
            setVisible(true);
        }
    
        private void initComponents() {
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            setTitle("Update Question");
            setSize(500, 600);
    
            // Establecer el layout antes de agregar componentes
            setLayout(new GridBagLayout());
    
            JLabel jLabel1 = new JLabel("Update Question");
            jLabel1.setFont(new Font("Segoe UI", Font.BOLD, 24));
            jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
            
        surveyBox = new JComboBox<>();
        SurveyService surveyService = new SurveyRepository();
        FindAllSurveyUseCase findAllSurveyUseCase = new FindAllSurveyUseCase(surveyService);
        List<Survey> surveys = findAllSurveyUseCase.findAllSurvey();
        for (Survey survey : surveys) {
            surveyBox.addItem(String.valueOf(survey.getId())+". " + survey.getName());
        }
        addComponent(surveyBox, 1, 1, 1);

        chapterBox = new JComboBox<>();
        surveyBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateChapterBox();
            }
        });
        addComponent(chapterBox, 2, 1, 1);
        
        responseoptionBox = new JComboBox<>();
        chapterBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateQuestionBox();
            }
        });
        addComponent(responseoptionBox, 3, 1, 1);
            
            
            toupdatechapterBox = new JComboBox<>();
    
            
            response_type = new JComboBox<>();
            response_type.addItem("single_choice");
            response_type.addItem("multiple_choice");
            response_type.addItem("open_text");

            responseoption_number = new JTextField();
            comment_responseoption = new JTextField();
            responseoption_text = new JTextField();
    
            jButton1 = new JButton("Reset");
            jButton2 = new JButton("Save");
            jButton3 = new JButton("Go back");
            jButton4 = new JButton("Find");
    
            jButton1.addActionListener(e -> resetFields());
            jButton2.addActionListener(e -> updateQuestion());
            jButton3.addActionListener(e -> {
                dispose();
                responseoptionUI.showCrudOptions();
            });
            jButton4.addActionListener(e -> findQuestion());
    
            // Añadir los componentes al contenedor
            addComponent(jLabel1, 0, 0, 2);
            addComponent(jButton4, 4, 0, 2);
            addComponent(toupdatechapterBox, 6, 1, 1);
            addComponent(new JLabel("Survey:"), 1, 0, 1);
            addComponent(new JLabel("Chapter:"), 2, 0, 1);
            addComponent(new JLabel("Question:"), 3, 0, 1);
            addComponent(new JLabel("Chapter:"), 6, 0, 1);
            addComponent(new JLabel("Number:"), 7, 0, 1);
            addComponent(responseoption_number, 7, 1, 1);
            addComponent(new JLabel("Response Type:"), 8, 0, 1);
            addComponent(response_type, 8, 1, 1);
            addComponent(new JLabel("Comment Question:"),9, 0, 1);
            addComponent(comment_responseoption, 9, 1, 1);
            addComponent(new JLabel("Question text:"), 10, 0, 1);
            addComponent(responseoption_text, 10, 1, 1);
    
            // Panel de botones
            JPanel buttonPanel = new JPanel();
            buttonPanel.add(jButton2);
            buttonPanel.add(jButton1);
            buttonPanel.add(jButton3);
            addComponent(buttonPanel, 11, 0, 2);
    
            setLocationRelativeTo(null);
    
            hideComponents();
        }
    
        private void addComponent(Component component, int row, int col, int width) {
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = col;  // La columna en la que se agregará el componente
            gbc.gridy = row;  // La fila en la que se agregará el componente
            gbc.gridwidth = width;  // Número de celdas de ancho que ocupará el componente
            gbc.fill = GridBagConstraints.HORIZONTAL;  // El componente se estirará horizontalmente
            gbc.insets = new Insets(10, 10, 10, 10);  // Márgenes alrededor del componente
            gbc.anchor = GridBagConstraints.CENTER; // Centro del componente
    
            add(component, gbc);  // Añade el componente con las restricciones especificadas
        }
    
        private void updateQuestion() {
            try {
                Question responseoption = new Question();
                responseoption.setId(this.responseoptionID);
                responseoption.setChapter_id(Integer.parseInt(TextBeforeDot(toupdatechapterBox.getSelectedItem().toString())));
                responseoption.setQuestion_number(responseoption_number.getText());
                responseoption.setResponse_type(response_type.getSelectedItem().toString());
                responseoption.setComment_responseoption(comment_responseoption.getText());
                responseoption.setQuestion_text(responseoption_text.getText());
                updateQuestionUseCase.execute(responseoption);
                JOptionPane.showMessageDialog(this, "Question updated successfully!");
                resetFields();
            } catch (Exception ex) {
                // Imprime el mensaje de error completo en la consola
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    
        private void findQuestion() {
            updateNewChapterBox();
            int codeToUpdate = Integer.parseInt(TextBeforeDot(responseoptionBox.getSelectedItem().toString()));
            Optional<Question> responseoptionToUpdate = findQuestionByCodeUseCase.findQuestionByCode(codeToUpdate);
    
            if (responseoptionToUpdate.isPresent()) {
                Question foundQuestion = responseoptionToUpdate.get();

                this.responseoptionID = foundQuestion.getId();
                responseoption_number.setText(foundQuestion.getQuestion_number());
                response_type.setSelectedItem(foundQuestion.getResponse_type());
                comment_responseoption.setText(foundQuestion.getComment_responseoption());
                responseoption_text.setText(foundQuestion.getQuestion_text());
                toupdatechapterBox.setSelectedItem(String.valueOf(this.chapterID) + ". " + this.chapterName);
                surveyBox.setEditable(false);
                responseoptionBox.setEditable(false);
                showComponents();
                revalidate(); // Asegura que el layout se actualice
                repaint(); // Redibuja la ventana
            } else {
                JOptionPane.showMessageDialog(this, "Question not found!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    
        private void resetFields() {
            responseoption_number.setText("");
            comment_responseoption.setText("");
            responseoption_text.setText("");
            surveyBox.setEditable(true);
            hideComponents();
        }
    
        private void hideComponents() {
            responseoption_number.setVisible(false);
            comment_responseoption.setVisible(false);
            responseoption_text.setVisible(false);
            response_type.setVisible(false);
            toupdatechapterBox.setVisible(false);
            jButton1.setVisible(false);
            jButton2.setVisible(false);
        }
    
        private void showComponents() {
            responseoption_number.setVisible(true);
            comment_responseoption.setVisible(true);
            responseoption_text.setVisible(true);
            response_type.setVisible(true);
            toupdatechapterBox.setVisible(true);
            jButton1.setVisible(true);
            jButton2.setVisible(true);
        }
    
        private String TextBeforeDot(String text) {
            // Buscar la posición del primer punto en la cadena
            int position = text.indexOf('.');
            if (position != -1) {
                return text.substring(0, position);
            } else {
                return text;
            }
        }
    
        private void updateQuestionBox() {
            responseoptionBox.removeAllItems();
            try {
                int chapterid = Integer.parseInt(TextBeforeDot(chapterBox.getSelectedItem().toString()));
            ChapterService chapterService = new ChapterRepository();
            FindChapterByCodeUseCase findChapterByCodeUseCase = new FindChapterByCodeUseCase(chapterService);
            Optional<Chapter> chapterFound = findChapterByCodeUseCase.findChapterByCode(chapterid);
            if (chapterFound.isPresent()){
            this.chapterID =chapterFound.get().getId();
            this.chapterName = chapterFound.get().getChapter_title();
            QuestionService responseoptionService = new QuestionRepository();
            FindQuestionByChapterUseCase findQuestionByChapterUseCase = new FindQuestionByChapterUseCase(responseoptionService);
            List<Question> Questions = findQuestionByChapterUseCase.findQuestionByChapter(chapterID);
            for(Question Questionitem : Questions){
                responseoptionBox.addItem(Questionitem.getId()+". "+ Questionitem.getQuestion_text());
            };
        }
            } catch (Exception e) {
                // TODO: handle exception
            }
            }

            
            private void updateChapterBox() {
            chapterBox.removeAllItems(); 
            int surveyid = Integer.parseInt(TextBeforeDot(surveyBox.getSelectedItem().toString()));
            SurveyService surveyService = new SurveyRepository();
            FindSurveyByCodeUseCase findSurveyByCodeUseCase = new FindSurveyByCodeUseCase(surveyService);
            Optional<Survey> surveyFound = findSurveyByCodeUseCase.findSurveyByCode(surveyid);
            if (surveyFound.isPresent()){
            this.surveyID =surveyFound.get().getId();
            this.surveyName = surveyFound.get().getName();
            ChapterService chapterService = new ChapterRepository();
            FindChapterBySurveyUseCase findChapterBySurveyUseCase = new FindChapterBySurveyUseCase(chapterService);
            List<Chapter> Chapters = findChapterBySurveyUseCase.findChapterBySurvey(surveyID);
            for(Chapter Chapteritem : Chapters){
                chapterBox.addItem(Chapteritem.getId()+". "+ Chapteritem.getChapter_title());
            };
        }}

        private void updateNewChapterBox() {
            toupdatechapterBox.removeAllItems(); 
            SurveyService surveyService = new SurveyRepository();
            FindSurveyByCodeUseCase findSurveyByCodeUseCase = new FindSurveyByCodeUseCase(surveyService);
            Optional<Survey> surveyFound = findSurveyByCodeUseCase.findSurveyByCode(this.surveyID);
            if (surveyFound.isPresent()){
            ChapterService chapterService = new ChapterRepository();
            FindChapterBySurveyUseCase findChapterBySurveyUseCase = new FindChapterBySurveyUseCase(chapterService);
            List<Chapter> Chapterstoupdate = findChapterBySurveyUseCase.findChapterBySurvey(this.surveyID);
            for(Chapter Chapteritem : Chapterstoupdate){
                toupdatechapterBox.addItem(Chapteritem.getId()+". "+ Chapteritem.getChapter_title());
            };
        }}
    

}

