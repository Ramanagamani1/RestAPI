package com.example.restapi;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class ImageController {

    /**
     * input: an integer id, optional height and width
     * output: An image corresponding to the id from lorem picsum website
     */

    private static Logger log = LoggerFactory.getLogger(ImageConfig.class);


    @Autowired
    ImageConfig imageConfig;
//     @Autowired
//     public RestTemplate restTemplate;

    private static Logger logger = LoggerFactory.getLogger(ImageController.class);

    @GetMapping(value = "/image", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getImage(@RequestParam(value = "id") int id,
                           @RequestParam(value = "width", required = false, defaultValue = "200") int width,
                           @RequestParam(value = "height", required = false, defaultValue = "300") int height){

        // uri and url

        logger.info("Rest API that produces image as an output");
        String url = "https://picsum.photos/id/" + id + "/" + width + "/" + height;
        RestTemplate restTemplate = imageConfig.getTemplate();
        log.info("Rest template that is autowired: {}",restTemplate);
        return restTemplate.getForObject(url, byte[].class);
    }
}

/*
    @Bean will return the same object in case of @Configuration annotation
    @Bean will return different object in case of @Component annotation
 */
//RestTemplate@4ee25d80
//RestTemplate@4ee25d80

//RestTemplate@43984213
//RestTemplate@65866640