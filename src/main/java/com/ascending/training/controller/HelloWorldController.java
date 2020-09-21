package com.ascending.training.controller;

import com.ascending.training.model.School;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/world")
public class HelloWorldController {
    private Logger logger = LoggerFactory.getLogger(getClass());

 //   @RequestMapping(value = "hello", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    //Easier way to write
    @GetMapping(value = "hello", produces = MediaType.APPLICATION_JSON_VALUE)
    public String Hello(){
        logger.info("===From Hello()===");
        return "Hello";
    }

    @RequestMapping(value = "hello", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String HelloPost(){
        logger.info("===From Hello()===");
        return "Hello";
    }

    @GetMapping(value = "hello/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String greetingWithHello(@PathVariable("name")String name123){
        logger.info("===From greetingWithHello()===, the input value of PathVariable('name')is: "+ name123);
        return "Hello " + name123;
    }

    //再加一个mapping@RequestParam("location123") 不行
//    @GetMapping(value = "Hola", produces = MediaType.APPLICATION_JSON_VALUE)
//    public String greetingWIthHola2(@RequestParam("location123")String location){
//        logger.info("===From greetingWIthHola()===, the input value of PathVariable('location')is: "+ location);
//        return "Hi " + location;
//    }
//  需要添加 params={"name"}, 如如果只有一个method， not mandatory
    @GetMapping(value = "Hola",params={"name123"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public String greetingWIthHola(@RequestParam("name123")String name){
        logger.info("===From greetingWIthHola()===, the input value of PathVariable('name')is: "+ name);
        return "Hi " + name;
    }

    @GetMapping(value = "Hola",params={"name","location"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public String greetingWIthHola2(@RequestParam("name")String name,
                                    @RequestParam("location")String location){
        logger.info("===From greetingWIthHola()===, the input value of PathVariable('name')is: "+ name);
        return "Hi " + name + "from " + location;
    }

    //@RequestBody pass object paramter
    @PostMapping(value = "hola/school", produces = MediaType.APPLICATION_JSON_VALUE)
    public School createSchool(@RequestBody School school){
        logger.info("===for create school call with HttpMethod.post, input school = {}", school);
        school.setId(222);
        return school;
    }

    @PutMapping(value = "hola/school", produces = MediaType.APPLICATION_JSON_VALUE)
    public School updateSchool(@RequestBody School school){
        logger.info("===for update school call with HttpMethod.post, input school = {}", school);
        school.setName("school_update");
        return school;
    }

    @PatchMapping(value = "hola/school", produces = MediaType.APPLICATION_JSON_VALUE)
    public School partialUpdateSchool(@RequestBody School school){
        logger.info("===for update partial school call with HttpMethod.post, input school = {}", school);
        school.setName(school.getName()+"update_partially");
        return school;
    }

    @DeleteMapping (value = "hola/school", produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteSchool(@RequestBody School school){
        logger.info("===for delete school call with HttpMethod.post, input school = {}", school);
        return "Delete school " + school.getName();
    }

    @GetMapping("/headertest")
    public String greeting(@RequestHeader("accept-language")String language){
        return "The item 'accept-languae' in headers has a value " + language;
    }






}
