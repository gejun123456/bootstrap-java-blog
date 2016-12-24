package com.rest.controller;

import com.google.common.collect.Lists;
import com.rest.dto.SearchResult;
import com.rest.service.SearchService;
import com.rest.utils.AntiSamyUtils;
import com.rest.vo.QueryResultVo;
import org.owasp.validator.html.PolicyException;
import org.owasp.validator.html.ScanException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by bruce.ge on 2016/11/8.
 */
@Controller
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping("/search")
    public ModelAndView search(@RequestParam("query") String query) throws ScanException, PolicyException {
        query = AntiSamyUtils.getCleanHtml(query);
        //totally get data from lucene need to control if the file not in database.
        List<SearchResult> searchResults = searchService.query(query);
        List<QueryResultVo> resultVos = buildResultVo(searchResults);
        ModelAndView s = new ModelAndView("searchPage");
        boolean hasContent = false;
        if (!CollectionUtils.isEmpty(resultVos)) {
            hasContent = true;
        }
        s.addObject("hasContent", hasContent);
        s.addObject("result", resultVos);
        s.addObject("resultlen", resultVos.size());
        return s;
    }

    private static List<QueryResultVo> buildResultVo(List<SearchResult> searchResults) {
        List<QueryResultVo> resultVos = Lists.newArrayList();
        for (SearchResult result : searchResults) {
            resultVos.add(buildResultVo(result));
        }
        return resultVos;
    }

    private static QueryResultVo buildResultVo(SearchResult result) {
        QueryResultVo vo = new QueryResultVo();
        vo.setLink(buildLink(result.getId()));
        vo.setMarktitle(result.getTitle());
        vo.setMarkContent(result.getMarkText());
        return vo;
    }

    private static String buildLink(int id) {
        return "/getArticle/" + id;
    }
}
