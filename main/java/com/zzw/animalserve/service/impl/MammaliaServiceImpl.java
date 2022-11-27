package com.zzw.animalserve.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzw.animalserve.entity.Mammalia;
import com.zzw.animalserve.mapper.MammaliaMapper;
import com.zzw.animalserve.service.MammaliaService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Service
public class MammaliaServiceImpl extends ServiceImpl<MammaliaMapper, Mammalia> implements MammaliaService {

    @Autowired
    private MammaliaMapper mammaliaMapper;

    /**
     * 查询表mammalia所有信息
     */
    @Override
    public List<Mammalia> findAllMammalia() {
        return mammaliaMapper.findAllMammalia();
    }

    /**
     * 根据主键mammaliaId查询表mammalia信息
     *
     * @param mammaliaId
     */
    @Override
    public Mammalia findMammaliaBymammaliaId(@Param("mammaliaId") Long mammaliaId) {
        return mammaliaMapper.findMammaliaBymammaliaId(mammaliaId);
    }

    @Override
    public Mammalia findMammaliaBymammaliaIdList(@Param("mammaliaId") Long mammaliaId) {
        return mammaliaMapper.findMammaliaBymammaliaIdList(mammaliaId);
    }

    /**
     * 根据条件查询表mammalia信息
     *
     * @param mammalia
     */
    @Override
    public List<Mammalia> findMammaliaByCondition(Mammalia mammalia) {
        return mammaliaMapper.findMammaliaByCondition(mammalia);
    }

    /**
     * 根据主键mammaliaId查询表mammalia信息
     *
     * @param mammaliaId
     */
    @Override
    public Integer deleteMammaliaBymammaliaId(@Param("mammaliaId") Long mammaliaId) {
        return mammaliaMapper.deleteMammaliaBymammaliaId(mammaliaId);
    }

    /**
     * 根据主键mammaliaId更新表mammalia信息
     *
     * @param mammalia
     */
    @Override
    public Integer updateMammaliaBymammaliaId(Mammalia mammalia) {
        return mammaliaMapper.updateMammaliaBymammaliaId(mammalia);
    }

    /**
     * 新增表mammalia信息
     *
     * @param mammalia
     */
    @Override
    public Integer addMammalia(Mammalia mammalia) {
        return mammaliaMapper.addMammalia(mammalia);
    }

}