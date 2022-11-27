package com.zzw.animalserve.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzw.animalserve.entity.BiologyImage;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface BiologyImageMapper extends BaseMapper<BiologyImage> {

    /**
     * 查询表biology_image所有信息
     */
    List<BiologyImage> findAllBiologyImage();

    /**
     * 根据主键biologyId查询表biology_image信息
     *
     * @param biologyId
     */
    BiologyImage findBiologyImageBybiologyId(@Param("biologyId") Long biologyId);

    List<BiologyImage> findBiologyImageListBybiologyId(@Param("biologyId") Long biologyId);

    /**
     * 根据条件查询表biology_image信息
     *
     * @param biologyImage
     */
    List<BiologyImage> findBiologyImageByCondition(BiologyImage biologyImage);

    /**
     * 根据主键biologyId查询表biology_image信息
     *
     * @param biologyId
     */
    Integer deleteBiologyImageBybiologyId(@Param("biologyId") Long biologyId);

    /**
     * 根据主键biologyId更新表biology_image信息
     *
     * @param biologyImage
     */
    Integer updateBiologyImageBybiologyId(BiologyImage biologyImage);

    /**
     * 新增表biology_image信息
     *
     * @param biologyImage
     */
    Integer addBiologyImage(BiologyImage biologyImage);

}