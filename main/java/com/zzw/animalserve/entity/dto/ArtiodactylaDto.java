package com.zzw.animalserve.entity.dto;

import com.zzw.animalserve.entity.Artiodactyla;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description(描述)
 * @autor: zhouzhengwei
 * @date: 2022/11/3__18:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArtiodactylaDto {

    @ApiModelProperty(value= "ID")
    private Long id;

    @ApiModelProperty(value= "name")
    private String name;

    @ApiModelProperty(value= "delete")
    private Integer delectTag;

    @ApiModelProperty(value= "aid")
    private Long aid;

    @ApiModelProperty(value= "cid")
    private Long cid;

    @ApiModelProperty(value= "mid")
    private Long mid;

    public Artiodactyla toEntity() {
        Artiodactyla artiodactyla = new Artiodactyla();
        artiodactyla.setArtiodactylaId(id);
        artiodactyla.setArtiodactylaName(name);
        artiodactyla.setDelectTag(delectTag);
        artiodactyla.setAnimaliaId(aid);
        artiodactyla.setChordataId(cid);
        artiodactyla.setMammaliaId(mid);
        return artiodactyla;
    }
}
