package com.zzw.animalserve.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzw.animalserve.entity.Bos;
import com.zzw.animalserve.mapper.BosMapper;
import com.zzw.animalserve.service.BosService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Service
public class BosServiceImpl extends ServiceImpl<BosMapper, Bos> implements BosService {

    @Autowired
    private BosMapper bosMapper;

    /**
     * 查询表bos所有信息
     */
    @Override
    public List<Bos> findAllBos() {
        return bosMapper.findAllBos();
    }

    /**
     * 根据主键bosId查询表bos信息
     *
     * @param bosId
     */
    @Override
    public Bos findBosBybosId(@Param("bosId") Long bosId) {
        return bosMapper.findBosBybosId(bosId);
    }

    @Override
    public Bos findBosBybosIdList(@Param("bosId") Long bosId) {
        return bosMapper.findBosBybosIdList(bosId);
    }

    /**
     * 根据条件查询表bos信息
     *
     * @param bos
     */
    @Override
    public List<Bos> findBosByCondition(Bos bos) {
        return bosMapper.findBosByCondition(bos);
    }

    /**
     * 根据主键bosId查询表bos信息
     *
     * @param bosId
     */
    @Override
    public Integer deleteBosBybosId(@Param("bosId") Long bosId) {
        return bosMapper.deleteBosBybosId(bosId);
    }

    /**
     * 根据主键bosId更新表bos信息
     *
     * @param bos
     */
    @Override
    public Integer updateBosBybosId(Bos bos) {
        return bosMapper.updateBosBybosId(bos);
    }

    /**
     * 新增表bos信息
     *
     * @param bos
     */
    @Override
    public Integer addBos(Bos bos) {
        return bosMapper.addBos(bos);
    }

}