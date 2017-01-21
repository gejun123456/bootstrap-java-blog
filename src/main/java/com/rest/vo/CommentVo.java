package com.rest.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by bruce.ge on 2016/11/18.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentVo {
    private String name;

    private String comment;

    private String ago;

    private Integer id;

    private Integer parentId;

    private Date addtime;

    //    if viewed than is 1 else is 0.
    private Short viewed;
}
