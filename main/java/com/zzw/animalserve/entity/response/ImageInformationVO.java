package com.zzw.animalserve.entity.response;

import com.zzw.animalserve.entity.ImageInformation;
import com.zzw.animalserve.utils.TimeFormat;
import lombok.Data;

/**
 * @description(描述)
 * @autor: zhouzhengwei
 * @date: 2022/10/27__19:47
 */
@Data
public class ImageInformationVO {

    private Long id;

    private String url;

    private String name;

    private String introduction;

    private Integer status;

    private String classType;

    private String createdAt;

    private String updatedAt;

    private Integer delectTag;

    public static ImageInformationVO entityToVO(ImageInformation imageInformation){
        ImageInformationVO imageInformationVO = new ImageInformationVO();
        imageInformationVO.setId(imageInformation.getImageInformationId());
        imageInformationVO.setUrl(imageInformation.getImageInformationUrl());
        imageInformationVO.setName(imageInformation.getImageInformationName());
        imageInformationVO.setIntroduction(imageInformation.getImageInformationContent());
        imageInformationVO.setStatus(imageInformation.getImageStatus());
        imageInformationVO.setClassType(imageInformation.getImageClass());
        imageInformationVO.setCreatedAt(imageInformation.getCreateTime());
        imageInformationVO.setUpdatedAt(imageInformation.getUpdateTime());
        imageInformationVO.setDelectTag(imageInformation.getDelectTag());
        return imageInformationVO;
    }

}
