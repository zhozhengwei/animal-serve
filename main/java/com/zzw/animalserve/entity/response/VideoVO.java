package com.zzw.animalserve.entity.response;

import com.zzw.animalserve.entity.VideoInformation;
import com.zzw.animalserve.utils.TimeFormat;
import io.swagger.models.auth.In;
import lombok.Data;

/**
 * @description(描述)
 * @autor: zhouzhengwei
 * @date: 2022/10/27__17:12
 */
@Data
public class VideoVO {

    private Long id;

    private String url;

    private String poster;

    private String introduction;

    private Integer type;

    private String createdAt;

    private String updatedAt;

    private Integer delectTag;

    public static VideoVO entityToVO(VideoInformation videoInformation){
        VideoVO videoVO = new VideoVO();
        videoVO.setId(videoInformation.getVideoInformationId());
        videoVO.setUrl(videoInformation.getVideoInformationUrl());
        videoVO.setPoster(videoInformation.getVideoInformationPoster());
        videoVO.setIntroduction(videoInformation.getVideoInformationContent());
        videoVO.setType(videoInformation.getVideoInformationType());
        videoVO.setCreatedAt(TimeFormat.formatTime(videoInformation.getCreateTime()));
        videoVO.setUpdatedAt(TimeFormat.formatTime(videoInformation.getUpdateTime()));
        videoVO.setDelectTag(videoInformation.getDelectTag());
        return videoVO;
    }
}
