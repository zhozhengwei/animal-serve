package com.zzw.animalserve.entity.dto;

import com.zzw.animalserve.entity.AssortSit;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description(描述)
 * @autor: zhouzhengwei
 * @date: 2022/11/4__0:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssortSitDto {

    @ApiModelProperty(value= "ID")
    private Long id;

    @ApiModelProperty(value= "name")
    private String name;

    @ApiModelProperty(value= "uid")
    private Long uid;

    @ApiModelProperty(value= "delete")
    private Integer delectTag;


    public AssortSit toEntity() {
        AssortSit assortSit = new AssortSit();
        assortSit.setAssortSitId(id);
        assortSit.setAssortSitName(name);
        assortSit.setCreateId(uid);
        assortSit.setDelectTag(delectTag);
        return assortSit;
    }
}
