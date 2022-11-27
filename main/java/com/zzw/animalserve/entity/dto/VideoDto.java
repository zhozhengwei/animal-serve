package com.zzw.animalserve.entity.dto;

import com.zzw.animalserve.entity.VideoInformation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description(描述)
 * @autor: zhouzhengwei
 * @date: 2022/10/18__2:10
 */

@ApiModel(value = "视频DTO")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoDto {

    @ApiModelProperty(value= "ID")
    private Long id;

    @ApiModelProperty(value= "视频封面")
    private String poster;

    @ApiModelProperty(value= "视频地址")
    private String url;

    @ApiModelProperty(value= "视频介绍")
    private String content;

    @ApiModelProperty(value= "视频类型")
    private Integer type;

    public VideoInformation toEntity() {
        VideoInformation videoInformation = new VideoInformation();
        videoInformation.setVideoInformationId(id);
        videoInformation.setVideoInformationUrl(url);
        videoInformation.setVideoInformationPoster(poster);
        videoInformation.setVideoInformationContent(content);
        videoInformation.setVideoInformationType(type);
        return videoInformation;
    }
}
