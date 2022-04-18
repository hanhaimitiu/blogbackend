package com.success.blogapi;

import com.success.blogapi.service.ObArticleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogApiApplicationTests {
    @Autowired
    private ObArticleService obArticleService;
    @Test
    void contextLoads() {
//        obArticleService.getArticle(1);
    obArticleService.getArticles();
    }

}
