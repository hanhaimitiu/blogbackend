package com.success.blogapi.vo.param;

import lombok.Data;

@Data
public class ArticleParam {
    private Integer id;

    /**
     *
     */
    private String description;

    /**
     *
     */
    private String title;

    /**
     *
     */
    private String content;


    /**
     *
     */
    private Integer isDelete;
}
