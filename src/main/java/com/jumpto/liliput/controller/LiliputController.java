package com.jumpto.liliput.controller;

import com.jumpto.liliput.model.Url;
import com.jumpto.liliput.service.LiliputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Controller
public class LiliputController {

    public LiliputController(LiliputService liliputService) {
        this.liliputService = liliputService;
    }

    @Autowired
    private LiliputService liliputService;

    // TODO: Use a repository...!
    private HashMap<String, String> liliputs = new HashMap<>();

    @RequestMapping("/")
    public String getIndex(Model model) {
        Url url = new Url();
        url.setName("");
        model.addAttribute("url", url);
        return "index";
    }

    @RequestMapping("/{liliput}")
    public String getUrl(@PathVariable("liliput") String liliput, Model model) {
        String url = liliputs.getOrDefault(liliput, "http://liliput.com");
        model.addAttribute("lili", "Short url: " + liliput);
        model.addAttribute("redirect", "Full Url: " + url);
        return "redirect";
    }


    @PostMapping("/liliput-add")
    public String addUrl(@ModelAttribute(name="url") Url url, Model model) {
        // TODO: check for existing url... if exists use the existing short url!
        // String liliputService.checkForUrl(...)
        String liliput = liliputService.generateLiliput(url.getName());
        liliputs.put(liliput, url.getName());
        model.addAttribute("lili", liliput);
        model.addAttribute("url", url.getName());
        return "added";
    }

}
