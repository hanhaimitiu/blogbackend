package com.success.blogapi.controller;

import com.success.blogapi.service.ObArticleService;
import com.success.blogapi.service.ObUserService;
import com.success.blogapi.vo.Result;
import com.success.blogapi.vo.param.ArticleParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("article")
public class ArticleController {
    @Autowired
    private ObArticleService obArticleService;

    @GetMapping()
    public Result getArticles(){
        return obArticleService.getArticles();
    }

    @GetMapping("{id}")
    public Result getArticle(@PathVariable Integer id){
        return obArticleService.getArticle(id);
    }
    @PostMapping()
    public Result createArticle(@RequestBody ArticleParam param){
        return obArticleService.createArticle(param);
    }

    @PutMapping("{id}")
    public Result editArticle(@RequestBody ArticleParam param){
        return obArticleService.editArticle(param);
    }

    @DeleteMapping("{id}")
    public Result deleteArticle(@PathVariable Integer id){
        return obArticleService.deleteArticle(id);
    }

}
