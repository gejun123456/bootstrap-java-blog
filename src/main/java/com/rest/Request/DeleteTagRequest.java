package com.rest.Request;


import javax.validation.constraints.NotNull;

/**
 * @Author bruce.ge
 * @Date 2017/2/1
 * @Description
 */
public class DeleteTagRequest {
    @NotNull
    private Integer tagId;

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }
}
