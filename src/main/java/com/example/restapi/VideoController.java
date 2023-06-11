package com.example.restapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VideoController {

   private static Logger logger = LoggerFactory.getLogger(VideoController.class);

   @Autowired
   ImageConfig imageConfig;


    @GetMapping("/random")
    public String getRandomVideo() {
        logger.info("rest template inside video controller: {}",imageConfig.getTemplate());
        return String.valueOf(Math.random());
    }
}
