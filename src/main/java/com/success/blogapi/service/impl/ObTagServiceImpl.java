package com.success.blogapi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.success.blogapi.dao.ObTag;
import com.success.blogapi.service.ObTagService;
import com.success.blogapi.mapper.ObTagMapper;
import com.success.blogapi.vo.Result;
import com.success.blogapi.vo.TagsVo;
import com.success.blogapi.vo.param.TagParam;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* @author wang
* @description 针对表【ob_tag】的数据库操作Service实现
* @createDate 2022-04-17 21:17:31
*/
@Service
public class ObTagServiceImpl extends ServiceImpl<ObTagMapper, ObTag>
    implements ObTagService{

    @Override
    public Result getTags() {
        List<ObTag> list = list();
        List<TagsVo> tagsVos=new ArrayList<>();
        for (ObTag vo:list){
           tagsVos.add(copy(vo));
        }

        return Result.success(tagsVos);
    }

    private TagsVo copy(ObTag vo) {
        TagsVo tagsVo = new TagsVo();
        tagsVo.setTagid(vo.getId());
        tagsVo.setName(vo.getName());
        return tagsVo;
    }

    @Override
    public Result createTag(TagParam param) {
        ObTag obTag = new ObTag();
        obTag.setName(param.getName());
        save(obTag);
        return Result.success(obTag);
    }
}




