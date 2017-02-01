package com.rest.Request;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * @Author bruce.ge
 * @Date 2017/2/1
 * @Description
 */
public class EditTagRequest {

    @NotNull
    private Integer tagId;

    @NotBlank
    private String newTagName;

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public String getNewTagName() {
        return newTagName;
    }

    public void setNewTagName(String newTagName) {
        this.newTagName = newTagName;
    }
}
