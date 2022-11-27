package com.zzw.animalserve.entity.dto;

import com.zzw.animalserve.entity.Animalia;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description(描述)
 * @autor: zhouzhengwei
 * @date: 2022/11/3__16:59
 */
@ApiModel(value = "界DTO")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimaliaDto {
    @ApiModelProperty(value= "ID")
    private Long id;

    @ApiModelProperty(value= "名字")
    private String name;

    @ApiModelProperty(value= "delete")
    private Integer delectTag;

    public Animalia toEntity() {
        Animalia animalia = new Animalia();
        animalia.setAnimaliaId(id);
        animalia.setAnimaliaName(name);
        animalia.setDelectTag(delectTag);
        return animalia;
    }
}
