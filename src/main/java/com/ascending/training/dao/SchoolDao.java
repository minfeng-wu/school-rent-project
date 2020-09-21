package com.ascending.training.dao;

import com.ascending.training.model.School;

import java.sql.SQLException;
import java.util.List;

public interface SchoolDao {
    School save(School school) ;
    School update(School school);
    boolean deleteByName(String schoolname);
    boolean delete(School school);
    List<School> getschools();
    School getSchoolById(Long id);
    List<School> getDepartmentsWithChildren();
    School getSchoolByName(String schoolName);
}
