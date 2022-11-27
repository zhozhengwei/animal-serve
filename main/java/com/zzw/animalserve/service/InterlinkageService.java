package com.zzw.animalserve.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.zzw.animalserve.entity.Interlinkage;
import com.zzw.animalserve.entity.response.InterlinkageVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InterlinkageService  extends IService<Interlinkage> {

    PageInfo<InterlinkageVO> findPageList(int pageNum);

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