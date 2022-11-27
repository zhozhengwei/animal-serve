package com.zzw.animalserve.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.zzw.animalserve.entity.Biology;
import com.zzw.animalserve.entity.dto.BiologyDto;
import com.zzw.animalserve.entity.response.BiologyVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BiologyService extends IService<Biology> {

    PageInfo<BiologyVO> findPage(int pageNum, BiologyDto biologyDto);

    PageInfo<BiologyVO> findPageList(int pageNum);

    /**
     * 查询表biology所有信息
     */
    List<Biology> findAllBiology();

    /**
     * 根据主键biologyId查询表biology信息
     *
     * @param biologyId
     */
    Biology findBiologyBybiologyId(@Param("biologyId") Long biologyId);

    Biology findBiologyBybiologyIdList(@Param("biologyId") Long biologyId);

    /**
     * 根据条件查询表biology信息
     *
     * @param biology
     */
    List<Biology> findBiologyByCondition(Biology biology);

    /**
     * 根据主键biologyId查询表biology信息
     *
     * @param biologyId
     */
    Integer deleteBiologyBybiologyId(@Param("biologyId") Long biologyId);

    /**
     * 根据主键biologyId更新表biology信息
     *
     * @param biology
     */
    Integer updateBiologyBybiologyId(Biology biology);

    /**
     * 新增表biology信息
     *
     * @param biology
     */
    Integer addBiology(Biology biology);
}