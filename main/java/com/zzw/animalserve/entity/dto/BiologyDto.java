package com.zzw.animalserve.entity.dto;

import com.zzw.animalserve.entity.Biology;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @description(描述)
 * @autor: zhouzhengwei
 * @date: 2022/10/17__23:25
 */

@ApiModel(value = "动物DTO")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BiologyDto {

    @ApiModelProperty(value= "ID")
    private Long id;

    @ApiModelProperty(value= "动物学名全称")
    private String name;

    @ApiModelProperty(value= "形态学描述")
    private String description;

    @ApiModelProperty(value= "生物学")
    private String biology;

    @ApiModelProperty(value= "海外分布")
    private String abroad;

    @ApiModelProperty(value= "国内分布")
    private String domestic;

    @ApiModelProperty(value= "属于XX属")
    private Long bosId;

    @ApiModelProperty(value= "属于XX科")
    private Long bovidaeId;

    @ApiModelProperty(value= "属于XX目")
    private Long artiodactylaId;

    @ApiModelProperty(value= "属于XX纲")
    private Long mammaliaId;

    @ApiModelProperty(value= "属于XX门")
    private Long chordataId;

    @ApiModelProperty(value= "属于动物界")
    private Long animaliaId;

    @ApiModelProperty(value= "图片列表")
    private List<String> images;

    public Biology toEntity(){
        Biology toBiology = new Biology();
        toBiology.setBiologyId(id);
        toBiology.setBiologyName(name);
        toBiology.setMorphologicalDescription(description);
        toBiology.setBiology(biology);
        toBiology.setDistributionAbroad(abroad);
        toBiology.setDomesticDistribution(domestic);
        toBiology.setBosId(bosId);
        toBiology.setBovidaeId(bovidaeId);
        toBiology.setArtiodactylaId(artiodactylaId);
        toBiology.setMammaliaId(mammaliaId);
        toBiology.setChordataId(chordataId);
        toBiology.setAnimaliaId(animaliaId);
        return toBiology;
    }

}
