package com.zzw.animalserve.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzw.animalserve.entity.ArticleComment;
import com.zzw.animalserve.entity.dto.ArticleCommentDto;
import com.zzw.animalserve.entity.response.ArticleCommentVO;
import com.zzw.animalserve.mapper.ArticleCommentMapper;
import com.zzw.animalserve.mapper.CommentMapper;
import com.zzw.animalserve.service.ArticleCommentService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ArticleCommentServiceImpl  extends ServiceImpl<ArticleCommentMapper, ArticleComment> implements ArticleCommentService {

    @Autowired
    private ArticleCommentMapper articleCommentMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public Integer insComment(ArticleCommentDto articleCommentDto){
        ArticleComment articleComment = articleCommentDto.toEntity();
        articleComment.getComment().setCreateTime(new Date());
        articleComment.getComment().setUpdateTime(new Date());
        articleComment.getComment().setCreateId(articleCommentDto.getUid());
        articleComment.getComment().setUpdateId(articleCommentDto.getUid());
        articleComment.getComment().setDelectTag(0);
        Integer cid = commentMapper.addComment(articleComment.getComment());
        articleComment.setCommentId(Long.valueOf(articleComment.getComment().getCommentId()));
        Integer integer = articleCommentMapper.addArticleComment(articleComment);
        return integer;
    }

    @Override
    public List<ArticleCommentVO> findCommentByArticleId(Long articleId){
//        ArticleComment articleComment = new ArticleComment();
//        articleComment.setArticleId(articleId);
        List<ArticleComment> articleCommentByCondition = articleCommentMapper.findArticleCommentListByarticleId(articleId);
        List<ArticleCommentVO> articleCommentVOList = new ArrayList<>();
        for (ArticleComment temp : articleCommentByCondition) {
            ArticleCommentVO articleCommentVO = ArticleCommentVO.entityToVO(temp);
            articleCommentVOList.add(articleCommentVO);
        }
        return articleCommentVOList;
    }

    /**
     * 查询表article_comment所有信息
     */
    @Override
    public List<ArticleComment> findAllArticleComment() {
        return articleCommentMapper.findAllArticleComment();
    }

    /**
     * 根据主键articleId查询表article_comment信息
     *
     * @param articleId
     */
    @Override
    public ArticleComment findArticleCommentByarticleId(@Param("articleId") Long articleId) {
        return articleCommentMapper.findArticleCommentByarticleId(articleId);
    }

    /**
     * 根据条件查询表article_comment信息
     *
     * @param articleComment
     */
    @Override
    public List<ArticleComment> findArticleCommentByCondition(ArticleComment articleComment) {
        return articleCommentMapper.findArticleCommentByCondition(articleComment);
    }

    /**
     * 根据主键articleId查询表article_comment信息
     *
     * @param articleId
     */
    @Override
    public Integer deleteArticleCommentByarticleId(@Param("articleId") Long articleId) {
        return articleCommentMapper.deleteArticleCommentByarticleId(articleId);
    }

    /**
     * 根据主键articleId更新表article_comment信息
     *
     * @param articleComment
     */
    @Override
    public Integer updateArticleCommentByarticleId(ArticleComment articleComment) {
        return articleCommentMapper.updateArticleCommentByarticleId(articleComment);
    }

    /**
     * 新增表article_comment信息
     *
     * @param articleComment
     */
    @Override
    public Integer addArticleComment(ArticleComment articleComment) {
        return articleCommentMapper.addArticleComment(articleComment);
    }

}