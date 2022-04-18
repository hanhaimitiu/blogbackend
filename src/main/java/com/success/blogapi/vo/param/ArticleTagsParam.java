package com.success.blogapi.vo.param;

import lombok.Data;

import java.util.List;

@Data
public class ArticleTagsParam {

    Integer articleid;

    List<Integer> tag;
}
