package com.zzw.animalserve.entity.dto;

import com.zzw.animalserve.entity.Bovidae;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description(描述)
 * @autor: zhouzhengwei
 * @date: 2022/11/3__19:24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BovidaeDto {

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

    public Bovidae toEntity() {
        Bovidae bovidae = new Bovidae();
        bovidae.setBovidaeId(id);
        bovidae.setBovidaeName(name);
        bovidae.setDelectTag(delectTag);
        bovidae.setAnimaliaId(aid);
        bovidae.setChordataId(cid);
        bovidae.setMammaliaId(mid);
        bovidae.setArtiodactylaId(arid);
        return bovidae;
    }
}
