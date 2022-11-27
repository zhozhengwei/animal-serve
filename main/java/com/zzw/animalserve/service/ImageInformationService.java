package com.zzw.animalserve.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.zzw.animalserve.entity.Book;
import com.zzw.animalserve.entity.ImageInformation;
import com.zzw.animalserve.entity.dto.ImageInformationDto;
import com.zzw.animalserve.entity.response.ImageInformationVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ImageInformationService extends IService<ImageInformation> {

    PageInfo<ImageInformationVO> findPage(int pageNum, ImageInformationDto imageInformationDto);

    PageInfo<ImageInformationVO> findClassType(int pageNum, String name);

    /**
     * 查询表image_information所有信息
     */
    List<ImageInformation> findAllImageInformation();

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