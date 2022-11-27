package com.zzw.animalserve.entity.dto;

import com.zzw.animalserve.entity.ImageInformation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description(描述)
 * @autor: zhouzhengwei
 * @date: 2022/10/27__19:41
 */
@ApiModel(value = "图片DTO")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageInformationDto {

    @ApiModelProperty(value= "ID")
    private Long id;

    @ApiModelProperty(value= "图片地址")
    private String url;

    @ApiModelProperty(value= "图片名称")
    private String name;

    @ApiModelProperty(value= "图片简介")
    private String content;

    @ApiModelProperty(value= "图片类型")
    private Integer type;

    @ApiModelProperty(value= "图鉴标识")
    private String classType;


    public ImageInformation toEntity() {
        ImageInformation info = new ImageInformation();
        info.setImageInformationId(id);
        info.setImageInformationUrl(url);
        info.setImageInformationName(name);
        info.setImageInformationContent(content);
        info.setImageStatus(type);
        info.setImageClass(classType);
        return info;
    }
}
