package com.success.blogapi.mapper;

import com.success.blogapi.dao.Tags;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.success.blogapi.vo.TagsVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author wang
* @description 针对表【tags】的数据库操作Mapper
* @createDate 2022-04-17 21:17:46
* @Entity com.success.blogapi.dao.Tags
*/
@Repository
public interface TagsMapper extends BaseMapper<Tags> {

    List<TagsVo> gettags(int id);


}




