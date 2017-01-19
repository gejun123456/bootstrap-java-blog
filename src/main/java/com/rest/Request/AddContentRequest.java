package com.rest.Request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.SafeHtml;

/**
 * Created by bruce.ge on 2016/11/6.
 */
@Data
public class AddContentRequest {
    @NotEmpty
    @Length(max = 100)
    @SafeHtml
    private String title;

    @NotEmpty
    @Length(max = 5000)
    private String sourceContent;

    @NotEmpty
    @SafeHtml
    @Length(max = 6000)
    private String sourceHtml;

    @NotEmpty
    @SafeHtml
    @Length(max = 2000)
    private String indexHtml;

}
