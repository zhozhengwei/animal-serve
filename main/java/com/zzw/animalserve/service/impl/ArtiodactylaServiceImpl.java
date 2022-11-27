package com.zzw.animalserve.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzw.animalserve.entity.Artiodactyla;
import com.zzw.animalserve.mapper.ArtiodactylaMapper;
import com.zzw.animalserve.service.ArtiodactylaService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Service
public class ArtiodactylaServiceImpl extends ServiceImpl<ArtiodactylaMapper, Artiodactyla> implements ArtiodactylaService {

    @Autowired
    private ArtiodactylaMapper artiodactylaMapper;

    /**
     * 查询表artiodactyla所有信息
     */
    @Override
    public List<Artiodactyla> findAllArtiodactyla() {
        return artiodactylaMapper.findAllArtiodactyla();
    }

    /**
     * 根据主键artiodactylaId查询表artiodactyla信息
     *
     * @param artiodactylaId
     */
    @Override
    public Artiodactyla findArtiodactylaByartiodactylaId(@Param("artiodactylaId") Long artiodactylaId) {
        return artiodactylaMapper.findArtiodactylaByartiodactylaId(artiodactylaId);
    }

    @Override
    public Artiodactyla findArtiodactylaByartiodactylaIdList(@Param("artiodactylaId") Long artiodactylaId) {
        return artiodactylaMapper.findArtiodactylaByartiodactylaIdList(artiodactylaId);
    }

    /**
     * 根据条件查询表artiodactyla信息
     *
     * @param artiodactyla
     */
    @Override
    public List<Artiodactyla> findArtiodactylaByCondition(Artiodactyla artiodactyla) {
        return artiodactylaMapper.findArtiodactylaByCondition(artiodactyla);
    }

    /**
     * 根据主键artiodactylaId查询表artiodactyla信息
     *
     * @param artiodactylaId
     */
    @Override
    public Integer deleteArtiodactylaByartiodactylaId(@Param("artiodactylaId") Long artiodactylaId) {
        return artiodactylaMapper.deleteArtiodactylaByartiodactylaId(artiodactylaId);
    }

    /**
     * 根据主键artiodactylaId更新表artiodactyla信息
     *
     * @param artiodactyla
     */
    @Override
    public Integer updateArtiodactylaByartiodactylaId(Artiodactyla artiodactyla) {
        return artiodactylaMapper.updateArtiodactylaByartiodactylaId(artiodactyla);
    }

    /**
     * 新增表artiodactyla信息
     *
     * @param artiodactyla
     */
    @Override
    public Integer addArtiodactyla(Artiodactyla artiodactyla) {
        return artiodactylaMapper.addArtiodactyla(artiodactyla);
    }

}