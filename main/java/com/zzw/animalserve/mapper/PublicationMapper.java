package com.zzw.animalserve.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzw.animalserve.entity.Publication;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface PublicationMapper extends BaseMapper<Publication> {

    /**
     * 查询表publication所有信息
     */
    List<Publication> findAllPublication();

    List<Publication> findPublicationBySearched(String name);

    /**
     * 根据主键publicationId查询表publication信息
     *
     * @param publicationId
     */
    Publication findPublicationBypublicationId(@Param("publicationId") Long publicationId);

    /**
     * 根据条件查询表publication信息
     *
     * @param publication
     */
    List<Publication> findPublicationByCondition(Publication publication);

    /**
     * 根据主键publicationId查询表publication信息
     *
     * @param publicationId
     */
    Integer deletePublicationBypublicationId(@Param("publicationId") Long publicationId);

    /**
     * 根据主键publicationId更新表publication信息
     *
     * @param publication
     */
    Integer updatePublicationBypublicationId(Publication publication);

    /**
     * 新增表publication信息
     *
     * @param publication
     */
    Integer addPublication(Publication publication);

}