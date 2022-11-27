package com.zzw.animalserve.entity.dto;

import com.zzw.animalserve.entity.ArticleComment;
import com.zzw.animalserve.entity.Comment;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description(描述)
 * @autor: zhouzhengwei
 * @date: 2022/10/24__23:44
 */
@ApiModel(value = "评论DTO")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleCommentDto {

    @ApiModelProperty(value= "文章ID")
    private Long aid;

    @ApiModelProperty(value= "用户ID")
    private Long uid;

    @ApiModelProperty(value= "评论ID")
    private Long cid;

    @ApiModelProperty(value= "子级评价")
    private Long parentId;

    @ApiModelProperty(value= "评论内容")
    private String content;

    public ArticleComment toEntity() {
        ArticleComment toEntity = new ArticleComment();
        toEntity.setArticleId(aid);
        toEntity.setCommentId(cid);
        Comment comment = new Comment();
        comment.setMemberId(uid);
        comment.setParentCmtId(parentId);
        comment.setCommentContent(content);
        toEntity.setComment(comment);
        return toEntity;
    }


}
