package com.pavan.samples.moviecatalogservice.resources;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AuthorizationController {

    @RequestMapping(value = "/authFirst", method = RequestMethod.GET)
    public ModelAndView getEmployeeInfo() {
        return new ModelAndView("authFirst");
    }
}
