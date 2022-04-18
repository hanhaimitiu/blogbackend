package com.success.blogapi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.success.blogapi.dao.ObTag;
import com.success.blogapi.service.ObTagService;
import com.success.blogapi.mapper.ObTagMapper;
import org.springframework.stereotype.Service;

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
    public List<ObTag> getTags() {

        return null;
    }
}




