package com.zzw.animalserve.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.zzw.animalserve.entity.Article;
import com.zzw.animalserve.entity.dto.ArticleDto;
import com.zzw.animalserve.entity.response.ArticleVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleService extends IService<Article> {

    PageInfo<ArticleVO> findPage(int pageNum, ArticleDto article);

    PageInfo<ArticleVO> history(int pageNum, ArticleDto articleDto);

    long count(Article article);


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
}