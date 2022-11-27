package com.zzw.animalserve.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzw.animalserve.entity.Chordata;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository
@Mapper
public interface ChordataMapper  extends BaseMapper<Chordata> {

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

    List<Chordata> findChordataByanimaliaId(@Param("animaliaId") Long animaliaId);

    Chordata findChordataByChordataIdList(@Param("chordataId") Long chordataId);

    List<Chordata> findChordataByassortSitId(@Param("assortSitId") Long assortSitId);
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