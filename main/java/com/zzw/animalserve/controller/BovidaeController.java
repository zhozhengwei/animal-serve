package com.zzw.animalserve.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzw.animalserve.common.BaseController;
import com.zzw.animalserve.common.BaseResponse;
import com.zzw.animalserve.common.ErrorCode;
import com.zzw.animalserve.common.ResultUtils;
import com.zzw.animalserve.entity.Artiodactyla;
import com.zzw.animalserve.entity.Biology;
import com.zzw.animalserve.entity.Bos;
import com.zzw.animalserve.entity.Bovidae;
import com.zzw.animalserve.entity.dto.BovidaeDto;
import com.zzw.animalserve.entity.response.ArtiodactylaVO;
import com.zzw.animalserve.entity.response.BovidaeVO;
import com.zzw.animalserve.exception.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.zzw.animalserve.service.BovidaeService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Api(tags = "科")
@RestController
@RequestMapping("/bovidae")
public class BovidaeController extends BaseController {

    @Autowired
    private BovidaeService bovidaeService;


    /**
     * 分页查询
     * @param name
     * @return
     */
    @ApiOperation("科列表")
    @GetMapping("/list")
    //@PreAuthorize("hasAuthority('sys:mammalia:lists')")
    public BaseResponse<List<BovidaeVO>> queryByPage(String name) {
        if(StringUtils.isAnyBlank(name)){
            if(redisCache.getCacheList("bovidae").size()==0){
                List<Bovidae> allBovidae = bovidaeService.findAllBovidae();
                List<BovidaeVO> bovidaeVOList = new ArrayList<>();
                for (Bovidae temp:allBovidae) {
                    if(temp.getDelectTag() == 0){
                        BovidaeVO bovidaeVO = BovidaeVO.entityToVO(temp);
                        bovidaeVOList.add(bovidaeVO);
                    }
                }
                redisCache.setCacheList("bovidae",bovidaeVOList);
            }
            List<BovidaeVO> temp = redisCache.getCacheList("bovidae");
            return ResultUtils.success(temp);
        }else{
            if(redisCache.getCacheList("bovidae").size()==0){
                Bovidae bovidae = new Bovidae();
                bovidae.setBovidaeName(name);
                List<Bovidae> bovidaeByCondition = bovidaeService.findBovidaeByCondition(bovidae);
                List<BovidaeVO> bovidaeVOList = new ArrayList<>();
                for (Bovidae temp:bovidaeByCondition) {
                    if(temp.getDelectTag() == 0){
                        BovidaeVO bovidaeVO = BovidaeVO.entityToVO(temp);
                        bovidaeVOList.add(bovidaeVO);
                    }
                }
                redisCache.setCacheList("bovidae",bovidaeVOList);
            }
            List<BovidaeVO> temp = redisCache.getCacheList("bovidae");
            return ResultUtils.success(temp);
        }
    }

    @ApiOperation("条件查询")
    @PostMapping("/listSearch")
    //@PreAuthorize("hasAuthority('sys:mammalia:lists')")
    public BaseResponse<List<BovidaeVO>> queryBySearch(@RequestBody BovidaeDto bovidaeDto) {
        Bovidae bovidae = bovidaeDto.toEntity();
        List<Bovidae> bovidaeByCondition = bovidaeService.findBovidaeByCondition(bovidae);
        List<BovidaeVO> bovidaeVOList = new ArrayList<>();
        for (Bovidae temp:bovidaeByCondition) {
            if(temp.getDelectTag() == 0){
                BovidaeVO bovidaeVO = BovidaeVO.entityToVO(temp);
                bovidaeVOList.add(bovidaeVO);
            }
        }
        return ResultUtils.success(bovidaeVOList);
    }

    // Page<RoleVO> roles = roleService.page(getPage(), new QueryWrapper<Role>().like(StrUtil.isNotBlank(name), "name", name));


    @ApiOperation("前台科列表")
    @GetMapping("/listAll/{id}")
    //@PreAuthorize("hasAuthority('sys:animalia:lists')")
    public BaseResponse<List<BovidaeVO>> queryByPageList(@PathVariable("id") Long id) {
        Bovidae chordata = new Bovidae();
        chordata.setArtiodactylaId(id);
        System.out.println("===Bovidae=====>"+chordata.toString());
        List<Bovidae> allAnimalia = bovidaeService.findBovidaeByCondition(chordata);
        System.out.println("===Bovidae=====>"+allAnimalia.toString());
        if(StringUtils.isAnyBlank(allAnimalia.toString())){
            throw new BusinessException(ErrorCode.NULL_ERROR,"未查询到该目内部有科列表");
        }
        List<BovidaeVO> animaliaVOList = new ArrayList<>();
        for (Bovidae animalia : allAnimalia) {
            if(animalia.getDelectTag() == 0){
                BovidaeVO animaliaVO = BovidaeVO.entityToVO(animalia);
                animaliaVOList.add(animaliaVO);
            }
        }
        return ResultUtils.success(animaliaVOList);
    }


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiOperation("单个科信息")
    @GetMapping("/searchId/{id}")
    //@PreAuthorize("hasAuthority('sys:mammalia:sigln')")
    public BaseResponse<BovidaeVO> queryById(@PathVariable("id") Long id) {
        Bovidae bovidaeBybovidaeId = this.bovidaeService.findBovidaeBybovidaeId(id);
        BovidaeVO bovidaeVO = BovidaeVO.entityToVO(bovidaeBybovidaeId);
        return ResultUtils.success(bovidaeVO);
    }


    /**
     * 新增数据
     *
     * @param bovidaeDto 实体
     * @return 新增结果
     */
    @ApiOperation("新增科")
    @PostMapping("/save")
    //@PreAuthorize("hasAuthority('sys:chordata:save')")
    public BaseResponse<Integer> add(@RequestBody BovidaeDto bovidaeDto) {
        Bovidae bovidae = bovidaeDto.toEntity();
        bovidae.setCreateTime(new Date());
        bovidae.setUpdateTime(new Date());
        bovidae.setDelectTag(0);
        redisCache.deleteObject("bovidae");
        return ResultUtils.success(this.bovidaeService.addBovidae(bovidae));
    }

    /**
     * 编辑数据
     *
     * @param bovidaeDto 实体
     * @return 编辑结果
     */
    @ApiOperation("编辑科信息")
    @PutMapping("/update")
    //@PreAuthorize("hasAuthority('sys:mammalia:update')")
    public BaseResponse<Integer> edit(@RequestBody BovidaeDto bovidaeDto) {
        Bovidae bovidae = bovidaeDto.toEntity();
        bovidae.setUpdateTime(new Date());
        redisCache.deleteObject("bovidae");
        return ResultUtils.success(this.bovidaeService.updateBovidaeBybovidaeId(bovidae));
    }

    /**
     * 删除数据
     * @param id 主键
     * @return 删除是否成功
     */
    @ApiOperation("删除科")
    @DeleteMapping("/delete")
    //@PreAuthorize("hasAuthority('sys:mammalia:delete')")
    public BaseResponse<Integer> deleteById(Long id) {
        Bovidae bovidae = bovidaeService.findBovidaeBybovidaeId(id);
        bovidae.setDelectTag(1);
        //属
        Bos bos = new Bos();
        bos.setBovidaeId(id);
        List<Bos> bosByCondition = bosService.findBosByCondition(bos);
        for (Bos temp: bosByCondition) {
            temp.setDelectTag(1);
            bosService.updateBosBybosId(temp);
        }
        //动物
        Biology biology = new Biology();
        biology.setBovidaeId(id);
        List<Biology> biologyByCondition = biologyService.findBiologyByCondition(biology);
        for (Biology temp: biologyByCondition) {
            temp.setDelectTag(1);
            biologyService.updateBiologyBybiologyId(temp);
        }
        redisCache.deleteObject("bovidae");
        return ResultUtils.success(this.bovidaeService.updateBovidaeBybovidaeId(bovidae));
    }


}