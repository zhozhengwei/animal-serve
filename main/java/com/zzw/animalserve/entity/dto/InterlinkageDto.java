package com.zzw.animalserve.entity.dto;

import com.zzw.animalserve.entity.Interlinkage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description(描述)
 * @autor: zhouzhengwei
 * @date: 2022/11/13__22:16
 */

@ApiModel(value = "外部链接DTO")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InterlinkageDto {

    @ApiModelProperty(value= "ID")
    private Long id;

    @ApiModelProperty(value= "外部url")
    private String url;

    @ApiModelProperty(value= "链接名称")
    private String name;

    @ApiModelProperty(value= "描述")
    private String content;

    public Interlinkage toEntity() {
        Interlinkage interlinkage = new Interlinkage();
        interlinkage.setInterlinkageId(id);
        interlinkage.setInterlinkageName(name);
        interlinkage.setInterlinkageUrl(url);
        interlinkage.setInterlinkageContent(content);
        return interlinkage;
    }
}
