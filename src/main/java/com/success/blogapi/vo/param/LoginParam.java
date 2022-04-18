package com.success.blogapi.vo.param;

import lombok.Data;

@Data
public class LoginParam {
    private String account;
    private String email;
    private String phone;
    private String password;
}
