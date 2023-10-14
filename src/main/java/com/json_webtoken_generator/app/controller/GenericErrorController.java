package com.json_webtoken_generator.app.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.jboss.logging.Logger;

@Controller
public class GenericErrorController implements ErrorController  {
    protected static final Logger logger = Logger.getLogger(GenericErrorController.class);
    
    @RequestMapping("/error")
    public String handleError() {

        logger.info("An error has occurred. \n Loading JSP workflow Preview");        

        return "/error/preview.html";
    }

    public String handlError() { // Depricated in this version of Spring Boot.
        return null;
    }
}
