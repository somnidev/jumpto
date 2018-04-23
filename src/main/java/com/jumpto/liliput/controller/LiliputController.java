package com.jumpto.liliput.controller;

import com.jumpto.liliput.model.Url;
import com.jumpto.liliput.service.LiliputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LiliputController {

    public LiliputController(LiliputService liliputService) {
        this.liliputService = liliputService;
    }

    @Autowired
    private LiliputService liliputService;

    @RequestMapping("/")
    public String getIndex(Model model) {
        Url url = new Url();
        url.setName("");
        model.addAttribute("url", url);
        return "index";
    }

    @RequestMapping("/{liliput}")
    public String getUrl(@PathVariable("liliput") String liliput, Model model) {
        String url = liliputService.getUrlFromLiliput(liliput);
        model.addAttribute("lili", "Short url: " + liliput);
        model.addAttribute("redirect", "Full Url: " + url);
        return "redirect";
    }


    @PostMapping("/liliput-add")
    public String addUrl(@ModelAttribute(name="url") Url url, Model model) {
        String liliput = liliputService.getLiliputFromUrl(url.getName());
        if (liliput == null) {
            liliput = liliputService.generateLiliput(url.getName());
            liliputService.addUrlWithLiliput(url.getName(), liliput);
        }
        model.addAttribute("lili", liliput);
        model.addAttribute("url", url.getName());
        return "added";
    }

}
