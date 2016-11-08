package com.rest.controller;

import com.google.common.collect.Lists;
import com.rest.dto.QueryResult;
import com.rest.utils.LuceneUtils;
import com.rest.utils.MarkDownUtil;
import com.rest.vo.QueryResultVo;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by bruce.ge on 2016/11/8.
 */
@Controller
public class SearchController {
    @RequestMapping("/search")
    public ModelAndView search(@RequestParam("query") String query){
        List<QueryResult> queryResults = LuceneUtils.query(query);
        List<QueryResultVo> resultVos = buildResultVo(queryResults);
        ModelAndView s = new ModelAndView("searchPage");
        boolean hasContent =false;
        if(!CollectionUtils.isEmpty(resultVos)){
            hasContent = true;
        }
        s.addObject("hasContent",hasContent);
        s.addObject("result",resultVos);
        s.addObject("resultlen",resultVos.size());
        return s;
    }

    private static List<QueryResultVo> buildResultVo(List<QueryResult> queryResults) {
        List<QueryResultVo> resultVos = Lists.newArrayList();
        for (QueryResult result : queryResults){
            resultVos.add(buildResultVo(result));
        }
        return resultVos;
    }

    private static QueryResultVo buildResultVo(QueryResult result) {
        QueryResultVo vo = new QueryResultVo();
        vo.setLink(buildLink(result.getId()));
        vo.setMarktitle(result.getTitle());
        vo.setMarkContent(result.getMarkText());
        return vo;
    }

    private static String buildLink(int id) {
        return "/getArticle/"+id;
    }
}
