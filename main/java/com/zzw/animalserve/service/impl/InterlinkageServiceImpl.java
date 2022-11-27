package com.zzw.animalserve.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zzw.animalserve.entity.Interlinkage;
import com.zzw.animalserve.entity.response.InterlinkageVO;
import com.zzw.animalserve.mapper.InterlinkageMapper;
import com.zzw.animalserve.service.InterlinkageService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

@Service
public class InterlinkageServiceImpl extends ServiceImpl<InterlinkageMapper, Interlinkage> implements InterlinkageService {

    @Autowired
    private InterlinkageMapper interlinkageMapper;

    /**
     * 分页查询
     */
    @Override
    public PageInfo<InterlinkageVO> findPageList(int pageNum) {
        PageHelper.startPage(pageNum, 6);
        List<Interlinkage> allInterlinkage = interlinkageMapper.findAllInterlinkage();
        PageInfo pageResult = new PageInfo(allInterlinkage);
        List<InterlinkageVO> interlinkageVOList = new ArrayList<>();
        for (Interlinkage temp: allInterlinkage){
            if(temp.getDelectTag() == 0){
                InterlinkageVO interlinkageVO = InterlinkageVO.entityToVO(temp);
                interlinkageVOList.add(interlinkageVO);
            }
        }
        pageResult.setList(interlinkageVOList);
        return pageResult;
    }
    /**
     * 查询表interlinkage所有信息
     */
    @Override
    public List<Interlinkage> findAllInterlinkage() {
        return interlinkageMapper.findAllInterlinkage();
    }

    /**
     * 根据主键interlinkageId查询表interlinkage信息
     *
     * @param interlinkageId
     */
    @Override
    public Interlinkage findInterlinkageByinterlinkageId(@Param("interlinkageId") Long interlinkageId) {
        return interlinkageMapper.findInterlinkageByinterlinkageId(interlinkageId);
    }

    /**
     * 根据条件查询表interlinkage信息
     *
     * @param interlinkage
     */
    @Override
    public List<Interlinkage> findInterlinkageByCondition(Interlinkage interlinkage) {
        return interlinkageMapper.findInterlinkageByCondition(interlinkage);
    }

    /**
     * 根据主键interlinkageId查询表interlinkage信息
     *
     * @param interlinkageId
     */
    @Override
    public Integer deleteInterlinkageByinterlinkageId(@Param("interlinkageId") Long interlinkageId) {
        return interlinkageMapper.deleteInterlinkageByinterlinkageId(interlinkageId);
    }

    /**
     * 根据主键interlinkageId更新表interlinkage信息
     *
     * @param interlinkage
     */
    @Override
    public Integer updateInterlinkageByinterlinkageId(Interlinkage interlinkage) {
        return interlinkageMapper.updateInterlinkageByinterlinkageId(interlinkage);
    }

    /**
     * 新增表interlinkage信息
     *
     * @param interlinkage
     */
    @Override
    public Integer addInterlinkage(Interlinkage interlinkage) {
        return interlinkageMapper.addInterlinkage(interlinkage);
    }

}