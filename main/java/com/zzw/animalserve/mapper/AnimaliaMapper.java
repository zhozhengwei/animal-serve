package com.zzw.animalserve.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzw.animalserve.entity.Animalia;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AnimaliaMapper extends BaseMapper<Animalia> {

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