package com.pinganfu.finmng.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by anygao on 14-6-10.
 */
public class SitemeshController {
    @RequestMapping(value = "/WEB-INF/pages/decorators/{page}")
    public String siteMeshPage(@PathVariable String page){
        return "decorators/"+page;
    }
}
