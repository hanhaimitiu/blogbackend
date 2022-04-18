package com.success.blogapi.service;

import com.success.blogapi.dao.Tags;
import com.baomidou.mybatisplus.extension.service.IService;
import com.success.blogapi.vo.TagsVo;

import java.util.List;

/**
* @author wang
* @description 针对表【tags】的数据库操作Service
* @createDate 2022-04-17 21:17:46
*/
public interface TagsService extends IService<Tags> {
    //用于将tags扩展出name，并返回ListTagsVo,一个ListTagsVo对应一个文章
    List<TagsVo> getallTags();

}
