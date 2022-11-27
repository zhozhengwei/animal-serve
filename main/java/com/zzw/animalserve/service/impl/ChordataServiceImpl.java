package com.zzw.animalserve.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzw.animalserve.entity.Chordata;
import com.zzw.animalserve.mapper.ChordataMapper;
import com.zzw.animalserve.service.ChordataService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Service
public class ChordataServiceImpl extends ServiceImpl<ChordataMapper, Chordata> implements ChordataService {

    @Autowired
    private ChordataMapper chordataMapper;

    /**
     * 查询表chordata所有信息
     */
    @Override
    public List<Chordata> findAllChordata() {
        return chordataMapper.findAllChordata();
    }

    /**
     * 根据主键chordataId查询表chordata信息
     *
     * @param chordataId
     */
    @Override
    public Chordata findChordataBychordataId(@Param("chordataId") Long chordataId) {
        return chordataMapper.findChordataBychordataId(chordataId);
    }

    /**
     * 根据主键chordataId查询表chordata信息
     * @param chordataId
     */
    @Override
    public Chordata findChordataByChordataIdList(@Param("chordataId") Long chordataId) {
        return chordataMapper.findChordataByChordataIdList(chordataId);
    }

    /**
     * 根据条件查询表chordata信息
     *
     * @param chordata
     */
    @Override
    public List<Chordata> findChordataByCondition(Chordata chordata) {
        return chordataMapper.findChordataByCondition(chordata);
    }

    /**
     * 根据主键chordataId查询表chordata信息
     *
     * @param chordataId
     */
    @Override
    public Integer deleteChordataBychordataId(@Param("chordataId") Long chordataId) {
        return chordataMapper.deleteChordataBychordataId(chordataId);
    }

    /**
     * 根据主键chordataId更新表chordata信息
     *
     * @param chordata
     */
    @Override
    public Integer updateChordataBychordataId(Chordata chordata) {
        return chordataMapper.updateChordataBychordataId(chordata);
    }

    /**
     * 新增表chordata信息
     *
     * @param chordata
     */
    @Override
    public Integer addChordata(Chordata chordata) {
        return chordataMapper.addChordata(chordata);
    }

}