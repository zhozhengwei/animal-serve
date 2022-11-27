package com.zzw.animalserve.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.zzw.animalserve.service.ArticleTagService;

@RestController
@RequestMapping("/articleTag")
public class ArticleTagController {

@Autowired
private ArticleTagService articleTagService;



}