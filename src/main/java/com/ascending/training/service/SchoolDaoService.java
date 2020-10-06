package com.ascending.training.service;

import com.ascending.training.dao.SchoolDao;
import com.ascending.training.model.School;
import com.ascending.training.repository.SchoolDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolDaoService {
    //问题！！！！！！！！！！！！！！！！！！！！！！！！！
    @Autowired
    private SchoolDaoImpl schoolDao;

    public List<School> getAllSchools(){
        List<School> schoolList = schoolDao.getschools();
        return schoolList;
    }

    public School getSchoolbyName(String name) {
        School school = schoolDao.getSchoolByName(name);
        return school;
    }

    public School saveSchool(School school) {
        schoolDao.save(school);
        return school;
    }

    public boolean deleteSchool(School school) {
        return schoolDao.delete(school);
    }
}
