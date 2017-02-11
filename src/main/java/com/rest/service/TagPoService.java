package com.rest.service;

import com.google.common.collect.Lists;
import com.rest.domain.Content;
import com.rest.domain.TagPo;
import com.rest.enums.StatusEnum;
import com.rest.mapper.ContentMapper;
import com.rest.mapper.ContentTagRelationDao;
import com.rest.mapper.TagPoDao;
import com.rest.vo.ContentForTagVo;
import com.rest.vo.TagVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
public class TagPoService {

    @Resource
    private TagPoDao tagPoDao;

    @Autowired
    private ContentMapper contentMapper;


    @Autowired
    private ContentTagRelationDao contentTagRelationDao;

    public int insert(TagPo pojo) {
        return tagPoDao.insert(pojo);
    }

    public int insertList(List<TagPo> pojos) {
        return tagPoDao.insertList(pojos);
    }

    public int update(TagPo pojo) {
        return tagPoDao.update(pojo);
    }

    public int updateTagNameById(String newTagName, Integer id) {
        return tagPoDao.updateTagNameById(newTagName, id);
    }

    public List<TagVo> findAll() {
        List<TagPo> all = tagPoDao.findAll();
        List<TagVo> vos = convertToVo(all);
        return vos;
    }


    public List<TagVo> findUsedTags() {
        List<TagVo> tagVolist = Lists.newArrayList();
        List<TagPo> all = tagPoDao.findAll();
//        Map<Integer, TagPo> collect = all.stream().collect(Collectors.toMap(x -> x.getId(), x -> x));
//        List<Integer> tagIdList = all.stream().map(TagPo::getId).collect(Collectors.toList());
        if (all.size() > 0) {
            for (TagPo tagPo : all) {
                List<Integer> contentIdByTagId = contentTagRelationDao.findContentIdByTagId(tagPo.getId());
                if (contentIdByTagId.size() > 0) {
                    int s = contentMapper.countByIdInAndStatus(contentIdByTagId, StatusEnum.ACTIVE.getValue());
                    if (s > 0) {
                        tagVolist.add(convertToVo(tagPo));
                    }
                }
            }
        }
        return tagVolist;
    }

    private List<TagVo> convertToVo(List<TagPo> all) {
        List<TagVo> vos = Lists.newArrayList();
        for (TagPo tagPo : all) {
            vos.add(convertToVo(tagPo));
        }
        return vos;
    }


    public List<String> findAllTagNames() {
        return tagPoDao.findTagName();
    }

    private TagVo convertToVo(TagPo tagPo) {
        TagVo tagVo = new TagVo();
        tagVo.setId(tagPo.getId());
        tagVo.setTagName(tagPo.getTagName());
        tagVo.setCreateTime(tagPo.getCreateTime());
        tagVo.setUpdateTime(tagPo.getUpdateTime());
        return tagVo;
    }

    public void delete(Integer id) {
        tagPoDao.deleteById(id);
    }


    public List<String> findTagsForContent(Integer contentId) {
        List<String> tagNames
            = Lists.newArrayList();
        List<Integer> tagIdByContentId = contentTagRelationDao.findTagIdByContentId(contentId);
        if (tagIdByContentId.size() != 0) {
            tagNames = tagPoDao.findTagNameByIdIn(tagIdByContentId);
        }
        return tagNames;
    }


    public List<ContentForTagVo> findContentForTagVo(Integer tagId) {
        List<ContentForTagVo> contentForTagVolist = Lists.newArrayList();
        List<Integer> contentIdByTagId = contentTagRelationDao.findContentIdByTagId(tagId);
        if (contentIdByTagId.size() > 0) {
            List<Content> titleAndAddtimeByIdInAndStatus = contentMapper.findIdAndTitleAndAddtimeByIdInAndStatus(contentIdByTagId, StatusEnum.ACTIVE.getValue());
            if (titleAndAddtimeByIdInAndStatus.size() > 0) {
                for (Content titleById : titleAndAddtimeByIdInAndStatus) {
                    ContentForTagVo contentForTagVo = new ContentForTagVo();
                    contentForTagVo.setContentTitle(titleById.getTitle());
                    contentForTagVo.setContentUrl("/getArticle/" + titleById.getId());
                    contentForTagVo.setAddTime(formatDate(titleById.getAddtime()));
                    contentForTagVolist.add(contentForTagVo);

                }
            }

        }
        return contentForTagVolist;
    }

    private static String formatDate(Date addtime) {
        LocalDate localDateTime = addtime.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
