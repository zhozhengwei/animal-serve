package com.zzw.animalserve.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzw.animalserve.entity.Animalia;
import com.zzw.animalserve.mapper.AnimaliaMapper;
import com.zzw.animalserve.service.AnimaliaService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Service
public class AnimaliaServiceImpl extends ServiceImpl<AnimaliaMapper, Animalia> implements AnimaliaService {

    @Autowired
    private AnimaliaMapper animaliaMapper;

    /**
     * 查询表animalia所有信息
     */
    @Override
    public List<Animalia> findAllAnimalia() {
        return animaliaMapper.findAllAnimalia();
    }

    /**
     * 根据主键animaliaId查询表animalia信息
     *
     * @param animaliaId
     */
    @Override
    public Animalia findAnimaliaByanimaliaId(@Param("animaliaId") Long animaliaId) {
        return animaliaMapper.findAnimaliaByanimaliaId(animaliaId);
    }

    /**
     * 根据条件查询表animalia信息
     *
     * @param animalia
     */
    @Override
    public List<Animalia> findAnimaliaByCondition(Animalia animalia) {
        return animaliaMapper.findAnimaliaByCondition(animalia);
    }

    /**
     * 根据主键animaliaId查询表animalia信息
     *
     * @param animaliaId
     */
    @Override
    public Integer deleteAnimaliaByanimaliaId(@Param("animaliaId") Long animaliaId) {
        return animaliaMapper.deleteAnimaliaByanimaliaId(animaliaId);
    }

    /**
     * 根据主键animaliaId更新表animalia信息
     *
     * @param animalia
     */
    @Override
    public Integer updateAnimaliaByanimaliaId(Animalia animalia) {
        return animaliaMapper.updateAnimaliaByanimaliaId(animalia);
    }

    /**
     * 新增表animalia信息
     *
     * @param animalia
     */
    @Override
    public Integer addAnimalia(Animalia animalia) {
        return animaliaMapper.addAnimalia(animalia);
    }

}