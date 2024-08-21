package com.surveymanagement.responseoption.infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import com.surveymanagement.responseoption.domain.entity.ResponseOption;
import com.surveymanagement.responseoption.domain.service.ResponseOptionService;
import com.surveymanagement.role.domain.entity.Role;

public class ResponseOptionRepository implements ResponseOptionService {
    private Connection connection;

    public ResponseOptionRepository() {
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
    public void createResponseOption(ResponseOption responseOption) {
        try {
            String query = "INSERT INTO response_options (option_value,categorycatalog_id,parentresponse_id,question_id,comment_response,option_text) VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, responseOption.getOptionValue());
            ps.setInt(2, responseOption.getCategoryCatalogId());
            ps.setInt(3, responseOption.getParentResponseId());
            ps.setInt(4, responseOption.getQuestionId());
            ps.setString(5, responseOption.getCommentResponse());
            ps.setString(6, responseOption.getOptionText());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("ResponseOption added successfully!");
            } else {
                System.out.println("ResponseOption addition failed!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

    @Override
    public void updateResponseOption(ResponseOption responseOption) {
        String query = "UPDATE response_options SET option_value = ?, categorycatalog_id = ?, parentresponse_id = ?, question_id = ?, comment_response = ?, option_text = ?  WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, responseOption.getOptionValue());
            ps.setInt(2, responseOption.getCategoryCatalogId());
            ps.setInt(3, responseOption.getParentResponseId());
            ps.setInt(4, responseOption.getQuestionId());
            ps.setString(5, responseOption.getCommentResponse());
            ps.setString(6, responseOption.getOptionText());
            ps.setInt(7, responseOption.getId());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("ResponseOption updated successfully!");
            } else {  
                System.out.println("ResponseOption update failed!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ResponseOption deleteResponseOption(int responseOptionId) {
        ResponseOption responseOption = null;
        String selectQuery = "SELECT * FROM response_options WHERE id = ?";
        String deleteQuery = "DELETE FROM response_options WHERE id = ?";

        try (PreparedStatement selectPs = connection.prepareStatement(selectQuery);
             PreparedStatement deletePs = connection.prepareStatement(deleteQuery)) {

            // First, fetch the role
            selectPs.setInt(1, responseOptionId);
            try (ResultSet rs = selectPs.executeQuery()) {
                if (rs.next()) {
                    responseOption = new ResponseOption(
                        rs.getInt("id"),
                        rs.getInt("option_value"),
                        rs.getInt("categorycatalog_id"),
                        rs.getString("created_at"),
                        rs.getInt("parentresponse_id"),
                        rs.getInt("question_id"),
                        rs.getString("updated_at"),
                        rs.getString("comment_response"),
                        rs.getString("option_text") 
                        
                    );
                }
            }

            // If role exists, delete it
            if (responseOption != null) {
                deletePs.setInt(1, responseOptionId);
                int rowsAffected = deletePs.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("ResponseOption deleted successfully!");
                    return responseOption;
                }
            }

            System.out.println("ResponseOption deletion failed. Role not found.");
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Optional<ResponseOption> findResponseOptionById(int responseOptionId) {
        String query = "SELECT * FROM response_options WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, responseOptionId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ResponseOption responseOption = new ResponseOption(
                        rs.getInt("id"),
                        rs.getInt("option_value"),
                        rs.getInt("categorycatalog_id"),
                        rs.getString("created_at"),
                        rs.getInt("parentresponse_id"),
                        rs.getInt("question_id"),
                        rs.getString("updated_at"),
                        rs.getString("comment_response"),
                        rs.getString("option_text") 
                    );
                    return Optional.of(responseOption);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<ResponseOption> findResponseOptionByQuestion(int questionId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findResponseOptionByQuestion'");
    }

}
