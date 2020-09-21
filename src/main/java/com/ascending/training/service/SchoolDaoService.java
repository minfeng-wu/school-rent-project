package com.ascending.training.service;

import com.ascending.training.dao.SchoolDao;
import com.ascending.training.model.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolDaoService {
    @Autowired
    private SchoolDao schoolDao;

    public List<School> getAllSchools(){
        List<School> schoolList = schoolDao.getschools();
        return schoolList;
    }

}
