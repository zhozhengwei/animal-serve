package com.zzw.animalserve.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import com.zzw.animalserve.common.BaseController;
import com.zzw.animalserve.common.BaseResponse;
import com.zzw.animalserve.common.ErrorCode;
import com.zzw.animalserve.common.ResultUtils;
import com.zzw.animalserve.entity.Article;
import com.zzw.animalserve.entity.ArticleComment;
import com.zzw.animalserve.entity.ArticleTag;
import com.zzw.animalserve.entity.Tag;
import com.zzw.animalserve.entity.dto.ArticleDto;
import com.zzw.animalserve.entity.response.ArticleVO;
import com.zzw.animalserve.exception.BusinessException;
import com.zzw.animalserve.service.*;
import com.zzw.animalserve.utils.TimeFormat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@Api(tags = "文章信息")
@RestController
@RequestMapping("/article")
@Slf4j
public class ArticleController  extends BaseController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleTagService articleTagService;

    @Autowired
    private TagService tagService;

    @Autowired
    private ArticleCommentService articleCommentService;

    @Autowired
    private CommentService commentService;


    /**
     * 分页查询
     * @param articleDto
     * @return
     */
    @ApiOperation("前台文章列表")
    @PostMapping("/list")
//    @PreAuthorize("hasAuthority('sys:article:lists')")
    public BaseResponse<PageInfo<ArticleVO>> queryByPage(@RequestBody ArticleDto articleDto, Integer pageNum) {
        if(articleDto.getType() == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"分页数据请携带参数");
        }
        //第几页为null时,显示第一页
        if (pageNum == null) {
            pageNum = 1;
        }
        PageInfo<ArticleVO> page = articleService.findPage(pageNum, articleDto);

        return ResultUtils.success(page);
    }


    /**
     * 查询所有
     * @return
     */
    @ApiOperation("全文章列表")
    @GetMapping("/listAll")
    public BaseResponse<List<ArticleVO>> queryByList() {
        List<Article> allArticle = articleService.findAllArticle();
        List<ArticleVO> allArticleList = new ArrayList<>();
        for (Article temp : allArticle) {
            if(temp.getDelectTag() == 0){
                ArticleVO articleVO = ArticleVO.entityToVO(temp);
                allArticleList.add(articleVO);
            }
        }
        return ResultUtils.success(allArticleList);
    }

    @ApiOperation("条件查询")
    @PostMapping("/listSearch")
    public BaseResponse<List<ArticleVO>> queryBySearch(@RequestBody ArticleDto articleDto) {
        Article article = articleDto.toEntity();
        List<Article> articleByCondition = articleService.findArticleByCondition(article);
        List<ArticleVO> allArticleList = new ArrayList<>();
        for (Article temp : articleByCondition) {
            if(temp.getDelectTag() == 0){
                ArticleVO articleVO = ArticleVO.entityToVO(temp);
                allArticleList.add(articleVO);
            }
        }
        return ResultUtils.success(allArticleList);
    }


    /**
     * 查询所有
     * @return
     */
    @ApiOperation("随机列表")
    @GetMapping("/randomList")
    public BaseResponse<List<ArticleVO>> randoomList() {
        Article article = new Article();
        article.setArticleAttribute(2);
        List<Article> articleByCondition = articleService.findArticleByCondition(article);
        int sum = articleByCondition.size();
        int a = 0;
        int b = 0;
        int c = 0;
        for (int i=0;i<3;i++) {
            a = (int)(1+Math.random()*sum);
            b = (int)(1+Math.random()*sum);
            c = (int)(1+Math.random()*sum);
        }
        List<Article> list = new ArrayList<>();
        List<ArticleVO> articleVOList = new ArrayList<>();
        list.add(articleByCondition.get(a-1));
        list.add(articleByCondition.get(b-1));
        list.add(articleByCondition.get(c-1));
        for (Article temp: list) {
            if(temp.getDelectTag() == 0){
                ArticleVO articleVO = ArticleVO.entityToVO(temp);
                articleVOList.add(articleVO);
            }
        }
        return ResultUtils.success(articleVOList);
    }

    /**
     * 分页查询
     * @param articleDto
     * @return
     */
    @ApiOperation("热门新闻")
    @GetMapping("/host")
//    @PreAuthorize("hasAuthority('sys:article:lists')")
    public BaseResponse<List<ArticleVO>> queryByPageList(ArticleDto articleDto) {
        Article article = articleDto.toEntity();
        List<Article> articleByCondition = articleService.findArticleByCondition(article);
        //排序时间最大的返回给到前端
        Collections.sort(articleByCondition, new Comparator<Article>() {

            @Override
            public int compare(Article o1, Article o2) {
                return o1.getCreateTime().compareTo(o2.getCreateTime());
            }
        });
        //将信息进行封装
        List<ArticleVO> articleVOList = new ArrayList<>();
        for (Article temp: articleByCondition) {
            if(temp.getDelectTag() == 0){
                ArticleVO articleVO = ArticleVO.entityToVO(temp);
                articleVOList.add(articleVO);
            }
        }


        return ResultUtils.success(articleVOList);
    }

    /**
     * 分页查询
     * @param articleDto
     * @return
     */
    @ApiOperation("历史新闻")
    @PostMapping("/history")
//    @PreAuthorize("hasAuthority('sys:article:lists')")
    public BaseResponse<PageInfo<ArticleVO>> queryByPageHistoryList(@RequestBody ArticleDto articleDto, Integer pageNum) {
        //第几页为null时,显示第一页
        if (pageNum == null) {
            pageNum = 1;
        }
        PageInfo<ArticleVO> history = articleService.history(pageNum, articleDto);

        return ResultUtils.success(history);
    }




    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiOperation("单个文章信息")
    @GetMapping("/searchId/{id}")
//    @PreAuthorize("hasAuthority('sys:article:sigln')")
    public BaseResponse<ArticleVO> queryById(@PathVariable("id") Long id) {
        Article articleByarticleId = this.articleService.findArticleByarticleId(id);
        ArticleVO articleVO = ArticleVO.entityToVO(articleByarticleId);
        return ResultUtils.success(articleVO);
    }

    @ApiOperation("统计个人文章数量")
    @GetMapping("/memberHistory/{id}")
//    @PreAuthorize("hasAuthority('sys:article:sigln')")
    public BaseResponse<Long> countById(@PathVariable("id") Long id) {
        Article articleByarticleId = new Article();
        articleByarticleId.setMemberId(id);
        long count = articleService.count(articleByarticleId);
        return ResultUtils.success(count);
    }


    @ApiOperation("改变文章观看数量")
    @GetMapping("/updateLookCount/{id}")
    public BaseResponse<Integer> updateByCount(@PathVariable("id") Long id) {
        Article articleByarticleId = this.articleService.findArticleByarticleId(id);
        long count = articleByarticleId.getArticleLookCount() + 1;
        articleByarticleId.setArticleLookCount(count);
        Integer integer = articleService.updateArticleByarticleId(articleByarticleId);
        return ResultUtils.success(integer);
    }


    /**
     * 新增数据
     *
     * @param articleDto 实体
     * @return 新增结果
     */
    @ApiOperation("新增文章")
    @PostMapping("/save")
//    @PreAuthorize("hasAuthority('sys:article:save')")
    public BaseResponse<Integer> add(@RequestBody ArticleDto articleDto) {
        Article article = articleDto.toEntity();
        article.setCreateId(articleDto.getUid());
        article.setUpdateId(articleDto.getUid());
        article.setCreateTime(new Date());
        article.setUpdateTime(new Date());
        article.setArticleLookCount(0L);
        article.setArticleCommentsCount(0L);
        article.setArticleCollectsCount(0L);
        article.setDelectTag(0);
        //返回一个ID
        Integer integer = articleService.addArticle(article);
        ArticleTag articleTag = new ArticleTag();
        articleTag.setArticleId(article.getArticleId());
        for (String temp:articleDto.getTagName()) {
            Tag tag = new Tag();
            tag.setCreateTime(new Date());
            tag.setUpdateTime(new Date());
            tag.setCreateId(articleDto.getUid());
            tag.setUpdateId(articleDto.getUid());
            tag.setDelectTag(0);
            tag.setTagTitle(temp);
            //返回一个ID
            Integer addTag = tagService.addTag(tag);
            articleTag.setTagId(tag.getTagId());
            articleTagService.addArticleTag(articleTag);
        }
        return ResultUtils.success(integer);
    }


    /**
     * 编辑数据
     *
     * @param articleDto 实体
     * @return 编辑结果
     */
    @ApiOperation("编辑文章信息")
    @PutMapping("/update")
//    @PreAuthorize("hasAuthority('sys:article:update')")
    public BaseResponse<Integer> edit(@RequestBody ArticleDto articleDto) {
        Article article = articleDto.toEntity();
        article.setUpdateTime(new Date());
        return ResultUtils.success(this.articleService.updateArticleByarticleId(article));
    }


    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @ApiOperation("删除文章信息")
    @DeleteMapping("/delete")
//    @PreAuthorize("hasAuthority('sys:article:delete')")
    public BaseResponse<Integer> deleteById(Long id) {
        Integer integer = articleTagService.deleteArticleTagByarticleId(id);
        ArticleComment articleCommentByarticleId = articleCommentService.findArticleCommentByarticleId(id);
        if(!Objects.isNull(articleCommentByarticleId)){
            Integer integer1 = articleCommentService.deleteArticleCommentByarticleId(articleCommentByarticleId.getArticleId());
            Integer comment = commentService.deleteCommentBycommentId(articleCommentByarticleId.getComment().getCommentId());
        }
        log.warn("删除的标签记录文章=======>"+integer.toString());
        Integer byarticleId = this.articleService.deleteArticleByarticleId(id);
        return ResultUtils.success(byarticleId);
    }


}