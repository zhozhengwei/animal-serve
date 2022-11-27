package com.zzw.animalserve.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import com.zzw.animalserve.common.BaseController;
import com.zzw.animalserve.common.BaseResponse;
import com.zzw.animalserve.common.ResultUtils;
import com.zzw.animalserve.entity.Publication;
import com.zzw.animalserve.entity.dto.PublicationDto;
import com.zzw.animalserve.entity.response.BookVO;
import com.zzw.animalserve.entity.response.PublicationVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.zzw.animalserve.service.PublicationService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Api(tags = "出版物信息")
@RestController
@RequestMapping("/publication")
public class PublicationController extends BaseController {

    @Autowired
    private PublicationService publicationService;



    /**
     * 分页查询
     * @param pageNum
     * @return
     */
    @ApiOperation("分页列表")
    @GetMapping("/listAll/{pageNum}")
//    @PreAuthorize("hasAuthority('sys:link:lists')")
    public BaseResponse<PageInfo<PublicationVO>> queryByPagefindAll(@PathVariable("pageNum") Integer pageNum) {
        //第几页为null时,显示第一页
        if (pageNum == null) {
            pageNum = 1;
        }
        PageInfo<PublicationVO> pageList = publicationService.findPageList(pageNum);
        return ResultUtils.success(pageList);
    }


    @ApiOperation("分页列表")
    @PostMapping("/listSearch")
//    @PreAuthorize("hasAuthority('sys:link:lists')")
    public BaseResponse<PageInfo<PublicationVO>> queryByPageSearch(Integer pageNum, @RequestBody PublicationDto publicationDto) {
        //第几页为null时,显示第一页
        if (pageNum == null) {
            pageNum = 1;
        }
        PageInfo<PublicationVO> pageList = publicationService.findPageSearch(pageNum,publicationDto.getName());
        return ResultUtils.success(pageList);
    }


    /**
     * 分页查询
     * @param name
     * @return
     */
    @ApiOperation("出版物列表")
    @GetMapping("/list")
//    @PreAuthorize("hasAuthority('sys:public:lists')")
    public BaseResponse<List<PublicationVO>> queryByPage(String name) {

        if(StringUtils.isAnyBlank(name)){
            List<Publication> allPublication = publicationService.findAllPublication();
            List<PublicationVO> publicationVOList = new ArrayList<>();
            for (Publication temp : allPublication) {
                if(temp.getDelectTag() == 0){
                    PublicationVO publicationVO = PublicationVO.entityToVO(temp);
                    publicationVOList.add(publicationVO);
                }
            }
            return ResultUtils.success(publicationVOList);
        }else{
            Publication publication = new Publication();
            publication.setPublicationName(name);
            List<Publication> publicationByCondition = publicationService.findPublicationByCondition(publication);
            List<PublicationVO> publicationVOList = new ArrayList<>();
            for (Publication temp : publicationByCondition) {
                if(temp.getDelectTag() == 0){
                    PublicationVO publicationVO = PublicationVO.entityToVO(temp);
                    publicationVOList.add(publicationVO);
                }
            }
            return ResultUtils.success(publicationVOList);
        }
    }

    @ApiOperation("条件查询")
    @PostMapping("/listPublication")
//    @PreAuthorize("hasAuthority('sys:public:lists')")
    public BaseResponse<List<PublicationVO>> queryByPublication(@RequestBody PublicationDto publicationDto) {
        Publication publication = publicationDto.toEntity();
        System.out.println("++++++===publication=======>"+publication.toString());
        List<Publication> publicationByCondition = publicationService.findPublicationByCondition(publication);
        List<PublicationVO> publicationVOList = new ArrayList<>();
        for (Publication temp : publicationByCondition) {
            if(temp.getDelectTag() == 0){
                PublicationVO publicationVO = PublicationVO.entityToVO(temp);
                publicationVOList.add(publicationVO);
            }
        }
        return ResultUtils.success(publicationVOList);
    }

    // Page<RoleVO> roles = roleService.page(getPage(), new QueryWrapper<Role>().like(StrUtil.isNotBlank(name), "name", name));

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiOperation("单条出版物数据")
    @GetMapping("/searchId/{id}")
//    @PreAuthorize("hasAuthority('sys:public:sigln')")
    public BaseResponse<PublicationVO> queryById(@PathVariable("id") Long id) {
        Publication publicationBypublicationId = this.publicationService.findPublicationBypublicationId(id);
        PublicationVO publicationVO = PublicationVO.entityToVO(publicationBypublicationId);
        return ResultUtils.success(publicationVO);
    }

    /**
     * 新增数据
     *
     * @param publicationDto 实体
     * @return 新增结果
     */
    @ApiOperation("新增出版物信息")
    @PostMapping("/save")
//    @PreAuthorize("hasAuthority('sys:public:save')")
    public BaseResponse<Integer> add(@RequestBody PublicationDto publicationDto) {
        Publication publication = publicationDto.toEntity();
        publication.setCreateTime(new Date());
        publication.setUpdateTime(new Date());
        publication.setDelectTag(0);
        return ResultUtils.success(this.publicationService.addPublication(publication));
    }

    /**
     * 编辑数据
     *
     * @param publicationDto 实体
     * @return 编辑结果
     */
    @ApiOperation("编辑出版物信息")
    @PutMapping("/update")
//    @PreAuthorize("hasAuthority('sys:public:update')")
    public BaseResponse<Integer> edit(@RequestBody PublicationDto publicationDto) {
        Publication publication = publicationDto.toEntity();
        System.out.println("=====publication====>"+publication.toString());
        publication.setUpdateTime(new Date());
        return ResultUtils.success(this.publicationService.updatePublicationBypublicationId(publication));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @ApiOperation("删除出版物信息")
    @DeleteMapping("/delete")
//    @PreAuthorize("hasAuthority('sys:public:delete')")
    public BaseResponse<Integer> deleteById(Long id) {
        Publication applyMember = publicationService.findPublicationBypublicationId(id);
        applyMember.setDelectTag(1);
        return ResultUtils.success(this.publicationService.updatePublicationBypublicationId(applyMember));
    }
}