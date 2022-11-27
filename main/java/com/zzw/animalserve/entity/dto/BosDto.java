package com.zzw.animalserve.entity.dto;

import com.zzw.animalserve.entity.Bos;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description(描述)
 * @autor: zhouzhengwei
 * @date: 2022/11/3__20:03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BosDto {

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

    @ApiModelProperty(value= "arid")
    private Long arid;

    @ApiModelProperty(value= "bid")
    private Long bid;

    public Bos toEntity() {
        Bos bos = new Bos();
        bos.setBosId(id);
        bos.setBosName(name);
        bos.setDelectTag(delectTag);
        bos.setAnimaliaId(aid);
        bos.setChordataId(cid);
        bos.setMammaliaId(mid);
        bos.setArtiodactylaId(arid);
        bos.setBovidaeId(bid);
        return bos;
    }
}
