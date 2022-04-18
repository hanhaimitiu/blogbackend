package com.success.blogapi.service;

import com.success.blogapi.dao.ObArticle;
import com.baomidou.mybatisplus.extension.service.IService;
import com.success.blogapi.vo.Result;
import com.success.blogapi.vo.param.ArticleParam;

/**
* @author wang
* @description 针对表【ob_article】的数据库操作Service
* @createDate 2022-04-17 22:36:01
*/
public interface ObArticleService extends IService<ObArticle> {
    Result getArticles();

    Result getArticle(Integer id);

    Result deleteArticle(Integer id);

    Result editArticle(ArticleParam param);

    Result createArticle(ArticleParam param);
}
