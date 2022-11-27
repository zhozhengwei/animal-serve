package com.zzw.animalserve.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzw.animalserve.common.BaseController;
import com.zzw.animalserve.common.BaseResponse;
import com.zzw.animalserve.common.ErrorCode;
import com.zzw.animalserve.common.ResultUtils;
import com.zzw.animalserve.entity.*;
import com.zzw.animalserve.entity.dto.ChordataDto;
import com.zzw.animalserve.entity.response.AnimaliaVO;
import com.zzw.animalserve.entity.response.ChordataVO;
import com.zzw.animalserve.exception.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.zzw.animalserve.service.ChordataService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Api(tags = "门")
@RestController
@RequestMapping("/chordata")
public class ChordataController extends BaseController {

    @Autowired
    private ChordataService chordataService;

    /**
     * 分页查询
     * @param name
     * @return
     */
    @ApiOperation("门列表")
    @GetMapping("/list")
    //@PreAuthorize("hasAuthority('sys:chordata:lists')")
    public BaseResponse<List<ChordataVO>> queryByPage(String name) {
        if(StringUtils.isAnyBlank(name)){
            if(redisCache.getCacheList("chordata").size() == 0){
                List<Chordata> allChordata = chordataService.findAllChordata();
                List<ChordataVO> chordataVOList = new ArrayList<>();
                for (Chordata temp : allChordata) {
                    if(temp.getDelectTag() == 0){
                        ChordataVO chordataVO = ChordataVO.entityToVO(temp);
                        chordataVOList.add(chordataVO);
                    }
                }
                redisCache.setCacheList("chordata",chordataVOList);
            }
            List<ChordataVO> temp = redisCache.getCacheList("chordata");
            return ResultUtils.success(temp);
        }else{
            if(redisCache.getCacheList("chordata").size() == 0){
                Chordata chordata = new Chordata();
                chordata.setChordataName(name);
                List<Chordata> chordataByCondition = chordataService.findChordataByCondition(chordata);
                List<ChordataVO> chordataVOList = new ArrayList<>();
                for (Chordata temp : chordataByCondition) {
                    if(temp.getDelectTag() == 0){
                        ChordataVO chordataVO = ChordataVO.entityToVO(temp);
                        chordataVOList.add(chordataVO);
                    }
                }
                redisCache.setCacheList("chordata",chordataVOList);
            }
            List<ChordataVO> temp = redisCache.getCacheList("chordata");
            return ResultUtils.success(temp);
        }
    }

    @ApiOperation("条件查询")
    @PostMapping("/listSearch")
    //@PreAuthorize("hasAuthority('sys:chordata:lists')")
    public BaseResponse<List<ChordataVO>> queryBySearch(@RequestBody ChordataDto chordataDto) {
        Chordata chordata = chordataDto.toEntity();
        List<Chordata> chordataByCondition = chordataService.findChordataByCondition(chordata);
        List<ChordataVO> chordataVOList = new ArrayList<>();
        for (Chordata temp : chordataByCondition) {
            ChordataVO chordataVO = ChordataVO.entityToVO(temp);
            chordataVOList.add(chordataVO);
        }
        return ResultUtils.success(chordataVOList);
    }

    // Page<RoleVO> roles = roleService.page(getPage(), new QueryWrapper<Role>().like(StrUtil.isNotBlank(name), "name", name));

    /**
     * 分页查询
     * @param
     * @return
     */
    @ApiOperation("前台门列表")
    @GetMapping("/listAll/{id}")
    //@PreAuthorize("hasAuthority('sys:animalia:lists')")
    public BaseResponse<List<ChordataVO>> queryByPageList(@PathVariable("id") Long id) {
        Chordata chordata = new Chordata();
        chordata.setAnimaliaId(id);
        List<Chordata> allAnimalia = chordataService.findChordataByCondition(chordata);
        if(StringUtils.isAnyBlank(allAnimalia.toString())){
            throw new BusinessException(ErrorCode.NULL_ERROR,"未查询到该界内部有门列表");
        }
        List<ChordataVO> animaliaVOList = new ArrayList<>();
        for (Chordata animalia : allAnimalia) {
            if(animalia.getDelectTag() == 0){
                ChordataVO animaliaVO = ChordataVO.entityToVO(animalia);
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
    @ApiOperation("单个门信息")
    @GetMapping("/searchId/{id}")
//    @PreAuthorize("hasAuthority('sys:chordata:sigln')")
    public BaseResponse<ChordataVO> queryById(@PathVariable("id") Long id) {
        Chordata chordataBychordataId = this.chordataService.findChordataBychordataId(id);
        ChordataVO chordataVO = ChordataVO.entityToVO(chordataBychordataId);
        return ResultUtils.success(chordataVO);
    }


    /**
     * 新增数据
     *
     * @param chordataDto 实体
     * @return 新增结果
     */
    @ApiOperation("新增门")
    @PostMapping("/save")
    //@PreAuthorize("hasAuthority('sys:chordata:save')")
    public BaseResponse<Integer> add(@RequestBody ChordataDto chordataDto) {
        Chordata chordata = chordataDto.toEntity();
        chordata.setCreateTime(new Date());
        chordata.setUpdateTime(new Date());
        chordata.setDelectTag(0);
        redisCache.deleteObject("chordata");
        return ResultUtils.success(this.chordataService.addChordata(chordata));
    }

    /**
     * 编辑数据
     *
     * @param chordataDto 实体
     * @return 编辑结果
     */
    @ApiOperation("编辑门信息")
    @PutMapping("/update")
    //@PreAuthorize("hasAuthority('sys:animalia:update')")
    public BaseResponse<Integer> edit(@RequestBody ChordataDto chordataDto) {
        Chordata chordata = chordataDto.toEntity();
        chordata.setUpdateTime(new Date());
        redisCache.deleteObject("chordata");
        return ResultUtils.success(this.chordataService.updateChordataBychordataId(chordata));
    }

    /**
     * 删除数据
     * @param id 主键
     * @return 删除是否成功
     */
    @ApiOperation("删除门")
    @DeleteMapping("/delete")
    //@PreAuthorize("hasAuthority('sys:animalia:delete')")
    public BaseResponse<Integer> deleteById(Long id) {
        Chordata chordata = chordataService.findChordataBychordataId(id);
        chordata.setDelectTag(1);
        //纲
        Mammalia mammalia = new Mammalia();
        mammalia.setChordataId(id);
        List<Mammalia> mammaliaByCondition = mammaliaService.findMammaliaByCondition(mammalia);
        for (Mammalia temp:mammaliaByCondition) {
            temp.setDelectTag(1);
            mammaliaService.updateMammaliaBymammaliaId(temp);
        }
        //目
        Artiodactyla artiodactyla = new Artiodactyla();
        artiodactyla.setChordataId(id);
        List<Artiodactyla> artiodactylaByCondition = artiodactylaService.findArtiodactylaByCondition(artiodactyla);
        for (Artiodactyla temp: artiodactylaByCondition) {
            temp.setDelectTag(1);
            artiodactylaService.updateArtiodactylaByartiodactylaId(temp);
        }
        //科
        Bovidae bovidae = new Bovidae();
        bovidae.setChordataId(id);
        List<Bovidae> bovidaeByCondition = bovidaeService.findBovidaeByCondition(bovidae);
        for (Bovidae temp: bovidaeByCondition) {
            temp.setDelectTag(1);
            bovidaeService.updateBovidaeBybovidaeId(temp);
        }
        //属
        Bos bos = new Bos();
        bos.setChordataId(id);
        List<Bos> bosByCondition = bosService.findBosByCondition(bos);
        for (Bos temp: bosByCondition) {
            temp.setDelectTag(1);
            bosService.updateBosBybosId(temp);
        }
        //动物
        Biology biology = new Biology();
        biology.setChordataId(id);
        List<Biology> biologyByCondition = biologyService.findBiologyByCondition(biology);
        for (Biology temp: biologyByCondition) {
            temp.setDelectTag(1);
            biologyService.updateBiologyBybiologyId(temp);
        }
        redisCache.deleteObject("chordata");
        return ResultUtils.success(this.chordataService.updateChordataBychordataId(chordata));
    }


}