package com.zzw.animalserve.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzw.animalserve.entity.Interlinkage;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface InterlinkageMapper  extends BaseMapper<Interlinkage> {

    /**
     * 查询表interlinkage所有信息
     */
    List<Interlinkage> findAllInterlinkage();

    /**
     * 根据主键interlinkageId查询表interlinkage信息
     *
     * @param interlinkageId
     */
    Interlinkage findInterlinkageByinterlinkageId(@Param("interlinkageId") Long interlinkageId);

    /**
     * 根据条件查询表interlinkage信息
     *
     * @param interlinkage
     */
    List<Interlinkage> findInterlinkageByCondition(Interlinkage interlinkage);

    /**
     * 根据主键interlinkageId查询表interlinkage信息
     *
     * @param interlinkageId
     */
    Integer deleteInterlinkageByinterlinkageId(@Param("interlinkageId") Long interlinkageId);

    /**
     * 根据主键interlinkageId更新表interlinkage信息
     *
     * @param interlinkage
     */
    Integer updateInterlinkageByinterlinkageId(Interlinkage interlinkage);

    /**
     * 新增表interlinkage信息
     *
     * @param interlinkage
     */
    Integer addInterlinkage(Interlinkage interlinkage);

}