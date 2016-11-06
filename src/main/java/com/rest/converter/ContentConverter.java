package com.rest.converter;

import com.google.common.collect.Lists;
import com.rest.Request.AddContentRequest;
import com.rest.domain.Content;
import com.rest.dto.PageContentDto;
import com.rest.utils.MarkDownUtil;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by bruce.ge on 2016/11/6.
 */
public class ContentConverter {
    public static List<PageContentDto> convetToPageDto(List<Content> contents){
        List<PageContentDto> pageContentDtos = Lists.newArrayList();
        if(CollectionUtils.isEmpty(contents)){
            return pageContentDtos;
        }

        for(Content s : contents){
            PageContentDto dto = convertToPage(s);
            pageContentDtos.add(dto);
        }
        return pageContentDtos;
    }

    private static PageContentDto convertToPage(Content s) {
        PageContentDto dto = new PageContentDto();
        dto.setTitle(s.getTitle());
        dto.setContent(s.getHtml_content());
        dto.setLink(buildLink(s));
        dto.setStartDate(s.getAddtime());
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
}
