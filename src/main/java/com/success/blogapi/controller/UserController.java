package com.success.blogapi.controller;
import com.success.blogapi.service.ObUserService;
import com.success.blogapi.vo.Result;
import com.success.blogapi.vo.param.LoginParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private ObUserService obUserService;

    @PostMapping("login")
    public Result login(@RequestBody LoginParam loginParam){

        return obUserService.login(loginParam);
    }

    @GetMapping("logout")
    public Result logout(@RequestHeader("Authorization") String token){
        return obUserService.logout(token);
    }
    @GetMapping("currentuser")
    public Result currentUser(@RequestHeader("Authorization") String token){

        return obUserService.findUserByToken(token);
    }
    @PostMapping("register")
    public Result register(@RequestBody LoginParam loginParam){
        return obUserService.register(loginParam);
    }
}
