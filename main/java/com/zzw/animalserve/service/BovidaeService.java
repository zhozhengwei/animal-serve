package com.zzw.animalserve.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zzw.animalserve.entity.Bovidae;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BovidaeService extends IService<Bovidae> {

    /**
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