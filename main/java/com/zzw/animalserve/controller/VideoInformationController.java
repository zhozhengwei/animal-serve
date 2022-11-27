package com.zzw.animalserve.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import com.zzw.animalserve.common.BaseController;
import com.zzw.animalserve.common.BaseResponse;
import com.zzw.animalserve.common.ErrorCode;
import com.zzw.animalserve.common.ResultUtils;
import com.zzw.animalserve.entity.Book;
import com.zzw.animalserve.entity.ImageInformation;
import com.zzw.animalserve.entity.VideoInformation;
import com.zzw.animalserve.entity.dto.ArticleDto;
import com.zzw.animalserve.entity.dto.VideoDto;
import com.zzw.animalserve.entity.response.ArticleVO;
import com.zzw.animalserve.entity.response.BookVO;
import com.zzw.animalserve.entity.response.VideoVO;
import com.zzw.animalserve.exception.BusinessException;
import com.zzw.animalserve.utils.RedisCache;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.zzw.animalserve.service.VideoInformationService;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Api(tags = "视频库")
@RestController
@RequestMapping("/videoInformation")
public class VideoInformationController extends BaseController{

    @Autowired
    private VideoInformationService videoInformationService;



    /**
     * 分页查询
     * @param videoDto
     * @return
     */
    @ApiOperation("视频列表")
    @PostMapping("/listQuery")
//    @PreAuthorize("hasAuthority('sys:article:lists')")
    public BaseResponse<PageInfo<VideoVO>> queryByPage(@RequestBody VideoDto videoDto, Integer pageNum) {

        if(videoDto.getType() == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"分页数据请携带参数");
        }
        //第几页为null时,显示第一页
        if (pageNum == null) {
            pageNum = 1;
        }
        PageInfo<VideoVO> page = videoInformationService.findPage(pageNum, videoDto);

        return ResultUtils.success(page);
    }

    @ApiOperation("视频TypeTwo列表")
    @PostMapping("/listTypeTwo")
//    @PreAuthorize("hasAuthority('sys:article:lists')")
    public BaseResponse<PageInfo<VideoVO>> queryByTypeTwo(@RequestBody VideoDto videoDto, Integer pageNum) {
        System.out.println("=========搜索name=======>"+videoDto.toString());
        if(videoDto == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"分页数据请携带参数");
        }
        //第几页为null时,显示第一页
        if (pageNum == null) {
            pageNum = 1;
        }
        PageInfo<VideoVO> page = videoInformationService.findTypeTwo(pageNum, videoDto.getContent());

        return ResultUtils.success(page);
    }

    @ApiOperation("视频TypeOne列表")
    @PostMapping("/listTypeOne")
//    @PreAuthorize("hasAuthority('sys:article:lists')")
    public BaseResponse<PageInfo<VideoVO>> queryByTypeOne(@RequestBody VideoDto videoDto, Integer pageNum) {
        System.out.println("=========搜索name=======>"+videoDto.toString());
        if(videoDto == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"分页数据请携带参数");
        }
        //第几页为null时,显示第一页
        if (pageNum == null) {
            pageNum = 1;
        }
        PageInfo<VideoVO> page = videoInformationService.findTypeOne(pageNum, videoDto.getContent());

        return ResultUtils.success(page);
    }

    @ApiOperation("单个视频")
    @GetMapping("searchId/{id}")
//    @PreAuthorize("hasAuthority('sys:book:sigln')")
    public BaseResponse<VideoVO> queryById(@PathVariable("id") Long id) {
        VideoInformation videoInformationByvideoInformationId = this.videoInformationService.findVideoInformationByvideoInformationId(id);
        VideoVO videoVO = VideoVO.entityToVO(videoInformationByvideoInformationId);
        return ResultUtils.success(videoVO);
    }

    /**
     * 分页查询
     *
     * @param name
     * @return
     */
    @ApiOperation("全部视频列表")
    @GetMapping("/list")
//    @PreAuthorize("hasAuthority('sys:image:lists')")
    public BaseResponse<List<VideoVO>> queryByPage(String name) {
        if(StringUtils.isAnyBlank(name)){
            List<VideoInformation> allVideoInformation = videoInformationService.findAllVideoInformation();
            List<VideoVO> videoVOList  = new ArrayList<>();
            for (VideoInformation temp: allVideoInformation) {
                if(temp.getDelectTag() == 0){
                    VideoVO videoVO = VideoVO.entityToVO(temp);
                    videoVOList.add(videoVO);
                }
            }
            return ResultUtils.success(videoVOList);
        }else{
            VideoInformation videoInformation = new VideoInformation();
            videoInformation.setVideoInformationContent(name);
            List<VideoInformation> videoInformationByCondition = videoInformationService.findVideoInformationByCondition(videoInformation);
            List<VideoVO> videoVOList  = new ArrayList<>();
            for (VideoInformation temp: videoInformationByCondition) {
                if(temp.getDelectTag() == 0){
                    VideoVO videoVO = VideoVO.entityToVO(temp);
                    videoVOList.add(videoVO);
                }
            }
            return ResultUtils.success(videoVOList);
        }
    }

    @ApiOperation("条件查询")
    @PostMapping("/listSearch")
//    @PreAuthorize("hasAuthority('sys:image:lists')")
    public BaseResponse<List<VideoVO>> queryBySearch(@RequestBody VideoDto video) {
        if(redisCache.getCacheList("video").size() == 0){
            VideoInformation videoInformation = video.toEntity();
            List<VideoInformation> videoInformationByCondition = videoInformationService.findVideoInformationByCondition(videoInformation);
            List<VideoVO> videoVOList  = new ArrayList<>();
            for (VideoInformation temp: videoInformationByCondition) {
                if(temp.getDelectTag() == 0) {
                    VideoVO videoVO = VideoVO.entityToVO(temp);
                    videoVOList.add(videoVO);
                }
            }
            redisCache.setCacheList("video",videoVOList);
        }
        List<VideoVO> temp = redisCache.getCacheList("video");
        return ResultUtils.success(temp);
    }

    /**
     * 新增数据(第三方视频数据)
     *
     * @param video 实体
     * @return 新增结果
     */
    @ApiOperation("新增视频信息")
    @PostMapping("/save")
//    @PreAuthorize("hasAuthority('sys:videoInformation:save')")
    public BaseResponse<Object> add(@RequestBody VideoDto video) {
        VideoInformation videoInformation = new VideoInformation();
        videoInformation.setVideoInformationUrl(video.getUrl());
        videoInformation.setVideoInformationContent(video.getContent());
        videoInformation.setVideoInformationType(video.getType());
        videoInformation.setCreateTime(new Date());
        videoInformation.setUpdateTime(new Date());
        videoInformation.setDelectTag(0);
        Integer integer = videoInformationService.addVideoInformation(videoInformation);
        redisCache.deleteObject("video");
        return ResultUtils.success(integer);
    }

    @ApiOperation("编辑视频信息")
    @PutMapping("/update")
//    @PreAuthorize("hasAuthority('sys:videoInformation:update')")
    public BaseResponse<Integer> edit(@RequestBody VideoDto video) {
        VideoInformation videoInformation = video.toEntity();
        videoInformation.setUpdateTime(new Date());
        redisCache.deleteObject("video");
        return ResultUtils.success(this.videoInformationService.updateVideoInformationByvideoInformationId(videoInformation));
    }


    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @ApiOperation("删除图片")
    @DeleteMapping("/delete")
//    @PreAuthorize("hasAuthority('sys:videoInformation:delete')")
    public BaseResponse<Integer> deleteById(Long id) {
        VideoInformation videoInformation = videoInformationService.findVideoInformationByvideoInformationId(id);
        videoInformation.setDelectTag(1);
        redisCache.deleteObject("video");
        return ResultUtils.success(this.videoInformationService.updateVideoInformationByvideoInformationId(videoInformation));
    }

}