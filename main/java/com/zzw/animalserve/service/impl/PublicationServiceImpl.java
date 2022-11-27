package com.zzw.animalserve.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zzw.animalserve.entity.Publication;
import com.zzw.animalserve.entity.response.PublicationVO;
import com.zzw.animalserve.mapper.PublicationMapper;
import com.zzw.animalserve.service.PublicationService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

@Service
public class PublicationServiceImpl extends ServiceImpl<PublicationMapper, Publication> implements PublicationService {

    @Autowired(required = false)
    private PublicationMapper publicationMapper;




    /**
     * 分页查询
     */
    @Override
    public PageInfo<PublicationVO> findPageList(int pageNum) {
        PageHelper.startPage(pageNum, 9);
        List<Publication> allPublication = publicationMapper.findAllPublication();
        PageInfo pageResult = new PageInfo(allPublication);
        List<PublicationVO> publicationVOList = new ArrayList<>();
        for (Publication temp: allPublication){
            PublicationVO publicationVO = PublicationVO.entityToVO(temp);
            publicationVOList.add(publicationVO);
        }
        pageResult.setList(publicationVOList);
        return pageResult;
    }

    @Override
    public PageInfo<PublicationVO> findPageSearch(int pageNum, String name) {
        PageHelper.startPage(pageNum, 9);
        List<Publication> allBook = publicationMapper.findPublicationBySearched(name);
        PageInfo pageResult = new PageInfo(allBook);
        List<PublicationVO> bookVOList = new ArrayList<>();
        for (Publication temp: allBook){
            if(temp.getDelectTag() == 0){
                PublicationVO bookVO = PublicationVO.entityToVO(temp);
                bookVOList.add(bookVO);
            }
        }
        pageResult.setList(bookVOList);
        return pageResult;
    }

    /**
     * 查询表publication所有信息
     */
    @Override
    public List<Publication> findAllPublication() {
        return publicationMapper.findAllPublication();
    }

    /**
     * 根据主键publicationId查询表publication信息
     *
     * @param publicationId
     */
    @Override
    public Publication findPublicationBypublicationId(@Param("publicationId") Long publicationId) {
        return publicationMapper.findPublicationBypublicationId(publicationId);
    }

    /**
     * 根据条件查询表publication信息
     *
     * @param publication
     */
    @Override
    public List<Publication> findPublicationByCondition(Publication publication) {
        return publicationMapper.findPublicationByCondition(publication);
    }

    /**
     * 根据主键publicationId查询表publication信息
     *
     * @param publicationId
     */
    @Override
    public Integer deletePublicationBypublicationId(@Param("publicationId") Long publicationId) {
        return publicationMapper.deletePublicationBypublicationId(publicationId);
    }

    /**
     * 根据主键publicationId更新表publication信息
     *
     * @param publication
     */
    @Override
    public Integer updatePublicationBypublicationId(Publication publication) {
        return publicationMapper.updatePublicationBypublicationId(publication);
    }

    /**
     * 新增表publication信息
     *
     * @param publication
     */
    @Override
    public Integer addPublication(Publication publication) {
        return publicationMapper.addPublication(publication);
    }

}