package com.zzw.animalserve.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zzw.animalserve.entity.ImageInformation;
import com.zzw.animalserve.entity.dto.ImageInformationDto;
import com.zzw.animalserve.entity.response.ImageInformationVO;
import com.zzw.animalserve.mapper.ImageInformationMapper;
import com.zzw.animalserve.service.ImageInformationService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImageInformationServiceImpl extends ServiceImpl<ImageInformationMapper, ImageInformation> implements ImageInformationService {

    @Autowired
    private ImageInformationMapper imageInformationMapper;



    @Override
    public PageInfo<ImageInformationVO> findPage(int pageNum, ImageInformationDto imageInformationDto) {
        ImageInformation imageInformation = imageInformationDto.toEntity();
        //设置分页的当前页和每页条数
        PageHelper.startPage(pageNum, 9);
        List<ImageInformation> videoInformationByCondition = imageInformationMapper.findImageInformationByCondition(imageInformation);
        PageInfo pageResult = new PageInfo(videoInformationByCondition);
        List<ImageInformationVO> videoVOList = new ArrayList<>();
        for (ImageInformation temp:videoInformationByCondition) {
            if(temp.getDelectTag() == 0){
                ImageInformationVO videoVO = ImageInformationVO.entityToVO(temp);
                videoVOList.add(videoVO);
            }
        }
        pageResult.setList(videoVOList);
        //实例化PageInfo
        return pageResult;
    }

    @Override
    public PageInfo<ImageInformationVO> findClassType(int pageNum, String name) {
        //设置分页的当前页和每页条数
        PageHelper.startPage(pageNum, 9);
        List<ImageInformation> videoInformationByCondition = imageInformationMapper.findImageInformationBySearchList(name);
        PageInfo pageResult = new PageInfo(videoInformationByCondition);
        List<ImageInformationVO> videoVOList = new ArrayList<>();
        for (ImageInformation temp:videoInformationByCondition) {
            ImageInformationVO videoVO = ImageInformationVO.entityToVO(temp);
            videoVOList.add(videoVO);
        }
        pageResult.setList(videoVOList);
        //实例化PageInfo
        return pageResult;
    }


    /**
     * 查询表image_information所有信息
     */
    @Override
    public List<ImageInformation> findAllImageInformation() {
        return imageInformationMapper.findAllImageInformation();
    }

    /**
     * 根据主键imageInformationId查询表image_information信息
     *
     * @param imageInformationId
     */
    @Override
    public ImageInformation findImageInformationByimageInformationId(@Param("imageInformationId") Long imageInformationId) {
        return imageInformationMapper.findImageInformationByimageInformationId(imageInformationId);
    }

    /**
     * 根据条件查询表image_information信息
     *
     * @param imageInformation
     */
    @Override
    public List<ImageInformation> findImageInformationByCondition(ImageInformation imageInformation) {
        return imageInformationMapper.findImageInformationByCondition(imageInformation);
    }

    /**
     * 根据主键imageInformationId查询表image_information信息
     *
     * @param imageInformationId
     */
    @Override
    public Integer deleteImageInformationByimageInformationId(@Param("imageInformationId") Long imageInformationId) {
        return imageInformationMapper.deleteImageInformationByimageInformationId(imageInformationId);
    }

    /**
     * 根据主键imageInformationId更新表image_information信息
     *
     * @param imageInformation
     */
    @Override
    public Integer updateImageInformationByimageInformationId(ImageInformation imageInformation) {
        return imageInformationMapper.updateImageInformationByimageInformationId(imageInformation);
    }

    /**
     * 新增表image_information信息
     *
     * @param imageInformation
     */
    @Override
    public Integer addImageInformation(ImageInformation imageInformation) {
        return imageInformationMapper.addImageInformation(imageInformation);
    }

}