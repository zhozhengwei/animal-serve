package com.zzw.animalserve.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.zzw.animalserve.entity.ImageInformation;
import com.zzw.animalserve.entity.VideoInformation;
import com.zzw.animalserve.entity.dto.VideoDto;
import com.zzw.animalserve.entity.response.VideoVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VideoInformationService extends IService<VideoInformation> {

    PageInfo<VideoVO> findPage(int pageNum, VideoDto videoDto);

    PageInfo<VideoVO> findTypeTwo(int pageNum, String content);

    PageInfo<VideoVO> findTypeOne(int pageNum, String content);

    /**
     * 查询表video_information所有信息
     */
    List<VideoInformation> findAllVideoInformation();

    /**
     * 根据主键videoInformationId查询表video_information信息
     *
     * @param videoInformationId
     */
    VideoInformation findVideoInformationByvideoInformationId(@Param("videoInformationId") Long videoInformationId);

    /**
     * 根据条件查询表video_information信息
     *
     * @param videoInformation
     */
    List<VideoInformation> findVideoInformationByCondition(VideoInformation videoInformation);

    /**
     * 根据主键videoInformationId查询表video_information信息
     *
     * @param videoInformationId
     */
    Integer deleteVideoInformationByvideoInformationId(@Param("videoInformationId") Long videoInformationId);

    /**
     * 根据主键videoInformationId更新表video_information信息
     *
     * @param videoInformation
     */
    Integer updateVideoInformationByvideoInformationId(VideoInformation videoInformation);

    /**
     * 新增表video_information信息
     *
     * @param videoInformation
     */
    Integer addVideoInformation(VideoInformation videoInformation);
}