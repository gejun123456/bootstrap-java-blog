package com.rest.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.rest.converter.AriveConverter;
import com.rest.domain.Archives;
import com.rest.mapper.ContentMapper;
import com.rest.mapper.ContentTimeMapper;
import com.rest.vo.ArchiveVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * Created by bruce.ge on 2016/11/6.
 */
@Controller
public class ArchivesController {
    @Autowired
    private ContentTimeMapper contentTimeMapper;

    @Autowired
    private ContentMapper contentMapper;

    @GetMapping("/archive")
    public ModelAndView getArchive(){
        List<Integer> years = Lists.newArrayList();
        List<String> yearStrings = Lists.newArrayList();
        years = contentTimeMapper.getDistinctYears();
        Map<String,List<ArchiveVo>> archiveMap = Maps.newHashMap();
        for(Integer year : years){
            List<Archives> byYear =
                       contentTimeMapper.findByYear(year);
            String a  = String.valueOf(year);
            yearStrings.add(a);
            archiveMap.put(a, AriveConverter.converetToVo(byYear));
        }
        ModelAndView archive = new ModelAndView("archive");
        archive.addObject("years",yearStrings);
        archive.addObject("archiveMap",archiveMap);
        return archive;
    }
}
