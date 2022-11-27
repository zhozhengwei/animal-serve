package com.zzw.animalserve.entity.dto;

import com.zzw.animalserve.entity.Tag;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description(描述)
 * @autor: zhouzhengwei
 * @date: 2022/11/15__4:27
 */

@ApiModel(value = "标签DTO")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagDto {

    @ApiModelProperty(value= "ID")
    private Long id;

    @ApiModelProperty(value= "标签名")
    private String name;

    @ApiModelProperty(value= "delete")
    private Integer delectTag;


    public Tag toEntity() {
        Tag tag = new Tag();
        tag.setTagId(id);
        tag.setTagTitle(name);
        tag.setDelectTag(delectTag);
        return tag;
    }

}
