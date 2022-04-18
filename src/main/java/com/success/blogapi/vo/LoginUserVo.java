package com.success.blogapi.vo;

import lombok.Data;

@Data
public class LoginUserVo {

    private Integer id;

    /**
     *
     */
    private String account;

    private String lastlogin;


}
