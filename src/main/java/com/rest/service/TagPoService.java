package com.rest.service;

import com.google.common.collect.Lists;
import com.rest.domain.TagPo;
import com.rest.mapper.ContentTagRelationDao;
import com.rest.mapper.TagPoDao;
import com.rest.vo.TagVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TagPoService {

    @Resource
    private TagPoDao tagPoDao;


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
}
