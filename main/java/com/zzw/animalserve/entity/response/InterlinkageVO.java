package com.zzw.animalserve.entity.response;

import com.zzw.animalserve.entity.Interlinkage;
import com.zzw.animalserve.utils.TimeFormat;
import lombok.Data;

/**
 * @description(描述)
 * @autor: zhouzhengwei
 * @date: 2022/10/26__15:57
 */
@Data
public class InterlinkageVO {

    private Long id;

    private String name;

    private String linkUrl;

    private String introduction;

    private String createdAt;

    private String updatedAt;

    private Integer delectTag;

    public static InterlinkageVO entityToVO(Interlinkage interlinkage){
        InterlinkageVO interlinkageVO = new InterlinkageVO();
        interlinkageVO.setId(interlinkage.getInterlinkageId());
        interlinkageVO.setName(interlinkage.getInterlinkageName());
        interlinkageVO.setLinkUrl(interlinkage.getInterlinkageUrl());
        interlinkageVO.setIntroduction(interlinkage.getInterlinkageContent());
        interlinkageVO.setCreatedAt(TimeFormat.formatTime(interlinkage.getCreateTime()));
        interlinkageVO.setUpdatedAt(TimeFormat.formatTime(interlinkage.getUpdateTime()));
        interlinkageVO.setDelectTag(interlinkage.getDelectTag());
        return interlinkageVO;
    }
}
