package com.zzw.animalserve.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zzw.animalserve.entity.AssortSit;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AssortSitService  extends IService<AssortSit> {

    /**
     * 查询表assort_sit所有信息
     */
    List<AssortSit> findAllAssortSit();

    /**
     * 根据主键assortSitId查询表assort_sit信息
     *
     * @param assortSitId
     */
    AssortSit findAssortSitByassortSitId(@Param("assortSitId") Long assortSitId);

    /**
     * 根据条件查询表assort_sit信息
     *
     * @param assortSit
     */
    List<AssortSit> findAssortSitByCondition(AssortSit assortSit);

    /**
     * 根据主键assortSitId查询表assort_sit信息
     *
     * @param assortSitId
     */
    Integer deleteAssortSitByassortSitId(@Param("assortSitId") Long assortSitId);

    /**
     * 根据主键assortSitId更新表assort_sit信息
     *
     * @param assortSit
     */
    Integer updateAssortSitByassortSitId(AssortSit assortSit);

    /**
     * 新增表assort_sit信息
     *
     * @param assortSit
     */
    Integer addAssortSit(AssortSit assortSit);
}