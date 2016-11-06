package com.rest.converter;

import com.google.common.collect.Lists;
import com.rest.Request.AddContentRequest;
import com.rest.domain.Content;
import com.rest.vo.ContentVo;
import com.rest.vo.PageContentVo;
import com.rest.utils.MarkDownUtil;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by bruce.ge on 2016/11/6.
 */
public class ContentConverter {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
    public static List<PageContentVo> convetToPageDto(List<Content> contents){
        List<PageContentVo> pageContentVos = Lists.newArrayList();
        if(CollectionUtils.isEmpty(contents)){
            return pageContentVos;
        }

        for(Content s : contents){
            PageContentVo dto = convertToPage(s);
            pageContentVos.add(dto);
        }
        return pageContentVos;
    }

    private static PageContentVo convertToPage(Content s) {
        PageContentVo dto = new PageContentVo();
        dto.setTitle(s.getTitle());
        dto.setContent(s.getHtml_content());
        dto.setLink(buildLink(s));
        dto.setStartDate(dateFormat.format(s.getAddtime()));
        return dto;
    }
//get content by id.
    private static String buildLink(Content s) {
        return "/pagecontent/"+s.getId();
    }

    public static Content convertToContent(AddContentRequest request) {
        Content content = new Content();
        content.setTitle(request.getTitle());
        content.setSource_content(request.getSourceContent());
        content.setHtml_content(MarkDownUtil.convertToHtml(request.getSourceContent()));
        content.setAddtime(new Date());
        content.setUpdatetime(new Date());
        return content;
    }

    public static ContentVo convetToVo(Content byId) {
        ContentVo contentVo = new ContentVo();
        contentVo.setTitle(byId.getTitle());
        contentVo.setContent(byId.getHtml_content());
        contentVo.setAddtime(dateFormat.format(byId.getAddtime()));
        return contentVo;
    }
}
