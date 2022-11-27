package com.zzw.animalserve.controller;


import com.zzw.animalserve.common.BaseController;
import com.zzw.animalserve.common.BaseResponse;
import com.zzw.animalserve.common.ErrorCode;
import com.zzw.animalserve.common.ResultUtils;
import com.zzw.animalserve.entity.Artiodactyla;
import com.zzw.animalserve.entity.Biology;
import com.zzw.animalserve.entity.Bos;
import com.zzw.animalserve.entity.Bovidae;
import com.zzw.animalserve.entity.dto.ArtiodactylaDto;
import com.zzw.animalserve.entity.response.ArtiodactylaVO;
import com.zzw.animalserve.exception.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.zzw.animalserve.service.ArtiodactylaService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Api(tags = "目")
@RestController
@RequestMapping("/artiodactyla")
public class ArtiodactylaController extends BaseController {

    @Autowired
    private ArtiodactylaService artiodactylaService;



    /**
     * 分页查询
     * @param name
     * @return
     */
    @ApiOperation("目列表")
    @GetMapping("/list")
//    @PreAuthorize("hasAuthority('sys:mammalia:lists')")
    public BaseResponse<List<ArtiodactylaVO>> queryByPage(String name) {
        if(StringUtils.isAnyBlank(name)){
            if(redisCache.getCacheList("artiodactyla").size()==0){
                List<Artiodactyla> allArtiodactyla = artiodactylaService.findAllArtiodactyla();
                List<ArtiodactylaVO> artiodactylaVOList = new ArrayList<>();
                for (Artiodactyla temp:allArtiodactyla) {
                    if(temp.getDelectTag() == 0){
                        ArtiodactylaVO artiodactylaVO = ArtiodactylaVO.entityToVO(temp);
                        artiodactylaVOList.add(artiodactylaVO);
                    }
                }
                redisCache.setCacheList("artiodactyla",artiodactylaVOList);
            }
            List<ArtiodactylaVO> temp=redisCache.getCacheList("artiodactyla");
            return ResultUtils.success(temp);
        }else{
            if(redisCache.getCacheList("artiodactyla").size()==0){
                Artiodactyla artiodactyla = new Artiodactyla();
                artiodactyla.setArtiodactylaName(name);
                List<Artiodactyla> artiodactylaByCondition = artiodactylaService.findArtiodactylaByCondition(artiodactyla);
                List<ArtiodactylaVO> artiodactylaVOList = new ArrayList<>();
                for (Artiodactyla temp:artiodactylaByCondition) {
                    if(temp.getDelectTag() == 0){
                        ArtiodactylaVO artiodactylaVO = ArtiodactylaVO.entityToVO(temp);
                        artiodactylaVOList.add(artiodactylaVO);
                    }
                }
                redisCache.setCacheList("artiodactyla",artiodactylaVOList);
            }
            List<ArtiodactylaVO> temp=redisCache.getCacheList("artiodactyla");
            return ResultUtils.success(temp);
        }
    }

    @ApiOperation("条件查询")
    @PostMapping("/listSearch")
//    @PreAuthorize("hasAuthority('sys:mammalia:lists')")
    public BaseResponse<List<ArtiodactylaVO>> queryBySearch(@RequestBody ArtiodactylaDto artiodactylaDto) {
        Artiodactyla artiodactyla = artiodactylaDto.toEntity();
        List<Artiodactyla> artiodactylaByCondition = artiodactylaService.findArtiodactylaByCondition(artiodactyla);
        List<ArtiodactylaVO> artiodactylaVOList = new ArrayList<>();
        for (Artiodactyla temp:artiodactylaByCondition) {
            ArtiodactylaVO artiodactylaVO = ArtiodactylaVO.entityToVO(temp);
            artiodactylaVOList.add(artiodactylaVO);
        }
        return ResultUtils.success(artiodactylaVOList);
    }

    // Page<RoleVO> roles = roleService.page(getPage(), new QueryWrapper<Role>().like(StrUtil.isNotBlank(name), "name", name));

    @ApiOperation("目列表")
    @GetMapping("/listAll/{id}")
//    @PreAuthorize("hasAuthority('sys:animalia:lists')")
    public BaseResponse<List<ArtiodactylaVO>> queryByPageList(@PathVariable("id") Long id) {
        Artiodactyla chordata = new Artiodactyla();
        chordata.setMammaliaId(id);
        List<Artiodactyla> allAnimalia = artiodactylaService.findArtiodactylaByCondition(chordata);
        if(StringUtils.isAnyBlank(allAnimalia.toString())){
            throw new BusinessException(ErrorCode.NULL_ERROR,"未查询到该纲内部有目列表");
        }
        List<ArtiodactylaVO> animaliaVOList = new ArrayList<>();
        for (Artiodactyla animalia : allAnimalia) {
            if(animalia.getDelectTag() == 0){
                ArtiodactylaVO animaliaVO = ArtiodactylaVO.entityToVO(animalia);
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
    @ApiOperation("单个目信息")
    @GetMapping("/searchId/{id}")
    //@PreAuthorize("hasAuthority('sys:mammalia:sigln')")
    public BaseResponse<ArtiodactylaVO> queryById(@PathVariable("id") Long id) {
        Artiodactyla artiodactylaByartiodactylaId = this.artiodactylaService.findArtiodactylaByartiodactylaId(id);
        ArtiodactylaVO artiodactylaVO = ArtiodactylaVO.entityToVO(artiodactylaByartiodactylaId);
        return ResultUtils.success(artiodactylaVO);
    }


    /**
     * 新增数据
     *
     * @param artiodactylaDto 实体
     * @return 新增结果
     */
    @ApiOperation("新增目")
    @PostMapping("/save")
    //@PreAuthorize("hasAuthority('sys:chordata:save')")
    public BaseResponse<Integer> add(@RequestBody ArtiodactylaDto artiodactylaDto) {
        Artiodactyla artiodactyla = artiodactylaDto.toEntity();
        artiodactyla.setCreateTime(new Date());
        artiodactyla.setUpdateTime(new Date());
        artiodactyla.setDelectTag(0);
        redisCache.deleteObject("artiodactyla");
        return ResultUtils.success(this.artiodactylaService.addArtiodactyla(artiodactyla));
    }

    /**
     * 编辑数据
     *
     * @param artiodactylaDto 实体
     * @return 编辑结果
     */
    @ApiOperation("编辑目信息")
    @PutMapping("/update")
    //@PreAuthorize("hasAuthority('sys:mammalia:update')")
    public BaseResponse<Integer> edit(@RequestBody ArtiodactylaDto artiodactylaDto) {
        Artiodactyla artiodactyla = artiodactylaDto.toEntity();
        artiodactyla.setUpdateTime(new Date());
        redisCache.deleteObject("artiodactyla");
        return ResultUtils.success(this.artiodactylaService.updateArtiodactylaByartiodactylaId(artiodactyla));
    }

    /**
     * 删除数据
     * @param id 主键
     * @return 删除是否成功
     */
    @ApiOperation("删除目")
    @DeleteMapping("/delete")
    //@PreAuthorize("hasAuthority('sys:mammalia:delete')")
    public BaseResponse<Integer> deleteById(Long id) {
        Artiodactyla artiodactyla = artiodactylaService.findArtiodactylaByartiodactylaId(id);
        artiodactyla.setDelectTag(1);
        //科
        Bovidae bovidae = new Bovidae();
        bovidae.setArtiodactylaId(id);
        List<Bovidae> bovidaeByCondition = bovidaeService.findBovidaeByCondition(bovidae);
        for (Bovidae temp: bovidaeByCondition) {
            temp.setDelectTag(1);
            bovidaeService.updateBovidaeBybovidaeId(temp);
        }
        //属
        Bos bos = new Bos();
        bos.setArtiodactylaId(id);
        List<Bos> bosByCondition = bosService.findBosByCondition(bos);
        for (Bos temp: bosByCondition) {
            temp.setDelectTag(1);
            bosService.updateBosBybosId(temp);
        }
        //动物
        Biology biology = new Biology();
        biology.setArtiodactylaId(id);
        List<Biology> biologyByCondition = biologyService.findBiologyByCondition(biology);
        for (Biology temp: biologyByCondition) {
            temp.setDelectTag(1);
            biologyService.updateBiologyBybiologyId(temp);
        }
        redisCache.deleteObject("artiodactyla");
        return ResultUtils.success(this.artiodactylaService.updateArtiodactylaByartiodactylaId(artiodactyla));
    }

}