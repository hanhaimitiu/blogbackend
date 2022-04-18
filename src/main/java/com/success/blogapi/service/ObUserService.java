package com.success.blogapi.service;

import com.success.blogapi.dao.ObUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.success.blogapi.vo.Result;
import com.success.blogapi.vo.param.LoginParam;

/**
* @author wang
* @description 针对表【ob_user】的数据库操作Service
* @createDate 2022-04-17 21:17:39
*/
public interface ObUserService extends IService<ObUser> {

    Result findUserByToken(String token);

    Result login(LoginParam loginParam);

    Result logout(String token);

    Result register(LoginParam loginParam);
}
