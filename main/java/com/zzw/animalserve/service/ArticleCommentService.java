package com.zzw.animalserve.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zzw.animalserve.entity.ArticleComment;
import com.zzw.animalserve.entity.dto.ArticleCommentDto;
import com.zzw.animalserve.entity.response.ArticleCommentVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleCommentService  extends IService<ArticleComment> {

        Integer insComment(ArticleCommentDto articleCommentDto);

    List<ArticleCommentVO> findCommentByArticleId(Long articleId);

    /**
     * 查询表article_comment所有信息
     */
    List<ArticleComment> findAllArticleComment();

    /**
     * 根据主键articleId查询表article_comment信息
     *
     * @param articleId
     */
    ArticleComment findArticleCommentByarticleId(@Param("articleId") Long articleId);

    /**
     * 根据条件查询表article_comment信息
     *
     * @param articleComment
     */
    List<ArticleComment> findArticleCommentByCondition(ArticleComment articleComment);

    /**
     * 根据主键articleId查询表article_comment信息
     *
     * @param articleId
     */
    Integer deleteArticleCommentByarticleId(@Param("articleId") Long articleId);

    /**
     * 根据主键articleId更新表article_comment信息
     *
     * @param articleComment
     */
    Integer updateArticleCommentByarticleId(ArticleComment articleComment);

    /**
     * 新增表article_comment信息
     *
     * @param articleComment
     */
    Integer addArticleComment(ArticleComment articleComment);
}