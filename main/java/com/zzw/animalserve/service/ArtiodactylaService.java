package com.zzw.animalserve.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zzw.animalserve.entity.Artiodactyla;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArtiodactylaService extends IService<Artiodactyla> {

    /**
     * 查询表artiodactyla所有信息
     */
    List<Artiodactyla> findAllArtiodactyla();

    /**
     * 根据主键artiodactylaId查询表artiodactyla信息
     *
     * @param artiodactylaId
     */
    Artiodactyla findArtiodactylaByartiodactylaId(@Param("artiodactylaId") Long artiodactylaId);

    Artiodactyla findArtiodactylaByartiodactylaIdList(@Param("artiodactylaId") Long artiodactylaId);

    /**
     * 根据条件查询表artiodactyla信息
     *
     * @param artiodactyla
     */
    List<Artiodactyla> findArtiodactylaByCondition(Artiodactyla artiodactyla);

    /**
     * 根据主键artiodactylaId查询表artiodactyla信息
     *
     * @param artiodactylaId
     */
    Integer deleteArtiodactylaByartiodactylaId(@Param("artiodactylaId") Long artiodactylaId);

    /**
     * 根据主键artiodactylaId更新表artiodactyla信息
     *
     * @param artiodactyla
     */
    Integer updateArtiodactylaByartiodactylaId(Artiodactyla artiodactyla);

    /**
     * 新增表artiodactyla信息
     *
     * @param artiodactyla
     */
    Integer addArtiodactyla(Artiodactyla artiodactyla);
}