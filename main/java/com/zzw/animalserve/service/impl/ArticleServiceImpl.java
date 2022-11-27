package com.zzw.animalserve.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zzw.animalserve.entity.Article;
import com.zzw.animalserve.entity.dto.ArticleDto;
import com.zzw.animalserve.entity.response.ArticleVO;
import com.zzw.animalserve.mapper.ArticleMapper;
import com.zzw.animalserve.service.ArticleService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    /**
     *
     *业务逻辑层查询所有条件查询新闻或者是文章
     *
     * @param pageNum
     * @param articleDto
     * @return
     */
    @Override
    public PageInfo<ArticleVO> findPage(int pageNum, ArticleDto articleDto) {
        Article article = articleDto.toEntity();
        System.out.println("====请求输入的文章类型数据===>"+article.toString());
        //设置分页的当前页和每页条数
        PageHelper.startPage(pageNum, 10);
        //数据访问层查询所有供暖号码的方法
        List<Article> heatingNumbers = articleMapper.findArticleByCondition(article);
        PageInfo pageResult = new PageInfo(heatingNumbers);
        //排序时间最大的返回给到前端
        Collections.sort(heatingNumbers, new Comparator<Article>() {
            @Override
            public int compare(Article o1, Article o2) {
                return o2.getCreateTime().compareTo(o1.getCreateTime());
            }
        });
        //将信息进行封装
        List<ArticleVO> articleVOList = new ArrayList<>();
        for (Article temp: heatingNumbers) {
            if(temp.getDelectTag() == 0){
                ArticleVO articleVO = ArticleVO.entityToVO(temp);
                articleVOList.add(articleVO);
            }
        }
        pageResult.setList(articleVOList);
        //实例化PageInfo
        return pageResult;
    }

    @Override
    public PageInfo<ArticleVO> history(int pageNum, ArticleDto articleDto){
        Article article = articleDto.toEntity();
        //设置分页的当前页和每页条数
        PageHelper.startPage(pageNum, 10);
        List<Article> history = articleMapper.findArticleByConditionUserId(article.getMemberId());
        PageInfo pageResult = new PageInfo(history);
        Collections.sort(history, new Comparator<Article>() {

            @Override
            public int compare(Article o1, Article o2) {
                return o2.getCreateTime().compareTo(o1.getCreateTime());
            }
        });
        //将信息进行封装
        List<ArticleVO> articleVOList = new ArrayList<>();
        for (Article temp: history) {
            if(temp.getDelectTag() == 0){
                ArticleVO articleVO = ArticleVO.entityToVO(temp);
                articleVOList.add(articleVO);
            }
        }
        pageResult.setList(articleVOList);
        //实例化PageInfo
        return pageResult;
    }

    @Override
    public long count(Article article) {
        return articleMapper.count(article);
    }

    /**
     * 查询表article所有信息
     */
    @Override
    public List<Article> findAllArticle() {
        return articleMapper.findAllArticle();
    }

    /**
     * 根据主键articleId查询表article信息
     *
     * @param articleId
     */
    @Override
    public Article findArticleByarticleId(@Param("articleId") Long articleId) {
        return articleMapper.findArticleByarticleId(articleId);
    }

    /**
     * 根据条件查询表article信息
     *
     * @param article
     */
    @Override
    public List<Article> findArticleByCondition(Article article) {
        return articleMapper.findArticleByCondition(article);
    }

    /**
     * 根据主键articleId查询表article信息
     *
     * @param articleId
     */
    @Override
    public Integer deleteArticleByarticleId(@Param("articleId") Long articleId) {
        return articleMapper.deleteArticleByarticleId(articleId);
    }

    /**
     * 根据主键articleId更新表article信息
     *
     * @param article
     */
    @Override
    public Integer updateArticleByarticleId(Article article) {
        return articleMapper.updateArticleByarticleId(article);
    }

    /**
     * 新增表article信息
     *
     * @param article
     */
    @Override
    public Integer addArticle(Article article) {
        return articleMapper.addArticle(article);
    }

}