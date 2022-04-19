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
   //获取所有tags
    @GetMapping
    public Result getTags(){
        return obTagService.getTags();
    }

    //创建一个tag
    @PostMapping
    public Result createTag(@RequestBody TagParam param){
        return obTagService.createTag(param);
    }
    //给文章增加tags
    @PostMapping("article")
    public Result addTagsArticle(@RequestBody ArticleTagsParam param){
        System.out.println(param);
        return tagsService.addTagsArticle(param);
    }
}
