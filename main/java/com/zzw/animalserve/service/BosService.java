package com.zzw.animalserve.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zzw.animalserve.entity.Bos;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BosService  extends IService<Bos> {

    /**
     * 查询表bos所有信息
     */
    List<Bos> findAllBos();

    /**
     * 根据主键bosId查询表bos信息
     *
     * @param bosId
     */
    Bos findBosBybosId(@Param("bosId") Long bosId);

    Bos findBosBybosIdList(@Param("bosId") Long bosId);

    /**
     * 根据条件查询表bos信息
     *
     * @param bos
     */
    List<Bos> findBosByCondition(Bos bos);

    /**
     * 根据主键bosId查询表bos信息
     *
     * @param bosId
     */
    Integer deleteBosBybosId(@Param("bosId") Long bosId);

    /**
     * 根据主键bosId更新表bos信息
     *
     * @param bos
     */
    Integer updateBosBybosId(Bos bos);

    /**
     * 新增表bos信息
     *
     * @param bos
     */
    Integer addBos(Bos bos);
}