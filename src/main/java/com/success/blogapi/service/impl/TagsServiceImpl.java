package com.success.blogapi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.success.blogapi.dao.Tags;
import com.success.blogapi.service.TagsService;
import com.success.blogapi.mapper.TagsMapper;
import com.success.blogapi.vo.Result;
import com.success.blogapi.vo.TagsVo;
import com.success.blogapi.vo.param.ArticleTagsParam;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author wang
* @description 针对表【tags】的数据库操作Service实现
* @createDate 2022-04-17 21:17:46
*/
@Service
public class TagsServiceImpl extends ServiceImpl<TagsMapper, Tags>
    implements TagsService{

    public List<TagsVo> getallTags(){

        return null;


    }

    @Override
    public Result addTagsArticle(ArticleTagsParam param) {
        System.out.println(param);
        List<Integer> tag = param.getTag();
        for (Integer tagid:tag){
            Tags tags = new Tags();
            tags.setArticleid(param.getArticleid());
            tags.setTagid(tagid);
            save(tags);
        }

        return Result.success("成功保存");
    }
}




