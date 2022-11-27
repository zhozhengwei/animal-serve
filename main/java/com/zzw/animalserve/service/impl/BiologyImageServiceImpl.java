package com.zzw.animalserve.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzw.animalserve.entity.BiologyImage;
import com.zzw.animalserve.mapper.BiologyImageMapper;
import com.zzw.animalserve.service.BiologyImageService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Service
public class BiologyImageServiceImpl extends ServiceImpl<BiologyImageMapper, BiologyImage> implements BiologyImageService {

    @Autowired
    private BiologyImageMapper biologyImageMapper;

    /**
     * 查询表biology_image所有信息
     */
    @Override
    public List<BiologyImage> findAllBiologyImage() {
        return biologyImageMapper.findAllBiologyImage();
    }

    /**
     * 根据主键biologyId查询表biology_image信息
     *
     * @param biologyId
     */
    @Override
    public BiologyImage findBiologyImageBybiologyId(@Param("biologyId") Long biologyId) {
        return biologyImageMapper.findBiologyImageBybiologyId(biologyId);
    }

    /**
     * 根据条件查询表biology_image信息
     *
     * @param biologyImage
     */
    @Override
    public List<BiologyImage> findBiologyImageByCondition(BiologyImage biologyImage) {
        return biologyImageMapper.findBiologyImageByCondition(biologyImage);
    }

    /**
     * 根据主键biologyId查询表biology_image信息
     *
     * @param biologyId
     */
    @Override
    public Integer deleteBiologyImageBybiologyId(@Param("biologyId") Long biologyId) {
        return biologyImageMapper.deleteBiologyImageBybiologyId(biologyId);
    }

    /**
     * 根据主键biologyId更新表biology_image信息
     *
     * @param biologyImage
     */
    @Override
    public Integer updateBiologyImageBybiologyId(BiologyImage biologyImage) {
        return biologyImageMapper.updateBiologyImageBybiologyId(biologyImage);
    }

    /**
     * 新增表biology_image信息
     *
     * @param biologyImage
     */
    @Override
    public Integer addBiologyImage(BiologyImage biologyImage) {
        return biologyImageMapper.addBiologyImage(biologyImage);
    }

}