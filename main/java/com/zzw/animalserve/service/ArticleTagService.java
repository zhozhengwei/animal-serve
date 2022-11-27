package com.zzw.animalserve.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zzw.animalserve.entity.ArticleTag;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleTagService extends IService<ArticleTag> {

    /**
     * 查询表article_tag所有信息
     */
    List<ArticleTag> findAllArticleTag();

    /**
     * 根据主键articleId查询表article_tag信息
     *
     * @param articleId
     */
    ArticleTag findArticleTagByarticleId(@Param("articleId") Long articleId);

    /**
     * 根据条件查询表article_tag信息
     *
     * @param articleTag
     */
    List<ArticleTag> findArticleTagByCondition(ArticleTag articleTag);

    /**
     * 根据主键articleId查询表article_tag信息
     *
     * @param articleId
     */
    Integer deleteArticleTagByarticleId(@Param("articleId") Long articleId);

    /**
     * 根据主键articleId更新表article_tag信息
     *
     * @param articleTag
     */
    Integer updateArticleTagByarticleId(ArticleTag articleTag);

    /**
     * 新增表article_tag信息
     *
     * @param articleTag
     */
    Integer addArticleTag(ArticleTag articleTag);
}