package com.success.blogapi.service;

import com.success.blogapi.dao.ObTag;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author wang
* @description 针对表【ob_tag】的数据库操作Service
* @createDate 2022-04-17 21:17:31
*/
public interface ObTagService extends IService<ObTag> {

    List<ObTag> getTags();
}
