package com.zzw.animalserve.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.zzw.animalserve.entity.Publication;
import com.zzw.animalserve.entity.response.PublicationVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PublicationService extends IService<Publication> {

    PageInfo<PublicationVO> findPageList(int pageNum);

    PageInfo<PublicationVO> findPageSearch(int pageNum, String name);

    /**
     * 查询表publication所有信息
     */
    List<Publication> findAllPublication();

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