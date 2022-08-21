package com.edcccd.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 静态资源访问
 */
@Controller
public class VipController {


    @GetMapping("ceshi")
    public String ceshi() {
        // return "cover/456.jpg";
        return "adasdas";
    }

}
