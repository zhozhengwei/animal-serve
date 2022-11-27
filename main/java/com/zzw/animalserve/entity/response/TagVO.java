package com.zzw.animalserve.entity.response;

import com.zzw.animalserve.entity.Tag;
import com.zzw.animalserve.utils.TimeFormat;
import lombok.Data;

/**
 * @description(描述)
 * @autor: zhouzhengwei
 * @date: 2022/11/15__1:25
 */

@Data
public class TagVO {

    private Long id;

    private String name;

    private String createdAt;

    private String updatedAt;

    private Integer delete;

    public static TagVO entityToVO(Tag tag){
        TagVO tagVO = new TagVO();
        tagVO.setId(tag.getTagId());
        tagVO.setName(tag.getTagTitle());
        tagVO.setCreatedAt(TimeFormat.formatTime(tag.getCreateTime()));
        tagVO.setUpdatedAt(TimeFormat.formatTime(tag.getUpdateTime()));
        tagVO.setDelete(tag.getDelectTag());
        return tagVO;
    }
}
