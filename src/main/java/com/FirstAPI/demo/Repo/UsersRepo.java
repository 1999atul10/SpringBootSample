package com.FirstAPI.demo.Repo;

import com.FirstAPI.demo.Model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UsersRepo {
    public JdbcTemplate getJdbc() {
        return jdbc;
    }

    @Autowired
    public void setJdbc(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private JdbcTemplate jdbc;

    public boolean save(Users users) {
        String sqlQuery = "Insert into Users(firstName,lastName,address,mobileNo) values(?,?,?,?)";
        int rowAffected = jdbc.update(sqlQuery, users.firstName, users.lastName, users.address, users.mobileNo);
        return rowAffected > 0;
    }

    // Get user ID from the database
    public int getId(Users users) {
        String sqlQuery = "SELECT id FROM Users WHERE firstname = ? AND lastname = ? AND address = ? AND mobileno = ?";
        try {
            return jdbc.queryForObject(sqlQuery, new Object[]{users.getFirstName(), users.getLastName(), users.getAddress(), users.getMobileNo()}, Integer.class);
        } catch (EmptyResultDataAccessException e) {
            // Handle case where user is not found
            return -1;
        }
    }

    public boolean delete(Users users) {
        String sqlQuery = "DELETE FROM Users WHERE id = ?";
        int rowsAffected = jdbc.update(sqlQuery, getId(users));
        return rowsAffected > 0;
    }


    public List<Users> findAll() {
        String sqlQuery = "SELECT firstName,lastName,address,mobileNo FROM Users;";

        // Use the query method of JdbcTemplate to fetch users
        List<Users> usersList = jdbc.query(sqlQuery, new RowMapper<Users>() {
            @Override
            public Users mapRow(ResultSet rs, int rowNum) throws SQLException {
                Users user = new Users();
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                user.setAddress(rs.getString("address"));
                user.setMobileNo(rs.getString("mobileNo"));
                return user;
            }
        });

        return usersList; // Return the populated list of Users
    }
}
