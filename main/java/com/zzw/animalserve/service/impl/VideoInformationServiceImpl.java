package com.zzw.animalserve.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zzw.animalserve.entity.VideoInformation;
import com.zzw.animalserve.entity.dto.VideoDto;
import com.zzw.animalserve.entity.response.VideoVO;
import com.zzw.animalserve.mapper.VideoInformationMapper;
import com.zzw.animalserve.service.VideoInformationService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

@Service
public class VideoInformationServiceImpl extends ServiceImpl<VideoInformationMapper, VideoInformation> implements VideoInformationService {

    @Autowired
    private VideoInformationMapper videoInformationMapper;


    @Override
    public PageInfo<VideoVO> findPage(int pageNum, VideoDto videoDto) {
        VideoInformation videoInformation = videoDto.toEntity();
        //设置分页的当前页和每页条数
        PageHelper.startPage(pageNum, 9);
        List<VideoInformation> videoInformationByCondition = videoInformationMapper.findVideoInformationByCondition(videoInformation);
        PageInfo pageResult = new PageInfo(videoInformationByCondition);
        List<VideoVO> videoVOList = new ArrayList<>();
        for (VideoInformation temp:videoInformationByCondition) {
            if(temp.getDelectTag() == 0){
                VideoVO videoVO = VideoVO.entityToVO(temp);
                videoVOList.add(videoVO);
            }
        }
        pageResult.setList(videoVOList);
        //实例化PageInfo
        return pageResult;
    }

    @Override
    public PageInfo<VideoVO> findTypeTwo(int pageNum, String content) {
        PageHelper.startPage(pageNum, 9);
        List<VideoInformation> likeContentTypeTwo = videoInformationMapper.findLikeContentTypeTwo(content);
        PageInfo pageResult = new PageInfo(likeContentTypeTwo);
        List<VideoVO> videoVOList = new ArrayList<>();
        for (VideoInformation temp:likeContentTypeTwo) {
            if(temp.getDelectTag() == 0){
                VideoVO videoVO = VideoVO.entityToVO(temp);
                videoVOList.add(videoVO);
            }
        }
        pageResult.setList(videoVOList);
        //实例化PageInfo
        return pageResult;
    }

    @Override
    public PageInfo<VideoVO> findTypeOne(int pageNum, String content) {
        PageHelper.startPage(pageNum, 9);
        List<VideoInformation> likeContentTypeTwo = videoInformationMapper.findLikeContentTypeOne(content);
        PageInfo pageResult = new PageInfo(likeContentTypeTwo);
        List<VideoVO> videoVOList = new ArrayList<>();
        for (VideoInformation temp:likeContentTypeTwo) {
            if(temp.getDelectTag() == 0){
                VideoVO videoVO = VideoVO.entityToVO(temp);
                videoVOList.add(videoVO);
            }
        }
        pageResult.setList(videoVOList);
        //实例化PageInfo
        return pageResult;
    }

    /**
     * 查询表video_information所有信息
     */
    @Override
    public List<VideoInformation> findAllVideoInformation() {
        return videoInformationMapper.findAllVideoInformation();
    }

    /**
     * 根据主键videoInformationId查询表video_information信息
     *
     * @param videoInformationId
     */
    @Override
    public VideoInformation findVideoInformationByvideoInformationId(@Param("videoInformationId") Long videoInformationId) {
        return videoInformationMapper.findVideoInformationByvideoInformationId(videoInformationId);
    }

    /**
     * 根据条件查询表video_information信息
     *
     * @param videoInformation
     */
    @Override
    public List<VideoInformation> findVideoInformationByCondition(VideoInformation videoInformation) {
        return videoInformationMapper.findVideoInformationByCondition(videoInformation);
    }

    /**
     * 根据主键videoInformationId查询表video_information信息
     *
     * @param videoInformationId
     */
    @Override
    public Integer deleteVideoInformationByvideoInformationId(@Param("videoInformationId") Long videoInformationId) {
        return videoInformationMapper.deleteVideoInformationByvideoInformationId(videoInformationId);
    }

    /**
     * 根据主键videoInformationId更新表video_information信息
     *
     * @param videoInformation
     */
    @Override
    public Integer updateVideoInformationByvideoInformationId(VideoInformation videoInformation) {
        return videoInformationMapper.updateVideoInformationByvideoInformationId(videoInformation);
    }

    /**
     * 新增表video_information信息
     *
     * @param videoInformation
     */
    @Override
    public Integer addVideoInformation(VideoInformation videoInformation) {
        return videoInformationMapper.addVideoInformation(videoInformation);
    }

}