package com.zzw.animalserve.entity.response;

import com.zzw.animalserve.entity.Publication;
import com.zzw.animalserve.utils.TimeFormat;
import lombok.Data;

/**
 * @description(描述)
 * @autor: zhouzhengwei
 * @date: 2022/10/26__19:27
 */
@Data
public class PublicationVO {

    private Long id;

    private String name;

    private String cover;

    private String url;

    private String introduction;

    private String createdAt;

    private String updatedAt;

    private Integer delectTag;

    public static PublicationVO entityToVO(Publication publication){
        PublicationVO publicationVO = new PublicationVO();
        publicationVO.setId(publication.getPublicationId());
        publicationVO.setName(publication.getPublicationName());
        publicationVO.setCover(publication.getPublicationImage());
        publicationVO.setUrl(publication.getPublicationUrl());
        publicationVO.setIntroduction(publication.getPublicationIntroduction());
        publicationVO.setCreatedAt(TimeFormat.formatTime(publication.getCreateTime()));
        publicationVO.setUpdatedAt(TimeFormat.formatTime(publication.getUpdateTime()));
        publicationVO.setDelectTag(publication.getDelectTag());
        return publicationVO;
    }

}
