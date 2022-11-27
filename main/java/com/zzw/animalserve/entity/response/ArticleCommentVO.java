package com.zzw.animalserve.entity.response;

import com.zzw.animalserve.entity.ArticleComment;
import com.zzw.animalserve.entity.Comment;
import com.zzw.animalserve.utils.TimeFormat;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @description(描述)
 * @autor: zhouzhengwei
 * @date: 2022/10/24__20:15
 */
@Data
public class ArticleCommentVO {
    private Long aid;

    private Long cid;

    private String actions = "回复";

    //用户信息

    private Long uid;

    private String username;

    private String avatar;

    //评论信息

    private String content;

    private String datetime;

    private List<ArticleCommentVO> children;


    public static ArticleCommentVO entityToVO(ArticleComment articleComment){
        //递归的出口
//        if (articleComment.getComment() == null){
//            return null;
//        }
        ArticleCommentVO articleCommentVO = new ArticleCommentVO();
        articleCommentVO.setAid(articleComment.getArticleId());
        articleCommentVO.setCid(articleComment.getCommentId());
        //用户信息
        articleCommentVO.setUid(articleComment.getComment().getMember().getMemberId());
        articleCommentVO.setUsername(articleComment.getComment().getMember().getMemberName());
        articleCommentVO.setAvatar(articleComment.getComment().getMember().getMemberImage());
        //评论信息
        articleCommentVO.setContent(articleComment.getComment().getCommentContent());
        articleCommentVO.setDatetime(TimeFormat.formatTime(articleComment.getComment().getCreateTime()));
        //孩子
//        List<ArticleCommentVO> articleCommentVOList = new ArrayList<>();
//        for (Comment temp:articleComment.getComment().getChildren()) {
//            ArticleCommentVO toByteArray= new ArticleCommentVO();
//            ArticleComment comment = new ArticleComment();
//            comment.setComment(temp);
//            toByteArray.entityToVO(comment);
//            articleCommentVOList.add(toByteArray);
//        }
//        articleCommentVO.setChildren(articleCommentVOList);

        return articleCommentVO;
    }

}
