package com.ascending.training.controller;

import com.ascending.training.model.School;
import com.ascending.training.model.User;
import com.ascending.training.service.SchoolDaoService;
import com.ascending.training.service.UserDaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/school")
public class SchoolController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SchoolDaoService schoolDaoService;

    @GetMapping(value = "", params={"name"},produces = MediaType.APPLICATION_JSON_VALUE)
    public School findSchoolByname(@RequestParam("name")String name) throws Exception {
        logger.info("################findSchoolByname" + name);
        School returnSchool = schoolDaoService.getSchoolbyName(name);
        return returnSchool;
    }

    @PostMapping(value = "save",produces = MediaType.APPLICATION_JSON_VALUE)
    public School saveSchool(@RequestBody School school) {
        logger.info("################saveSchool" + school.getName());
        School returnSchool = schoolDaoService.saveSchool(school);

        return returnSchool;
    }

    @DeleteMapping(value = "delete",produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean deletSchool(@RequestBody School school) {
        logger.info("################delete School" + school.getName());
        return schoolDaoService.deleteSchool(school);
    }


}
