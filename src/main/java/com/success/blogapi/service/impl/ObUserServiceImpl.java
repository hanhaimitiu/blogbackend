package com.success.blogapi.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.success.blogapi.dao.ObUser;
import com.success.blogapi.service.ObUserService;
import com.success.blogapi.mapper.ObUserMapper;
import com.success.blogapi.utils.DateUtils;
import com.success.blogapi.utils.JWTUtils;
import com.success.blogapi.vo.ErrorCode;
import com.success.blogapi.vo.LoginUserVo;
import com.success.blogapi.vo.Result;
import com.success.blogapi.vo.param.LoginParam;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
* @author wang
* @description 针对表【ob_user】的数据库操作Service实现
* @createDate 2022-04-17 21:17:39
*/
@Service
public class ObUserServiceImpl extends ServiceImpl<ObUserMapper, ObUser>
    implements ObUserService{


    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    private static final String slat = "onthewaytosuccess";

    @Override
    public Result findUserByToken(String token) {
        /**
         * 1.token合法性检验
         * 是否为空，解析是否成功，redis是否存在
         * 2.如果校验失败 返回错误
         * 3. 如果成功，返回对应的结果 LoginUserVo
         */
        ObUser obUser = checkToken(token);
        if (obUser == null) {
            System.out.println("查找失败");
            return Result.fail(ErrorCode.TOKEN_ERROR.getCode(), ErrorCode.TOKEN_ERROR.getMsg());

        }


        LoginUserVo loginUserVo = new LoginUserVo();
        loginUserVo.setAccount(obUser.getAccount());
        loginUserVo.setId(obUser.getId());
        loginUserVo.setLastlogin(DateUtils.format(obUser.getUpdatetime()));
        return Result.success(loginUserVo);
    }

    private ObUser checkToken(String token) {
        if (StringUtils.isBlank(token)) {
            return null;
        }
        Map<String, Object> stringObjectMap = JWTUtils.checkToken(token);
        if (stringObjectMap == null) {
            return null;
        }
        String userJson = redisTemplate.opsForValue().get("TOKEN_" + token);
        if (StringUtils.isBlank(userJson)) {
            return null;
        }
        return JSON.parseObject(userJson, ObUser.class);

    }

    @Override
    public Result login(LoginParam loginParam) {
        String account = loginParam.getAccount();
        String password = loginParam.getPassword();
        if (StringUtils.isBlank(account) || StringUtils.isBlank(password)) {
            return Result.fail(ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMsg());
        }
        password = DigestUtils.md5Hex(password + slat);
        ObUser obUser = findUser(account, password);
        if (obUser == null) {
            return Result.fail(ErrorCode.ACCOUNT_PWD_NOT_EXIST.getCode(), ErrorCode.ACCOUNT_PWD_NOT_EXIST.getMsg());
        }
        obUser.setUpdatetime(String.valueOf(System.currentTimeMillis()));
        saveOrUpdate(obUser);
        String token = JWTUtils.createToken(Long.valueOf(obUser.getId()));
        System.out.println(token);
        redisTemplate.opsForValue().set("TOKEN_" + token, JSON.toJSONString(obUser), 1, TimeUnit.DAYS);
        return Result.success(token);
    }

    @Override
    public Result logout(String token) {
        redisTemplate.delete("TOKEN_" + token);
        System.out.println("用户已退出");
        return Result.success(null);
    }

    @Override
    public Result register(LoginParam loginParam) {
        /**
         * 1.判断参数 是否合法
         * 2.判断账户是否存在，存在，则返回账号已注册
         * 3.不存在,注册用户
         * 4.生成token
         * 5.存入redis并返回
         * 6.加上事物，一旦中间的任何过程出现问题，注册的用户 需要回滚
         */
        String account = loginParam.getAccount();
        String password = loginParam.getPassword();
        String email = loginParam.getEmail();
        String phone = loginParam.getPhone();
        System.out.println(loginParam);
        if (StringUtils.isBlank(account) || StringUtils.isBlank(password) ||
                StringUtils.isBlank(email) || StringUtils.isBlank(phone)) {
            return Result.fail(ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMsg());

        }
        ObUser obUser = findUserByAccount(account);
        if (obUser != null) {
            return Result.fail(ErrorCode.ACCOUNT_EXIST.getCode(), ErrorCode.ACCOUNT_EXIST.getMsg());
        }
        obUser = new ObUser();
        obUser.setAccount(account);
        obUser.setEmail(email);
        obUser.setPhone(phone);
        obUser.setPassword(DigestUtils.md5Hex(password + slat));
        obUser.setCreatetime(String.valueOf(System.currentTimeMillis()));
        obUser.setUpdatetime(String.valueOf(System.currentTimeMillis()));

        save(obUser);
        String token = JWTUtils.createToken(Long.valueOf(obUser.getId()));
        redisTemplate.opsForValue().set("TOKEN_" + token, JSON.toJSONString(obUser), 1, TimeUnit.DAYS);
        return Result.success(token);
    }


    private ObUser findUserByAccount(String account) {
        LambdaQueryWrapper<ObUser> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.eq(ObUser::getAccount, account);

        return getOne(queryWrapper);
    }


    private ObUser findUser(String account, String password) {
        LambdaQueryWrapper<ObUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ObUser::getAccount, account);
        queryWrapper.eq(ObUser::getPassword, password);

        return getOne(queryWrapper);
    }

}




