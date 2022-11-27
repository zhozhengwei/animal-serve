package com.zzw.animalserve.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzw.animalserve.entity.ImageInformation;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ImageInformationMapper extends BaseMapper<ImageInformation> {

    /**
     * 查询表image_information所有信息
     */
    List<ImageInformation> findAllImageInformation();


    List<ImageInformation> findImageInformationBySearchList(String name);
    /**
     * 根据主键imageInformationId查询表image_information信息
     *
     * @param imageInformationId
     */
    ImageInformation findImageInformationByimageInformationId(@Param("imageInformationId") Long imageInformationId);

    /**
     * 根据条件查询表image_information信息
     *
     * @param imageInformation
     */
    List<ImageInformation> findImageInformationByCondition(ImageInformation imageInformation);

    /**
     * 根据主键imageInformationId查询表image_information信息
     *
     * @param imageInformationId
     */
    Integer deleteImageInformationByimageInformationId(@Param("imageInformationId") Long imageInformationId);

    /**
     * 根据主键imageInformationId更新表image_information信息
     *
     * @param imageInformation
     */
    Integer updateImageInformationByimageInformationId(ImageInformation imageInformation);

    /**
     * 新增表image_information信息
     *
     * @param imageInformation
     */
    Integer addImageInformation(ImageInformation imageInformation);

}