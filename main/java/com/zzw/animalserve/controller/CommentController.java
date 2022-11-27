package com.zzw.animalserve.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.zzw.animalserve.service.CommentService;

@RestController
@RequestMapping("/comment")
public class CommentController {

@Autowired
private CommentService commentService;



}