package com.zzw.animalserve.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzw.animalserve.entity.Biology;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository
@Mapper
public interface BiologyMapper extends BaseMapper<Biology> {

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

    List<Biology> findBiologyBybosId(@Param("bosId") Long bosId);

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