package com.zzw.animalserve.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzw.animalserve.entity.VideoInformation;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface VideoInformationMapper extends BaseMapper<VideoInformation> {

    /**
     * 查询表video_information所有信息
     */
    List<VideoInformation> findAllVideoInformation();


    List<VideoInformation> findLikeContentTypeOne(String content);


    List<VideoInformation> findLikeContentTypeTwo(String content);
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