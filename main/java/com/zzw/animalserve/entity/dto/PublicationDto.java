package com.zzw.animalserve.entity.dto;

import com.zzw.animalserve.entity.Publication;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description(描述)
 * @autor: zhouzhengwei
 * @date: 2022/11/2__20:17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublicationDto {
    @ApiModelProperty(value= "ID")
    private Long id;

    @ApiModelProperty(value= "刊物名")
    private String name;

    @ApiModelProperty(value= "封面")
    private String cover;

    @ApiModelProperty(value= "地址")
    private String url;

    @ApiModelProperty(value= "描述")
    private String introduction;

    @ApiModelProperty(value= "逻辑删除")
    private Integer delectTag;

    public Publication toEntity() {
        Publication publication = new Publication();
        publication.setPublicationId(id);
        publication.setPublicationName(name);
        publication.setPublicationUrl(url);
        publication.setPublicationImage(cover);
        publication.setPublicationIntroduction(introduction);
        publication.setDelectTag(delectTag);
        return publication;
    }
}
