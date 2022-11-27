package com.zzw.animalserve.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zzw.animalserve.entity.Biology;
import com.zzw.animalserve.entity.dto.BiologyDto;
import com.zzw.animalserve.entity.response.BiologyVO;
import com.zzw.animalserve.mapper.BiologyMapper;
import com.zzw.animalserve.service.BiologyService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

@Service
public class BiologyServiceImpl extends ServiceImpl<BiologyMapper, Biology> implements BiologyService {

    @Autowired
    private BiologyMapper biologyMapper;

    //条件查询，返回相应数据

    @Override
    public PageInfo<BiologyVO> findPage(int pageNum, BiologyDto biologyDto) {
        Biology biology = biologyDto.toEntity();
        PageHelper.startPage(pageNum, 9);
        List<Biology> biologyByCondition = biologyMapper.findBiologyByCondition(biology);
        PageInfo pageResult = new PageInfo(biologyByCondition);
        List<BiologyVO> biologyVOList = new ArrayList<>();
        for (Biology temp: biologyByCondition) {
            if(temp.getDelectTag() == 0){
                BiologyVO biologyVO = BiologyVO.entityToVO(temp);
                biologyVOList.add(biologyVO);
            }
        }
        pageResult.setList(biologyVOList);
        return pageResult;
    }
    //无需条件查询，返回全部数据的方法
    @Override
    public PageInfo<BiologyVO> findPageList(int pageNum) {
        PageHelper.startPage(pageNum, 9);
        List<Biology> allBiology = biologyMapper.findAllBiology();
        PageInfo pageResult = new PageInfo(allBiology);
        List<BiologyVO> biologyVOList = new ArrayList<>();
        for (Biology temp:allBiology) {
            if(temp.getDelectTag() == 0){
                BiologyVO biologyVO = BiologyVO.entityToVO(temp);
                biologyVOList.add(biologyVO);
            }
        }
        pageResult.setList(biologyVOList);
        return pageResult;
    }
    /**
     * 查询表biology所有信息
     */
    @Override
    public List<Biology> findAllBiology() {
        return biologyMapper.findAllBiology();
    }

    /**
     * 根据主键biologyId查询表biology信息
     *
     * @param biologyId
     */
    @Override
    public Biology findBiologyBybiologyId(@Param("biologyId") Long biologyId) {
        return biologyMapper.findBiologyBybiologyId(biologyId);
    }

    @Override
    public Biology findBiologyBybiologyIdList(@Param("biologyId") Long biologyId) {
        return biologyMapper.findBiologyBybiologyIdList(biologyId);
    }

    /**
     * 根据条件查询表biology信息
     *
     * @param biology
     */
    @Override
    public List<Biology> findBiologyByCondition(Biology biology) {
        return biologyMapper.findBiologyByCondition(biology);
    }

    /**
     * 根据主键biologyId查询表biology信息
     *
     * @param biologyId
     */
    @Override
    public Integer deleteBiologyBybiologyId(@Param("biologyId") Long biologyId) {
        return biologyMapper.deleteBiologyBybiologyId(biologyId);
    }

    /**
     * 根据主键biologyId更新表biology信息
     *
     * @param biology
     */
    @Override
    public Integer updateBiologyBybiologyId(Biology biology) {
        return biologyMapper.updateBiologyBybiologyId(biology);
    }

    /**
     * 新增表biology信息
     *
     * @param biology
     */
    @Override
    public Integer addBiology(Biology biology) {
        return biologyMapper.addBiology(biology);
    }

}