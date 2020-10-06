package com.ascending.training.controller;

import com.ascending.training.model.User;
import com.ascending.training.service.JWTService;
import com.ascending.training.service.UserDaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = {"/auth"})
public class AuthController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    UserDaoService Userservice;

    @Autowired
    private JWTService jwtService;

    private String errorMsg = "The email or password is not correct.";
    private String tokenKeyWord = "Authorization";
    private String tokenType = "Bearer";


    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map> authenticate(@RequestBody User user) throws Exception {
        String token;
        Map<String,String> resultMap = new HashMap<>();
        //resultMap.put("name","dummy token");

        try{
            User retrievedUser = Userservice.findUserByCredential(user.getEmail(), user.getPassword());
            if(retrievedUser == null){
                resultMap.put("msg", errorMsg);
                return ResponseEntity.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).body(resultMap);
            }
            token = jwtService.generateToken(retrievedUser);
            resultMap.put("token", token);
        }catch(Exception e){
            String msg = e.getMessage();
            if( msg == null){
                msg = "BAD REQUEST";
            }
            logger.error(msg);
            resultMap.put("msg",msg);
            return ResponseEntity.status(HttpServletResponse.SC_BAD_REQUEST).body(resultMap);
        }

        return ResponseEntity.status(HttpServletResponse.SC_OK).body(resultMap);
    }

}
