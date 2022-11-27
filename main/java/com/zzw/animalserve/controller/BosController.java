package com.zzw.animalserve.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzw.animalserve.common.BaseController;
import com.zzw.animalserve.common.BaseResponse;
import com.zzw.animalserve.common.ErrorCode;
import com.zzw.animalserve.common.ResultUtils;
import com.zzw.animalserve.entity.Biology;
import com.zzw.animalserve.entity.Bos;
import com.zzw.animalserve.entity.Bovidae;
import com.zzw.animalserve.entity.dto.BosDto;
import com.zzw.animalserve.entity.response.BosVO;
import com.zzw.animalserve.entity.response.BovidaeVO;
import com.zzw.animalserve.exception.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.zzw.animalserve.service.BosService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@Api(tags = "属")
@RestController
@RequestMapping("/bos")
public class BosController extends BaseController {

    @Autowired
    private BosService bosService;

    /**
     * 分页查询
     * @param name
     * @return
     */
    @ApiOperation("属列表")
    @GetMapping("/list")
    //@PreAuthorize("hasAuthority('sys:mammalia:lists')")
    public BaseResponse<List<BosVO>> queryByPage(String name) {
        if(StringUtils.isAnyBlank(name)){
            if(redisCache.getCacheList("bos").size()==0){
                List<Bos> allBos = bosService.findAllBos();
                List<BosVO> bosVOList = new ArrayList<>();
                for (Bos temp: allBos) {
                    if(temp.getDelectTag() == 0){
                        BosVO bosVO = BosVO.entityToVO(temp);
                        bosVOList.add(bosVO);
                    }
                }
                redisCache.setCacheList("bos",bosVOList);
            }
            List<BosVO> temp = redisCache.getCacheList("bos");
            return ResultUtils.success(temp);
        }else{
            if(redisCache.getCacheList("bos").size()==0){
                Bos bos = new Bos();
                bos.setBosName(name);
                List<Bos> bosByCondition = bosService.findBosByCondition(bos);
                List<BosVO> bosVOList = new ArrayList<>();
                for (Bos temp: bosByCondition) {
                    if(temp.getDelectTag() == 0){
                        BosVO bosVO = BosVO.entityToVO(temp);
                        bosVOList.add(bosVO);
                    }
                }
                redisCache.setCacheList("bos",bosVOList);
            }
            List<BosVO> temp = redisCache.getCacheList("bos");
            return ResultUtils.success(temp);
        }

    }

    @ApiOperation("条件查询")
    @PostMapping("/listSearch")
    //@PreAuthorize("hasAuthority('sys:mammalia:lists')")
    public BaseResponse<List<BosVO>> queryBySearch(@RequestBody BosDto bosDto) {
        Bos bos = bosDto.toEntity();
        List<Bos> bosByCondition = bosService.findBosByCondition(bos);
        List<BosVO> bosVOList = new ArrayList<>();
        for (Bos temp: bosByCondition) {
            if(temp.getDelectTag() == 0){
                BosVO bosVO = BosVO.entityToVO(temp);
                bosVOList.add(bosVO);
            }
        }
        return ResultUtils.success(bosVOList);
    }

    // Page<RoleVO> roles = roleService.page(getPage(), new QueryWrapper<Role>().like(StrUtil.isNotBlank(name), "name", name));

    @ApiOperation("前台属列表")
    @GetMapping("/listAll/{id}")
    //@PreAuthorize("hasAuthority('sys:animalia:lists')")
    public BaseResponse<List<BosVO>> queryByPageList(@PathVariable("id") Long id) {
        // todo 联接门类
        Bos chordata = new Bos();
        chordata.setBovidaeId(id);
        List<Bos> allAnimalia = bosService.findBosByCondition(chordata);
        if(StringUtils.isAnyBlank(allAnimalia.toString())){
            throw new BusinessException(ErrorCode.NULL_ERROR,"未查询到该科内部有属列表");
        }
        List<BosVO> animaliaVOList = new ArrayList<>();
        for (Bos animalia : allAnimalia) {
            if(animalia.getDelectTag() == 0){
                BosVO animaliaVO = BosVO.entityToVO(animalia);
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
    @ApiOperation("单个属信息")
    @GetMapping("/searchId/{id}")
    //@PreAuthorize("hasAuthority('sys:mammalia:sigln')")
    public BaseResponse<BosVO> queryById(@PathVariable("id") Long id) {
        Bos bosBybosId = this.bosService.findBosBybosId(id);
        BosVO bosVO = BosVO.entityToVO(bosBybosId);
        return ResultUtils.success(bosVO);
    }


    /**
     * 新增数据
     *
     * @param bosDto 实体
     * @return 新增结果
     */
    @ApiOperation("新增属")
    @PostMapping("/save")
    //@PreAuthorize("hasAuthority('sys:chordata:save')")
    public BaseResponse<Integer> add(@RequestBody BosDto bosDto) {
        Bos bos = bosDto.toEntity();
        bos.setCreateTime(new Date());
        bos.setUpdateTime(new Date());
        bos.setDelectTag(0);
        redisCache.deleteObject("bos");
        return ResultUtils.success(this.bosService.addBos(bos));
    }

    /**
     * 编辑数据
     *
     * @param bosDto 实体
     * @return 编辑结果
     */
    @ApiOperation("编辑属信息")
    @PutMapping("/update")
    //@PreAuthorize("hasAuthority('sys:mammalia:update')")
    public BaseResponse<Integer> edit(@RequestBody BosDto bosDto) {
        Bos bos = bosDto.toEntity();
        bos.setUpdateTime(new Date());
        redisCache.deleteObject("bos");
        return ResultUtils.success(this.bosService.updateBosBybosId(bos));
    }

    /**
     * 删除数据
     * @param id 主键
     * @return 删除是否成功
     */
    @ApiOperation("删除属")
    @DeleteMapping("/delete")
    //@PreAuthorize("hasAuthority('sys:mammalia:delete')")
    public BaseResponse<Integer> deleteById(Long id) {
        Bos bos = bosService.findBosBybosId(id);
        bos.setDelectTag(1);
        //动物
        Biology biology = new Biology();
        biology.setBosId(id);
        List<Biology> biologyByCondition = biologyService.findBiologyByCondition(biology);
        for (Biology temp: biologyByCondition) {
            temp.setDelectTag(1);
            biologyService.updateBiologyBybiologyId(temp);
        }
        redisCache.deleteObject("bos");
        return ResultUtils.success(this.bosService.updateBosBybosId(bos));
    }

}