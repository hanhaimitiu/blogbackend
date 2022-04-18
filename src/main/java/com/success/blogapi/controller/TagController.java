package com.success.blogapi.controller;

import com.success.blogapi.dao.Tags;
import com.success.blogapi.service.ObTagService;
import com.success.blogapi.service.TagsService;
import com.success.blogapi.vo.Result;
import com.success.blogapi.vo.param.ArticleTagsParam;
import com.success.blogapi.vo.param.TagParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("tag")
public class TagController {

   @Autowired
   private ObTagService obTagService;

   @Autowired
   private TagsService tagsService;
    @GetMapping
    public Result getTags(){
        return obTagService.getTags();
    }


    @PostMapping
    public Result createTag(@RequestBody TagParam param){
        return obTagService.createTag(param);
    }

    @PostMapping("article")
    public Result addTagsArticle(@RequestBody ArticleTagsParam param){
        return tagsService.addTagsArticle(param);
    }
}
