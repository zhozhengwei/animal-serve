package com.zzw.animalserve.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzw.animalserve.entity.Article;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

    /**
     * 查询表article所有信息
     */
    List<Article> findAllArticle();

    /**
     * 根据主键articleId查询表article信息
     *
     * @param articleId
     */
    Article findArticleByarticleId(@Param("articleId") Long articleId);

    /**
     * 根据主键memberId查询表article信息
     * @param memberId
     * @return
     */
    List<Article> findArticleByConditionUserId(@Param("memberId") Long memberId);

    /**
     * 根据条件查询表article信息
     *
     * @param article
     */
    List<Article> findArticleByCondition(Article article);

    /**
     * 根据主键articleId查询表article信息
     *
     * @param articleId
     */
    Integer deleteArticleByarticleId(@Param("articleId") Long articleId);

    /**
     * 根据主键articleId更新表article信息
     *
     * @param article
     */
    Integer updateArticleByarticleId(Article article);

    /**
     * 新增表article信息
     *
     * @param article
     */
    Integer addArticle(Article article);

    long count(Article article);
}