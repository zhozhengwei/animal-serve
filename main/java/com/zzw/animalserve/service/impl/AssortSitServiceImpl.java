package com.zzw.animalserve.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzw.animalserve.entity.AssortSit;
import com.zzw.animalserve.mapper.AssortSitMapper;
import com.zzw.animalserve.service.AssortSitService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Service
public class AssortSitServiceImpl extends ServiceImpl<AssortSitMapper, AssortSit> implements AssortSitService {

    @Autowired
    private AssortSitMapper assortSitMapper;

    /**
     * 查询表assort_sit所有信息
     */
    @Override
    public List<AssortSit> findAllAssortSit() {
        return assortSitMapper.findAllAssortSit();
    }

    /**
     * 根据主键assortSitId查询表assort_sit信息
     *
     * @param assortSitId
     */
    @Override
    public AssortSit findAssortSitByassortSitId(@Param("assortSitId") Long assortSitId) {
        return assortSitMapper.findAssortSitByassortSitId(assortSitId);
    }

    /**
     * 根据条件查询表assort_sit信息
     *
     * @param assortSit
     */
    @Override
    public List<AssortSit> findAssortSitByCondition(AssortSit assortSit) {
        return assortSitMapper.findAssortSitByCondition(assortSit);
    }

    /**
     * 根据主键assortSitId查询表assort_sit信息
     *
     * @param assortSitId
     */
    @Override
    public Integer deleteAssortSitByassortSitId(@Param("assortSitId") Long assortSitId) {
        return assortSitMapper.deleteAssortSitByassortSitId(assortSitId);
    }

    /**
     * 根据主键assortSitId更新表assort_sit信息
     *
     * @param assortSit
     */
    @Override
    public Integer updateAssortSitByassortSitId(AssortSit assortSit) {
        return assortSitMapper.updateAssortSitByassortSitId(assortSit);
    }

    /**
     * 新增表assort_sit信息
     *
     * @param assortSit
     */
    @Override
    public Integer addAssortSit(AssortSit assortSit) {
        return assortSitMapper.addAssortSit(assortSit);
    }

}