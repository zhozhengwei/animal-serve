package com.zzw.animalserve.entity.dto;

import com.zzw.animalserve.entity.Chordata;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description(描述)
 * @autor: zhouzhengwei
 * @date: 2022/11/3__18:36
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChordataDto {

    @ApiModelProperty(value= "ID")
    private Long id;

    @ApiModelProperty(value= "name")
    private String name;

    @ApiModelProperty(value= "tag")
    private Integer delectTag;

    @ApiModelProperty(value= "aid")
    private Long aid;

    public Chordata toEntity() {
        Chordata chordata = new Chordata();
        chordata.setChordataId(id);
        chordata.setChordataName(name);
        chordata.setDelectTag(delectTag);
        chordata.setAnimaliaId(aid);
        return chordata;
    }
}
