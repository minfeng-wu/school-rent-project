package com.ascending.training.controller;

import com.ascending.training.service.AWSS3Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping(value = {"/files"})
public class AWSS3FileController {
    private static final String queueName = "training_queue_ascending_com";
    //    @Autowired
//    private Logger logger;
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private AWSS3Service fileService;

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    //you can return either s3 key or file url
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        logger.info("test file name:"+file.getOriginalFilename());
        try {
            return fileService.uploadFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    //    @RequestMapping(value = "/{fileName}", method = RequestMethod.GET)
//    public ResponseEntity<String> getFileUrlForDownloading(@PathVariable String fileName, HttpServletRequest request) {
    @GetMapping(params={"fileName"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getFileUrlForDownloading(@RequestParam("fileName") String fileName) {
//        request.getSession()
//        Resource resource = null;
        String msg = "The file doesn't exist.";
        ResponseEntity responseEntity;
        try {
            String url = fileService.generatePresignedURLForDownloading(fileName);
            logger.debug(msg);
            responseEntity = ResponseEntity.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).body(url);
        }
        catch (Exception ex) {
            responseEntity = ResponseEntity.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).body(ex.getMessage());
            logger.debug(ex.getMessage());
        }
        return responseEntity;
    }
}
