package com.zzw.animalserve.controller;

import com.zzw.animalserve.common.BaseResponse;
import com.zzw.animalserve.common.ResultUtils;
import com.zzw.animalserve.entity.Article;
import com.zzw.animalserve.entity.ArticleComment;
import com.zzw.animalserve.entity.dto.ArticleCommentDto;
import com.zzw.animalserve.entity.response.ArticleCommentVO;
import com.zzw.animalserve.service.ArticleService;
import com.zzw.animalserve.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.zzw.animalserve.service.ArticleCommentService;

import java.util.ArrayList;
import java.util.List;

@Api(tags = "文章评价")
@RestController
@RequestMapping("/articleComment")
public class ArticleCommentController {

    @Autowired
    private ArticleCommentService articleCommentService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private ArticleService articleService;


    @ApiOperation("列表")
    @GetMapping("/list/{id}")
//    @PreAuthorize("hasAuthority('sys:activity:lists')")
    public BaseResponse<List<ArticleCommentVO>> queryByPage(@PathVariable("id") Long id) {
        //第几页为null时,显示第一页
        List<ArticleCommentVO> commentByArticleId = articleCommentService.findCommentByArticleId(id);
        return ResultUtils.success(commentByArticleId);
    }

    @ApiOperation("新增评价")
    @PostMapping("/save")
//    @PreAuthorize("hasAuthority('sys:activity:save')")
    public BaseResponse<Integer> add(@RequestBody ArticleCommentDto articleCommentDto) {
        Integer integer = articleCommentService.insComment(articleCommentDto);
        Article articleByarticleId = articleService.findArticleByarticleId(articleCommentDto.getAid());
        long count = articleByarticleId.getArticleCommentsCount() + 1;
        articleByarticleId.setArticleCommentsCount(count);
        articleService.updateArticleByarticleId(articleByarticleId);
        return ResultUtils.success(integer);
    }

    @ApiOperation("所有评论")
    @PostMapping("/listAll")
    public BaseResponse<List<ArticleCommentVO>> allList(){
        List<ArticleComment> allArticleComment = articleCommentService.findAllArticleComment();
        List<ArticleCommentVO> articleCommentVOList = new ArrayList<>();
        for (ArticleComment temp: allArticleComment) {
            if(temp.getComment().getDelectTag() == 0){
                ArticleCommentVO articleCommentVO = ArticleCommentVO.entityToVO(temp);
                articleCommentVOList.add(articleCommentVO);
            }
        }
        return ResultUtils.success(articleCommentVOList);
    }

    @ApiOperation("搜索单个信息")
    @GetMapping("/searchId/{id}")
//    @PreAuthorize("hasAuthority('sys:activity:lists')")
    public BaseResponse<ArticleCommentVO> searchByPage(@PathVariable("id") Long id) {
        //第几页为null时,显示第一页
        ArticleComment articleCommentByarticleId = articleCommentService.findArticleCommentByarticleId(id);
        ArticleCommentVO articleCommentVO = ArticleCommentVO.entityToVO(articleCommentByarticleId);
        return ResultUtils.success(articleCommentVO);
    }

    @ApiOperation("所有评论")
    @PostMapping("/update")
    public BaseResponse<Integer> update(@RequestBody ArticleCommentDto articleCommentDto){
        ArticleComment articleComment = articleCommentDto.toEntity();
        Integer integer = articleCommentService.updateArticleCommentByarticleId(articleComment);
        Integer comment = commentService.updateCommentBycommentId(articleComment.getComment());
        return ResultUtils.success(integer+comment);
    }

    @ApiOperation("删除评论")
    @PostMapping("/delete")
    public BaseResponse<Integer> deleteById(Long id){
        ArticleComment articleCommentByarticleId = articleCommentService.findArticleCommentByarticleId(id);
        Integer integer = articleCommentService.deleteArticleCommentByarticleId(articleCommentByarticleId.getArticleId());
        Integer comment = commentService.deleteCommentBycommentId(articleCommentByarticleId.getComment().getCommentId());
        return ResultUtils.success(integer+comment);
    }
}