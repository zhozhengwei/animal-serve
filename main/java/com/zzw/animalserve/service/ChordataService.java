package com.zzw.animalserve.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zzw.animalserve.entity.Animalia;
import com.zzw.animalserve.entity.Chordata;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChordataService   extends IService<Chordata> {

    /**
     * 查询表chordata所有信息
     */
    List<Chordata> findAllChordata();

    /**
     * 根据主键chordataId查询表chordata信息
     *
     * @param chordataId
     */
    Chordata findChordataBychordataId(@Param("chordataId") Long chordataId);

    Chordata findChordataByChordataIdList(@Param("chordataId") Long chordataId);

    /**
     * 根据条件查询表chordata信息
     *
     * @param chordata
     */
    List<Chordata> findChordataByCondition(Chordata chordata);

    /**
     * 根据主键chordataId查询表chordata信息
     *
     * @param chordataId
     */
    Integer deleteChordataBychordataId(@Param("chordataId") Long chordataId);

    /**
     * 根据主键chordataId更新表chordata信息
     *
     * @param chordata
     */
    Integer updateChordataBychordataId(Chordata chordata);

    /**
     * 新增表chordata信息
     *
     * @param chordata
     */
    Integer addChordata(Chordata chordata);
}