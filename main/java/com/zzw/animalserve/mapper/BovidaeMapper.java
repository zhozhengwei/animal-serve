package com.zzw.animalserve.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzw.animalserve.entity.Artiodactyla;
import com.zzw.animalserve.entity.Bovidae;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface BovidaeMapper extends BaseMapper<Bovidae> {

    /**Bovidae
     * 查询表bovidae所有信息
     */
    List<Bovidae> findAllBovidae();

    /**
     * 根据主键bovidaeId查询表bovidae信息
     *
     * @param bovidaeId
     */
    Bovidae findBovidaeBybovidaeId(@Param("bovidaeId") Long bovidaeId);

    Bovidae findBovidaeBybovidaeIdList(@Param("bovidaeId") Long bovidaeId);

    List<Bovidae> findBovidaeByartiodactylaId(@Param("artiodactylaId") Long artiodactylaId);
    /**
     * 根据条件查询表bovidae信息
     *
     * @param bovidae
     */
    List<Bovidae> findBovidaeByCondition(Bovidae bovidae);

    /**
     * 根据主键bovidaeId查询表bovidae信息
     *
     * @param bovidaeId
     */
    Integer deleteBovidaeBybovidaeId(@Param("bovidaeId") Long bovidaeId);

    /**
     * 根据主键bovidaeId更新表bovidae信息
     *
     * @param bovidae
     */
    Integer updateBovidaeBybovidaeId(Bovidae bovidae);

    /**
     * 新增表bovidae信息
     *
     * @param bovidae
     */
    Integer addBovidae(Bovidae bovidae);

}