package com.zzw.animalserve.entity.dto;

import com.zzw.animalserve.entity.Mammalia;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description(描述)
 * @autor: zhouzhengwei
 * @date: 2022/11/3__18:51
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MammaliaDto {

    @ApiModelProperty(value= "ID")
    private Long id;

    @ApiModelProperty(value= "ID")
    private String name;

    @ApiModelProperty(value= "ID")
    private Integer delectTag;

    @ApiModelProperty(value= "aid")
    private Long aid;

    @ApiModelProperty(value= "cid")
    private Long cid;

    public Mammalia toEntity() {
        Mammalia mammalia = new Mammalia();
        mammalia.setMammaliaId(id);
        mammalia.setMammaliaName(name);
        mammalia.setDelectTag(delectTag);
        mammalia.setAnimaliaId(aid);
        mammalia.setChordataId(cid);
        return mammalia;
    }


}
