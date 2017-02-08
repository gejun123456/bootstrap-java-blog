package com.rest.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by bruce.ge on 2016/11/6.
 */
@Getter
@Setter
public class PageContentVo {

//文章的内容
    private int id;

    private String title;

    private String content;

    private String link;

    private String startDate;

    private String tags;

    private boolean addMore;

}
