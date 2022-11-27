package com.zzw.animalserve.entity.response;

import com.zzw.animalserve.entity.ArticleTag;
import com.zzw.animalserve.utils.TimeFormat;
import lombok.Data;

/**
 * @description(描述)
 * @autor: zhouzhengwei
 * @date: 2022/10/24__23:04
 */
@Data
public class ArticleTagVO {

    private Long aid;

    private Long tid;

    //标签信息

    private String title;

    private Long uid;

    private String createTime;

    private String updateTime;

    public static ArticleTagVO entityToVO(ArticleTag articleTag){
        ArticleTagVO articleTagVO = new ArticleTagVO();
        articleTagVO.setAid(articleTag.getArticleId());
        articleTagVO.setTid(articleTag.getTagId());
        articleTagVO.setTitle(articleTag.getTag().getTagTitle());
        articleTagVO.setUid(articleTag.getTag().getCreateId());
        articleTagVO.setCreateTime(TimeFormat.formatTime(articleTag.getTag().getCreateTime()));
        articleTagVO.setUpdateTime(TimeFormat.formatTime(articleTag.getTag().getUpdateTime()));
        return articleTagVO;
    }
}
