package com.zzw.animalserve.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzw.animalserve.entity.ArticleComment;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ArticleCommentMapper  extends BaseMapper<ArticleComment> {

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


    List<ArticleComment> findArticleCommentListByarticleId(@Param("articleId") Long articleId);
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