package com.zzw.animalserve.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzw.animalserve.entity.Bovidae;
import com.zzw.animalserve.mapper.BovidaeMapper;
import com.zzw.animalserve.service.BovidaeService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Service
public class BovidaeServiceImpl extends ServiceImpl<BovidaeMapper, Bovidae> implements BovidaeService {

    @Autowired
    private BovidaeMapper bovidaeMapper;

    /**
     * 查询表bovidae所有信息
     */
    @Override
    public List<Bovidae> findAllBovidae() {
        return bovidaeMapper.findAllBovidae();
    }

    /**
     * 根据主键bovidaeId查询表bovidae信息
     *
     * @param bovidaeId
     */
    @Override
    public Bovidae findBovidaeBybovidaeId(@Param("bovidaeId") Long bovidaeId) {
        return bovidaeMapper.findBovidaeBybovidaeId(bovidaeId);
    }

    @Override
    public Bovidae findBovidaeBybovidaeIdList(@Param("bovidaeId") Long bovidaeId) {
        return bovidaeMapper.findBovidaeBybovidaeIdList(bovidaeId);
    }

    /**
     * 根据条件查询表bovidae信息
     *
     * @param bovidae
     */
    @Override
    public List<Bovidae> findBovidaeByCondition(Bovidae bovidae) {
        return bovidaeMapper.findBovidaeByCondition(bovidae);
    }

    /**
     * 根据主键bovidaeId查询表bovidae信息
     *
     * @param bovidaeId
     */
    @Override
    public Integer deleteBovidaeBybovidaeId(@Param("bovidaeId") Long bovidaeId) {
        return bovidaeMapper.deleteBovidaeBybovidaeId(bovidaeId);
    }

    /**
     * 根据主键bovidaeId更新表bovidae信息
     *
     * @param bovidae
     */
    @Override
    public Integer updateBovidaeBybovidaeId(Bovidae bovidae) {
        return bovidaeMapper.updateBovidaeBybovidaeId(bovidae);
    }

    /**
     * 新增表bovidae信息
     *
     * @param bovidae
     */
    @Override
    public Integer addBovidae(Bovidae bovidae) {
        return bovidaeMapper.addBovidae(bovidae);
    }

}