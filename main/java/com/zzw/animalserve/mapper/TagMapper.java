package com.zzw.animalserve.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzw.animalserve.entity.Tag;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface TagMapper  extends BaseMapper<Tag> {

    /**
     * 查询表tag所有信息
     */
    List<Tag> findAllTag();

    /**
     * 根据主键tagId查询表tag信息
     *
     * @param tagId
     */
    Tag findTagBytagId(@Param("tagId") Long tagId);

    /**
     * 根据条件查询表tag信息
     *
     * @param tag
     */
    List<Tag> findTagByCondition(Tag tag);

    /**
     * 根据主键tagId查询表tag信息
     *
     * @param tagId
     */
    Integer deleteTagBytagId(@Param("tagId") Long tagId);

    /**
     * 根据主键tagId更新表tag信息
     *
     * @param tag
     */
    Integer updateTagBytagId(Tag tag);

    /**
     * 新增表tag信息
     *
     * @param tag
     */
    Integer addTag(Tag tag);

}