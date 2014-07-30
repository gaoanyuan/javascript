package com.pinganfu.finmng.web.controller;

import com.pinganfu.finmng.web.service.PracticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by anygao on 14-7-10.
 */
@Controller
@RequestMapping(value="/sitemesh")
public class FreeMarkerController {
    @Autowired
    private PracticeService practiceService;
    @RequestMapping(value = "/menu",method = RequestMethod.GET)
    public String getMenu(Model model){
        model.addAttribute("userName", practiceService.getName());
        return "data/menu";
    }
}
