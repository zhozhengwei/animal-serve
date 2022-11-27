package com.zzw.animalserve.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzw.animalserve.entity.Mammalia;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface MammaliaMapper  extends BaseMapper<Mammalia> {

    /**
     * 查询表mammalia所有信息
     */
    List<Mammalia> findAllMammalia();

    /**
     * 根据主键mammaliaId查询表mammalia信息
     *
     * @param mammaliaId
     */
    Mammalia findMammaliaBymammaliaId(@Param("mammaliaId") Long mammaliaId);


    Mammalia findMammaliaBymammaliaIdList(@Param("mammaliaId") Long mammaliaId);


    List<Mammalia> findMammaliaListBymammaliaId(@Param("mammaliaId") Long mammaliaId);

    List<Mammalia> findMammaliaBychordataId(@Param("chordataId") Long chordataId);
    /**
     * 根据条件查询表mammalia信息
     *
     * @param mammalia
     */
    List<Mammalia> findMammaliaByCondition(Mammalia mammalia);

    /**
     * 根据主键mammaliaId查询表mammalia信息
     *
     * @param mammaliaId
     */
    Integer deleteMammaliaBymammaliaId(@Param("mammaliaId") Long mammaliaId);

    /**
     * 根据主键mammaliaId更新表mammalia信息
     *
     * @param mammalia
     */
    Integer updateMammaliaBymammaliaId(Mammalia mammalia);

    /**
     * 新增表mammalia信息
     *
     * @param mammalia
     */
    Integer addMammalia(Mammalia mammalia);

}