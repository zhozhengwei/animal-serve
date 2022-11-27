package com.zzw.animalserve.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzw.animalserve.entity.Bos;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface BosMapper extends BaseMapper<Bos> {

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

    List<Bos> findBosBybovidaeId(@Param("bovidaeId") Long bovidaeId);

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