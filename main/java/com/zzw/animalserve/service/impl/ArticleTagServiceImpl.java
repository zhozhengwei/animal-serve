package com.zzw.animalserve.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzw.animalserve.entity.ArticleTag;
import com.zzw.animalserve.mapper.ArticleTagMapper;
import com.zzw.animalserve.service.ArticleTagService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Service
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag> implements ArticleTagService {

    @Autowired
    private ArticleTagMapper articleTagMapper;

    /**
     * 查询表article_tag所有信息
     */
    @Override
    public List<ArticleTag> findAllArticleTag() {
        return articleTagMapper.findAllArticleTag();
    }

    /**
     * 根据主键articleId查询表article_tag信息
     *
     * @param articleId
     */
    @Override
    public ArticleTag findArticleTagByarticleId(@Param("articleId") Long articleId) {
        return articleTagMapper.findArticleTagByarticleId(articleId);
    }

    /**
     * 根据条件查询表article_tag信息
     *
     * @param articleTag
     */
    @Override
    public List<ArticleTag> findArticleTagByCondition(ArticleTag articleTag) {
        return articleTagMapper.findArticleTagByCondition(articleTag);
    }

    /**
     * 根据主键articleId查询表article_tag信息
     *
     * @param articleId
     */
    @Override
    public Integer deleteArticleTagByarticleId(@Param("articleId") Long articleId) {
        return articleTagMapper.deleteArticleTagByarticleId(articleId);
    }

    /**
     * 根据主键articleId更新表article_tag信息
     *
     * @param articleTag
     */
    @Override
    public Integer updateArticleTagByarticleId(ArticleTag articleTag) {
        return articleTagMapper.updateArticleTagByarticleId(articleTag);
    }

    /**
     * 新增表article_tag信息
     *
     * @param articleTag
     */
    @Override
    public Integer addArticleTag(ArticleTag articleTag) {
        return articleTagMapper.addArticleTag(articleTag);
    }

}