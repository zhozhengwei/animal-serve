package com.zzw.animalserve.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import com.zzw.animalserve.common.BaseController;
import com.zzw.animalserve.common.BaseResponse;
import com.zzw.animalserve.common.ErrorCode;
import com.zzw.animalserve.common.ResultUtils;
import com.zzw.animalserve.entity.Biology;
import com.zzw.animalserve.entity.BiologyImage;
import com.zzw.animalserve.entity.ImageInformation;
import com.zzw.animalserve.entity.dto.BiologyDto;
import com.zzw.animalserve.entity.response.BiologyVO;
import com.zzw.animalserve.exception.BusinessException;
import com.zzw.animalserve.service.BiologyImageService;
import com.zzw.animalserve.service.ImageInformationService;
import com.zzw.animalserve.utils.TimeFormat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.zzw.animalserve.service.BiologyService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Api(tags = "动物信息")
@RestController
@RequestMapping("/biology")
public class BiologyController extends BaseController{

    @Autowired
    private BiologyService biologyService;

    @Autowired
    private BiologyImageService biologyImageService;

    @Autowired
    private ImageInformationService imageInformationService;


    /**
     * 分页查询
     * @param biologyDto
     * @return
     */
    @ApiOperation("前台列表")
    @PostMapping("/listQuery")
//    @PreAuthorize("hasAuthority('sys:article:lists')")
    public BaseResponse<PageInfo<BiologyVO>> queryByPage(@RequestBody BiologyDto biologyDto, Integer pageNum) {
        //第几页为null时,显示第一页
        if (pageNum == null) {
            pageNum = 1;
        }
        PageInfo<BiologyVO> page = biologyService.findPage(pageNum, biologyDto);
        if(page.getList().size() <= 0){
            throw new BusinessException(ErrorCode.NULL_ERROR,"该分类下未查询到动物列表");
        }
        return ResultUtils.success(page);
    }

    /**
     * 分页查询
     * @param pageNum
     * @return
     */
    @ApiOperation("前台全部生物列表")
    @GetMapping("/listAll/{pageNum}")
//    @PreAuthorize("hasAuthority('sys:article:lists')")
    public BaseResponse<PageInfo<BiologyVO>> queryByPageList(@PathVariable("pageNum") Integer pageNum) {
        //第几页为null时,显示第一页
        if (pageNum == null) {
            pageNum = 1;
        }
        PageInfo<BiologyVO> page = biologyService.findPageList(pageNum);
        return ResultUtils.success(page);
    }

    /**
     * 分页查询
     * @param name
     * @return
     */
    @ApiOperation("后台生物列表")
    @GetMapping("/list")
//    @PreAuthorize("hasAuthority('sys:biology:lists')")
    public BaseResponse<List<BiologyVO>> queryByPage(String name) {
        // todo 联接纲类
        if(StringUtils.isAnyBlank(name)){
            List<Biology> allBiology = biologyService.findAllBiology();
            List<BiologyVO> biologyVOList = new ArrayList<>();
            for (Biology temp:allBiology) {
                if(temp.getDelectTag() == 0){
                    BiologyVO biologyVO = BiologyVO.entityToVO(temp);
                    biologyVOList.add(biologyVO);
                }
            }
            return ResultUtils.success(biologyVOList);
        }else{
            Biology biology = new Biology();
            biology.setBiologyName(name);
            List<Biology> biologyByCondition = biologyService.findBiologyByCondition(biology);
            List<BiologyVO> biologyVOList = new ArrayList<>();
            for (Biology temp:biologyByCondition) {
                if(temp.getDelectTag() == 0){
                    BiologyVO biologyVO = BiologyVO.entityToVO(temp);
                    biologyVOList.add(biologyVO);
                }
            }
            return ResultUtils.success(biologyVOList);
        }
    }

    @ApiOperation("条件查询")
    @PostMapping("/listSearch")
//    @PreAuthorize("hasAuthority('sys:biology:lists')")
    public BaseResponse<List<BiologyVO>> queryBySearch(@RequestBody BiologyDto biologyDto) {
        Biology biology = biologyDto.toEntity();
        List<Biology> biologyByCondition = biologyService.findBiologyByCondition(biology);
        List<BiologyVO> biologyVOList = new ArrayList<>();
        for (Biology temp:biologyByCondition) {
            if(temp.getDelectTag() == 0){
                BiologyVO biologyVO = BiologyVO.entityToVO(temp);
                biologyVOList.add(biologyVO);
            }
        }
        return ResultUtils.success(biologyVOList);
    }

    // Page<RoleVO> roles = roleService.page(getPage(), new QueryWrapper<Role>().like(StrUtil.isNotBlank(name), "name", name));

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiOperation("单个目信息")
    @GetMapping("/searchId/{id}")
    //@PreAuthorize("hasAuthority('sys:biology:sigln')")
    public BaseResponse<BiologyVO> queryById(@PathVariable("id") Long id) {
        Biology biologyBybiologyIdList = this.biologyService.findBiologyBybiologyIdList(id);
        BiologyVO biologyVO = BiologyVO.entityToVO(biologyBybiologyIdList);
        return ResultUtils.success(biologyVO);
    }

    /**
     * 新增数据
     *
     * @param biology 实体
     * @return 新增结果
     */
    @ApiOperation("新增动物信息")
    @PostMapping("/save")
    //@PreAuthorize("hasAuthority('sys:biology:save')")
    public BaseResponse<Integer> add(@RequestBody BiologyDto biology) {
        //返回一个ID动物
        Biology biology1 = biology.toEntity();
        biology1.setCreateTime(new Date());
        biology1.setUpdateTime(new Date());
        biology1.setDelectTag(0);
        Integer integer = biologyService.addBiology(biology1);
        System.out.println("=====biology1======>"+biology.toString());
        //添加
        for (String temp: biology.getImages()) {
            ImageInformation imageInformation = new ImageInformation();
            imageInformation.setImageInformationUrl(temp);
            imageInformation.setImageInformationName(biology.getName());
            imageInformation.setImageInformationContent(biology.getName());
            imageInformation.setImageStatus(0);
            imageInformation.setImageClass("0");
            imageInformation.setDelectTag(0);
            imageInformation.setCreateTime(TimeFormat.now());
            imageInformation.setUpdateTime(TimeFormat.now());
            imageInformation.setCreateId(2L);
            imageInformation.setUpdateId(2L);
            Integer integer1 = imageInformationService.addImageInformation(imageInformation);
            BiologyImage biologyImage = new BiologyImage();
            biologyImage.setBiologyId(biology1.getBiologyId());
            biologyImage.setImageInformationId(imageInformation.getImageInformationId());
            biologyImageService.addBiologyImage(biologyImage);
        }
        return ResultUtils.success(integer);
    }

    /**
     * 编辑数据
     *
     * @param biology 实体
     * @return 编辑结果
     */
    @ApiOperation("编辑信息")
    @PutMapping("/update")
    //@PreAuthorize("hasAuthority('sys:biology:update')")
    public BaseResponse<Integer> edit(@RequestBody BiologyDto biology) {
        Biology biology1 = biology.toEntity();
        biology1.setUpdateTime(new Date());
        return ResultUtils.success(this.biologyService.updateBiologyBybiologyId(biology1));
    }

    /**
     * 删除数据
     * @param id 主键
     * @return 删除是否成功
     */
    @ApiOperation("删除信息")
    @DeleteMapping("/delete")
    //@PreAuthorize("hasAuthority('sys:biology:delete')")
    public BaseResponse<Integer> deleteById(Long id) {
        Biology biology = biologyService.findBiologyBybiologyId(id);
        biology.setDelectTag(1);
        return ResultUtils.success(this.biologyService.updateBiologyBybiologyId(biology));
    }


}