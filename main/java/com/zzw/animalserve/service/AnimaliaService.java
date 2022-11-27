package com.zzw.animalserve.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zzw.animalserve.entity.Animalia;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AnimaliaService extends IService<Animalia> {

    /**
     * 查询表animalia所有信息
     */
    List<Animalia> findAllAnimalia();

    /**
     * 根据主键animaliaId查询表animalia信息
     *
     * @param animaliaId
     */
    Animalia findAnimaliaByanimaliaId(@Param("animaliaId") Long animaliaId);

    /**
     * 根据条件查询表animalia信息
     *
     * @param animalia
     */
    List<Animalia> findAnimaliaByCondition(Animalia animalia);

    /**
     * 根据主键animaliaId查询表animalia信息
     *
     * @param animaliaId
     */
    Integer deleteAnimaliaByanimaliaId(@Param("animaliaId") Long animaliaId);

    /**
     * 根据主键animaliaId更新表animalia信息
     *
     * @param animalia
     */
    Integer updateAnimaliaByanimaliaId(Animalia animalia);

    /**
     * 新增表animalia信息
     *
     * @param animalia
     */
    Integer addAnimalia(Animalia animalia);
}