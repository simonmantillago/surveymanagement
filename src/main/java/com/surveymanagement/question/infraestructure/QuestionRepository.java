package com.surveymanagement.question.infraestructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import com.surveymanagement.question.domain.entity.Question;
import com.surveymanagement.question.domain.service.QuestionService;

public class QuestionRepository implements QuestionService {
    private Connection connection;
        public QuestionRepository(){
        try {
            Properties props = new Properties();
            props.load(getClass().getClassLoader().getResourceAsStream("configdb.properties"));
            String url = props.getProperty("url");
            String user = props.getProperty("user");
            String password = props.getProperty("password");
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        @Override
        public void createQuestion(Question question) {
            try {
            String query = "INSERT INTO questions (chapter_id, question_number, response_type, comment_question, question_text) VALUES (?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, question.getChapter_id());
            ps.setString(2, question.getQuestion_number());
            ps.setString(3, question.getResponse_type());
            ps.setString(4, question.getComment_question());
            ps.setString(5, question.getQuestion_text());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        }
        @Override
        public void updateQuestion(Question question) {
            try {
                String query = "UPDATE questions SET chapter_id = ?, question_number = ?, response_type = ?, comment_question = ?, question_text = ? WHERE id = ?";
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setInt(1, question.getChapter_id());
                ps.setString(2, question.getQuestion_number());
                ps.setString(3, question.getResponse_type());
                ps.setString(4, question.getComment_question());
                ps.setString(5, question.getQuestion_text());
                ps.setInt(6, question.getId());
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }  
        }
        @Override
        public Question deleteQuestion(int id) {
            String query = "DELETE FROM questions WHERE id = ?";
            try {
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setInt(1, id);
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        public Optional<Question> findQuestionByName(String name, int chapter_id) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'findQuestionByName'");
        }
        @Override
        public Optional<Question> findQuestionByCode(int id) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'findQuestionByCode'");
        }
        @Override
        public List<Question> findAllQuestion() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'findAllQuestion'");
        }
        @Override
        public List<Question> findQuestionByChapter(int chapter_id) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'findQuestionByChapter'");
        }
}
