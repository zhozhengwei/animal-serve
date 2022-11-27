package com.zzw.animalserve.entity.response;


import com.zzw.animalserve.entity.Article;
import com.zzw.animalserve.entity.ArticleTag;
import com.zzw.animalserve.utils.TimeFormat;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @description(描述)
 * @autor: zhouzhengwei
 * @date: 2022/10/20__23:42
 */
@Data
public class ArticleVO {

    private Long id;

    private String title;

    private String cover;

    private String introduction;

    private String text;

    private Integer attribution;

    private Long collectsCount;

    private Long commentCount;

    private Long lookCount;

    private String createTime;

    private Integer type;

    private Integer delectTag;

    //用户信息

    private Long uid;

    private String username;

    private String avatar;

    private String gender;



    // 标签信息

    private  List<ArticleTagVO> tagList;


    //封站前端返回字段===>可以使用脱敏，构建自己想要的数据
    public static ArticleVO entityToVO(Article articleEntity){
        ArticleVO articleVO = new ArticleVO();
        articleVO.setId(articleEntity.getArticleId());
        articleVO.setTitle(articleEntity.getArticleTitle());
        articleVO.setCover(articleEntity.getArticleCover());
        articleVO.setIntroduction(articleEntity.getArticleIntroduction());
        articleVO.setText(articleEntity.getArticleText());
        articleVO.setAttribution(articleEntity.getArticleAttribute());
        articleVO.setCollectsCount(articleEntity.getArticleCollectsCount());
        articleVO.setCommentCount(articleEntity.getArticleCommentsCount());
        articleVO.setLookCount(articleEntity.getArticleLookCount());
        articleVO.setCreateTime(TimeFormat.formatTime(articleEntity.getCreateTime()));
        articleVO.setType(articleEntity.getArticleAttribute());
        articleVO.setDelectTag(articleEntity.getDelectTag());
        //用户信息
        articleVO.setUid(articleEntity.getMember().getMemberId());
        articleVO.setUsername(articleEntity.getMember().getMemberName());
        articleVO.setAvatar(articleEntity.getMember().getMemberImage());
        articleVO.setGender(articleEntity.getMember().getMemberSex());
        //评价信息

        //标签信息
        List<ArticleTagVO> articleTagVOList = new ArrayList<>();
        for (ArticleTag temp:articleEntity.getTagList()) {
            ArticleTagVO articleTagVO = ArticleTagVO.entityToVO(temp);
            articleTagVOList.add(articleTagVO);
        }
        articleVO.setTagList(articleTagVOList);
        return articleVO;
    }

}
