package com.surveymanagement.role.infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import com.surveymanagement.role.domain.entity.Role;
import com.surveymanagement.role.domain.service.RoleService;

public class RoleRepository implements RoleService {
    private Connection connection;

    public RoleRepository() {
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
    public void createRole(Role role) {
        try {
            String query = "INSERT INTO surveymanagement (name) VALUES (?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, role.getName());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Role added successfully!");
            } else {
                System.out.println("Role addition failed!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateRole(Role role) {
        String query = "UPDATE role SET name = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, role.getName());
            ps.setInt(2, role.getId());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Role updated successfully!");
            } else {
                System.out.println("Role update failed!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Role deleteRole(String codeRole) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteRole'");
    }

    @Override
    public Optional<Role> findRoleById(String codeRole) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findRoleById'");
    }

    @Override
    public List<Role> findAllRole() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllRole'");
    }

}
