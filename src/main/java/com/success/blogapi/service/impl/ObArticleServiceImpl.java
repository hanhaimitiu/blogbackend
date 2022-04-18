package com.success.blogapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.success.blogapi.dao.ObArticle;
import com.success.blogapi.mapper.TagsMapper;
import com.success.blogapi.service.ObArticleService;
import com.success.blogapi.mapper.ObArticleMapper;
import com.success.blogapi.utils.DateUtils;
import com.success.blogapi.vo.ArticleVo;
import com.success.blogapi.vo.Result;
import com.success.blogapi.vo.TagsVo;
import com.success.blogapi.vo.param.ArticleParam;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* @author wang
* @description 针对表【ob_article】的数据库操作Service实现
* @createDate 2022-04-17 22:36:01
*/
@Service
public class ObArticleServiceImpl extends ServiceImpl<ObArticleMapper, ObArticle>
    implements ObArticleService{

    @Autowired
    private TagsMapper tagsMapper;
    @Override
    public Result getArticles() {
        List<ObArticle> Articles = list();
        List<ArticleVo> articleVos=new ArrayList<>();
        for(ObArticle obArticle: Articles)
        {

            List<TagsVo> vos = tagsMapper.gettags(obArticle.getId());

            ArticleVo articleVo = new ArticleVo();

            BeanUtils.copyProperties(obArticle,articleVo);
            articleVo.setTags(vos);
            articleVo.setCreatetime(DateUtils.format(articleVo.getCreatetime()));
            articleVo.setUpdatetime(DateUtils.format(articleVo.getUpdatetime()));
            articleVos.add(articleVo);
        }

        return Result.success(articleVos);
    }

    @Override
    public Result getArticle(Integer id) {

        ObArticle article = getById(id);
        List<TagsVo> vos = tagsMapper.gettags(id);

        ArticleVo articleVo = new ArticleVo();
        BeanUtils.copyProperties(article,articleVo);
        articleVo.setTags(vos);
        articleVo.setCreatetime(DateUtils.format(articleVo.getCreatetime()));
        articleVo.setUpdatetime(DateUtils.format(articleVo.getUpdatetime()));
        System.out.println(articleVo);
        return Result.success(articleVo);
    }

    @Override
    public Result deleteArticle(Integer id) {
        removeById(id);
        return Result.success("删除成功");
    }

    @Override
    public Result editArticle(ArticleParam param) {
        LambdaUpdateWrapper<ObArticle> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(ObArticle::getId,param.getId())
                .set(ObArticle::getTitle,param.getTitle())
                .set(ObArticle::getContent,param.getContent())
                .set(ObArticle::getUpdatetime,System.currentTimeMillis())
                ;
        baseMapper.update(null,wrapper);
        return Result.success("ok");
    }

    @Override
    public Result createArticle(ArticleParam param) {
        ObArticle obArticle = new ObArticle();
        obArticle.setTitle(param.getTitle());
        obArticle.setContent(param.getContent());
        obArticle.setDescription(param.getDescription());
        obArticle.setCreatetime(String.valueOf(System.currentTimeMillis()));
        obArticle.setUpdatetime(String.valueOf(System.currentTimeMillis()));
        save(obArticle);
        return Result.success("创建成功");
    }
}




