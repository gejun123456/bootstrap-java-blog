package com.rest.converter;

import com.google.common.collect.Lists;
import com.rest.domain.Archives;
import com.rest.vo.ArchiveVo;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by bruce.ge on 2016/11/7.
 */
public class AriveConverter {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");

    public static List<ArchiveVo> converetToVo(List<Archives> byYear) {
        List<ArchiveVo> archiveVos = Lists.newArrayList();
        for(Archives archives : byYear){
            ArchiveVo vo = convertToVo(archives);
            archiveVos.add(vo);
        }
        return archiveVos;
    }

    private static ArchiveVo convertToVo(Archives archives) {
        ArchiveVo vo = new ArchiveVo();
        vo.setAdddate(dateFormat.format(archives.getAddtime()));
        vo.setTitle(archives.getTitle());
        vo.setLink(buildLink(archives.getId()));
        vo.setId(archives.getId());
        return vo;
    }

    private static String buildLink(int id) {
        return "/getArticle/"+id;
    }
}
