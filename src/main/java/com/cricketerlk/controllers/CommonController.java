package com.cricketerlk.controllers;

import com.cricketerlk.common.ClkResponseMessage;
import com.cricketerlk.common.Configurations;
import com.cricketerlk.common.Constants;
import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Supun on 5/6/2017.
 */

@RestController
@RequestMapping("/common")
public class CommonController {

    private final Logger logger = Logger.getLogger(CommonController.class);
    private static String UPLOADED_FOLDER = "";

    @PostConstruct
    public void init() {
        CommonController.UPLOADED_FOLDER = Configurations.getConfigurations(Constants.IMAGE_UPLOAD_PATH);
        logger.info("INIT: COMMON CONTROLLER");
        logger.info(UPLOADED_FOLDER);
    }

    @CrossOrigin(origins = "*",allowCredentials = "true")
    @PostMapping("/upload")
    public ResponseEntity<ClkResponseMessage> uploadFile(@RequestParam("files")MultipartFile file) {
        ClkResponseMessage<String> res = new ClkResponseMessage<String>();

        logger.info("Single file upload!");
        if (file.isEmpty()) {
            return new ResponseEntity("please select a file!", HttpStatus.OK);
        }

        try {

            saveUploadedFiles(Arrays.asList(file));

        } catch (IOException e) {
            res.setMessage("Failed to upload image");
            return new ResponseEntity<ClkResponseMessage>(res, HttpStatus.BAD_REQUEST);
        }

        res.setMessage("Success");
        res.setResObject(file.getOriginalFilename());
        return new ResponseEntity(res, new HttpHeaders(), HttpStatus.OK);
    }


    //save file
    private void saveUploadedFiles(List<MultipartFile> files) throws IOException {

        for (MultipartFile file : files) {

            if (file.isEmpty()) {
                continue; //next pls
            }

            byte[] bytes = file.getBytes();
            logger.info(UPLOADED_FOLDER + file.getOriginalFilename());
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

        }

    }

}
