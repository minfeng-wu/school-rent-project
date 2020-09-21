package com.ascending.training.jdbc;

import com.ascending.training.dao.SchoolDao;
import com.ascending.training.model.School;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SchoolDaoImplement implements SchoolDao {
    private Logger logger = LoggerFactory.getLogger(SchoolDaoImplement.class);
    private static final String DB_URL = "jdbc:postgresql://localhost:5430/ascending-14";
    private static final String USER = "admin";
    private static final String PASS = "password";

    @Override
    public School save(School school) {
        School createdSchool = null;
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try{
            //STEP 2: open a connection
            logger.debug("Connection to database");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP3: Execute a query
            logger.debug("Insert statement...");

            String SQL_INSERT = "INSERT INTO school( name, city, state, address) VALUES(?,?,?,?)";
            preparedStatement = conn.prepareStatement(SQL_INSERT);
        //    preparedStatement.setLong(1, school.getId());
            preparedStatement.setString(1, school.getName());
            preparedStatement.setString(2, school.getCity());
            preparedStatement.setString(3, school.getState());
            preparedStatement.setString(4, school.getAddress());

            int row = preparedStatement.executeUpdate();
            if(row > 0){
                createdSchool = school;
            }

        } catch(SQLException e){
            logger.error("Call save for school SQLexception: " + e);
        }
        return createdSchool;
    }

    @Override
    public School update(School school) {
        return null;
    }

    @Override
    public boolean deleteByName(String schoolName) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try{
            //STEP 2: open a connection
            logger.debug("Connection to database");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP3: Execute a query
            logger.debug("Insert statement...");

            String SQL_INSERT = "DELETE FROM school WHERE name = ?";
            preparedStatement = conn.prepareStatement(SQL_INSERT);
            preparedStatement.setString(1, schoolName);

            int row = preparedStatement.executeUpdate();
            if(row > 0){
                return true;
            }

        } catch(SQLException e){
            logger.error("Call deleteByName for school SQLexception: " + e);
        }
        return false;
    }


    @Override
    public boolean delete(School school) {
        return false;
    }

    @Override
    public List<School> getschools() {
        List<School> schools = new ArrayList();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            //STEP 2: open a connection
            logger.debug("Connection to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP3: Execute a query
            logger.debug("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM school";
            rs = stmt.executeQuery(sql);

            //STEP 4: Extract data from result set
            while(rs.next()){
                //Retrieve by column name
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String city = rs.getString("city");
                String state = rs.getString("state");
                String address = rs.getString("address");

                //Fill the object
                School school = new School();
                school.setId(id);
                school.setName(name);
                school.setCity(city);
                school.setState(state);
                school.setAddress(address);
                schools.add(school);
            }
        } catch(SQLException e){
            logger.error("Call getSchools for schools SQLException: " + e);
        }
        finally {
            //STEP 6: finally block used to close resources
            try{
                if(rs != null) rs.close();
                if(stmt != null) stmt.close();
                if(conn != null) conn.close();
            }
            catch(SQLException e){
                logger.error("close error in getSchool for schools: " + e);
            }
        }

        return schools;
    }

    @Override
    public School getSchoolById(Long id) {
        return null;
    }

    @Override
    public List<School> getDepartmentsWithChildren() {
        return null;
    }

    @Override
    public School getSchoolByName(String schoolName) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        School createdSchool = null;

        try {
            //STEP 2: open a connection
            logger.debug("Connection to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP3: Execute a query
            logger.debug("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM school WHERE ";
            rs = stmt.executeQuery(sql);

            //STEP 4: Extract data from result set
            while(rs.next()){
                //Retrieve by column name
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String city = rs.getString("city");
                String state = rs.getString("state");
                String address = rs.getString("address");

                //Fill the object
                School school = new School();
                school.setId(id);
                school.setName(name);
                school.setCity(city);
                school.setState(state);
                school.setAddress(address);

                createdSchool = school;

            }
        } catch(SQLException e){
            logger.error("Call getDepartmentByName for schools SQLException: " + e);
        }
        finally {
            //STEP 6: finally block used to close resources
            try{
                if(rs != null) rs.close();
                if(stmt != null) stmt.close();
                if(conn != null) conn.close();
            }
            catch(SQLException e){
                logger.error("close error in getSchoolByName for schools: " + e);
            }
        }

        return createdSchool;
    }



}
