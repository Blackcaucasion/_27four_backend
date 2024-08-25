package com.za.co._27four.assignment.luntu.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


/***
 * A web facing controller to access ui pages
 */
@Controller
public class WebController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

}
