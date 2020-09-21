package com.ascending.training.init;

import com.ascending.training.App;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.ascending.training"})
public class AppInitializer {
    private Logger logger = LoggerFactory.getLogger(AppInitializer.class);

    public static void main (String args[]){
        SpringApplication.run(AppInitializer.class, args);
    }
}
