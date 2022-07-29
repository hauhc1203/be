package com.codegym.controllers;

import com.codegym.models.*;

import com.codegym.service.BlogService;
import com.codegym.service.CategoryService;
import com.codegym.service.CommentService;
import com.codegym.service.LikeService;
import com.codegym.validate.ValidateBlog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpSession;
import java.util.ArrayList;


@RestController
@CrossOrigin("*")
@RequestMapping("/blogs")
public class BlogAPI {
    @Autowired
    HttpSession httpSession;
    @Autowired
    BlogService blogService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ValidateBlog validateBlog;

    @Autowired
    CommentService commentService;

    @Autowired
    LikeService likeService;


    @GetMapping
    public Page<Blogs> show(@RequestParam(defaultValue = "0") int page) {
        return blogService.getAll(PageRequest.of(page,3));
    }
    @GetMapping("/category")
    public ArrayList<Category> getCate(){
        return (ArrayList<Category>) categoryService.getAll();
    }
    @PostMapping
    public void create(@RequestBody Blogs blogs){
        blogService.save(blogs);
    }
    @GetMapping("/showEdit")
    public Blogs showEdit(@RequestParam long id){
        return blogService.findById(id).get();
    }

    @DeleteMapping("/{id}")
    public void del(@PathVariable long id ){
        blogService.delete(id);
    }

}
