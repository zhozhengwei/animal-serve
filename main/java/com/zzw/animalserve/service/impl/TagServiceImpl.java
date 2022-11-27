package com.zzw.animalserve.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzw.animalserve.entity.Tag;
import com.zzw.animalserve.mapper.TagMapper;
import com.zzw.animalserve.service.TagService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Autowired
    private TagMapper tagMapper;

    /**
     * 查询表tag所有信息
     */
    @Override
    public List<Tag> findAllTag() {
        return tagMapper.findAllTag();
    }

    /**
     * 根据主键tagId查询表tag信息
     *
     * @param tagId
     */
    @Override
    public Tag findTagBytagId(@Param("tagId") Long tagId) {
        return tagMapper.findTagBytagId(tagId);
    }

    /**
     * 根据条件查询表tag信息
     *
     * @param tag
     */
    @Override
    public List<Tag> findTagByCondition(Tag tag) {
        return tagMapper.findTagByCondition(tag);
    }

    /**
     * 根据主键tagId查询表tag信息
     *
     * @param tagId
     */
    @Override
    public Integer deleteTagBytagId(@Param("tagId") Long tagId) {
        return tagMapper.deleteTagBytagId(tagId);
    }

    /**
     * 根据主键tagId更新表tag信息
     *
     * @param tag
     */
    @Override
    public Integer updateTagBytagId(Tag tag) {
        return tagMapper.updateTagBytagId(tag);
    }

    /**
     * 新增表tag信息
     *
     * @param tag
     */
    @Override
    public Integer addTag(Tag tag) {
        return tagMapper.addTag(tag);
    }

}