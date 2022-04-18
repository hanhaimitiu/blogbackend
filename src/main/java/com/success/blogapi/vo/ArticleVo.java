package com.success.blogapi.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.List;

@Data
public class ArticleVo {

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
    private String createtime;

    private String updatetime;
    private List<TagsVo>   tags;
}
