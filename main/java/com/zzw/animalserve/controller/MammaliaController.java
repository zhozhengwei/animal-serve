package com.zzw.animalserve.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzw.animalserve.common.BaseController;
import com.zzw.animalserve.common.BaseResponse;
import com.zzw.animalserve.common.ErrorCode;
import com.zzw.animalserve.common.ResultUtils;
import com.zzw.animalserve.entity.*;
import com.zzw.animalserve.entity.dto.MammaliaDto;
import com.zzw.animalserve.entity.response.MammaliaVO;
import com.zzw.animalserve.exception.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.zzw.animalserve.service.MammaliaService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Api(tags = "纲")
@RestController
@RequestMapping("/mammalia")
public class MammaliaController extends BaseController{

    @Autowired
    private MammaliaService mammaliaService;

    /**
     * 分页查询
     * @param name
     * @return
     */
    @ApiOperation("纲列表")
    @GetMapping("/list")
    //@PreAuthorize("hasAuthority('sys:mammalia:lists')")
    public BaseResponse<List<MammaliaVO>> queryByPage(String name) {

        if(StringUtils.isAnyBlank(name)){
            if(redisCache.getCacheList("mammalia").size() == 0){
                List<Mammalia> allMammalia = mammaliaService.findAllMammalia();
                List<MammaliaVO> mammaliaVOList = new ArrayList<>();
                for (Mammalia temp:allMammalia) {
                    if(temp.getDelectTag() == 0){
                        MammaliaVO mammaliaVO = MammaliaVO.entityToVO(temp);
                        mammaliaVOList.add(mammaliaVO);
                    }
                }
                redisCache.setCacheList("mammalia",mammaliaVOList);
            }
            List<MammaliaVO> temp = redisCache.getCacheList("mammalia");
            return ResultUtils.success(temp);
        }else{
            Mammalia mammalia = new Mammalia();
            mammalia.setMammaliaName(name);
            if(redisCache.getCacheList("mammalia").size() == 0){
                List<Mammalia> mammaliaByCondition = mammaliaService.findMammaliaByCondition(mammalia);
                List<MammaliaVO> mammaliaVOList = new ArrayList<>();
                for (Mammalia temp:mammaliaByCondition) {
                    if(temp.getDelectTag() == 0){
                        MammaliaVO mammaliaVO = MammaliaVO.entityToVO(temp);
                        mammaliaVOList.add(mammaliaVO);
                    }
                }
                redisCache.setCacheList("mammalia",mammaliaVOList);
            }
            List<MammaliaVO> temp = redisCache.getCacheList("mammalia");
            return ResultUtils.success(temp);
        }
    }

    @ApiOperation("条件查询")
    @PostMapping("/listSearch")
    //@PreAuthorize("hasAuthority('sys:mammalia:lists')")
    public BaseResponse<List<MammaliaVO>> queryBySearch(@RequestBody MammaliaDto mammaliaDto) {
        Mammalia mammalia = mammaliaDto.toEntity();
        List<Mammalia> mammaliaByCondition = mammaliaService.findMammaliaByCondition(mammalia);
        List<MammaliaVO> mammaliaVOList = new ArrayList<>();
        for (Mammalia temp:mammaliaByCondition) {
            if(temp.getDelectTag() == 0){
                MammaliaVO mammaliaVO = MammaliaVO.entityToVO(temp);
                mammaliaVOList.add(mammaliaVO);
            }
        }
        return ResultUtils.success(mammaliaVOList);
    }

    // Page<RoleVO> roles = roleService.page(getPage(), new QueryWrapper<Role>().like(StrUtil.isNotBlank(name), "name", name));


    @ApiOperation("前台纲列表")
    @GetMapping("/listAll/{id}")
    //@PreAuthorize("hasAuthority('sys:animalia:lists')")
    public BaseResponse<List<MammaliaVO>> queryByPageList(@PathVariable("id") Long id) {
        Mammalia chordata = new Mammalia();
        chordata.setChordataId(id);
        List<Mammalia> allAnimalia = mammaliaService.findMammaliaByCondition(chordata);
        if(StringUtils.isAnyBlank(allAnimalia.toString())){
            throw new BusinessException(ErrorCode.NULL_ERROR,"未查询到该门内部有纲列表");
        }
        List<MammaliaVO> animaliaVOList = new ArrayList<>();
        for (Mammalia animalia : allAnimalia) {
            if(animalia.getDelectTag() == 0){
                MammaliaVO animaliaVO = MammaliaVO.entityToVO(animalia);
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
    @ApiOperation("单个纲信息")
    @GetMapping("/searchId/{id}")
    //@PreAuthorize("hasAuthority('sys:mammalia:sigln')")
    public BaseResponse<MammaliaVO> queryById(@PathVariable("id") Long id) {
        Mammalia mammaliaBymammaliaId = this.mammaliaService.findMammaliaBymammaliaId(id);
        MammaliaVO mammaliaVO = MammaliaVO.entityToVO(mammaliaBymammaliaId);
        return ResultUtils.success(mammaliaVO);
    }


    /**
     * 新增数据
     *
     * @param mammaliaDto 实体
     * @return 新增结果
     */
    @ApiOperation("新增纲")
    @PostMapping("/save")
    //@PreAuthorize("hasAuthority('sys:mammalia:save')")
    public BaseResponse<Integer> add(@RequestBody MammaliaDto mammaliaDto) {
        Mammalia mammalia = mammaliaDto.toEntity();
        mammalia.setCreateTime(new Date());
        mammalia.setUpdateTime(new Date());
        mammalia.setDelectTag(0);
        redisCache.deleteObject("mammalia");
        return ResultUtils.success(this.mammaliaService.addMammalia(mammalia));
    }

    /**
     * 编辑数据
     *
     * @param mammaliaDto 实体
     * @return 编辑结果
     */
    @ApiOperation("编辑纲信息")
    @PutMapping("/update")
    //@PreAuthorize("hasAuthority('sys:mammalia:update')")
    public BaseResponse<Integer> edit(@RequestBody MammaliaDto mammaliaDto) {
        Mammalia mammalia = mammaliaDto.toEntity();
        mammalia.setUpdateTime(new Date());
        redisCache.deleteObject("mammalia");
        return ResultUtils.success(this.mammaliaService.updateMammaliaBymammaliaId(mammalia));
    }

    /**
     * 删除数据
     * @param id 主键
     * @return 删除是否成功
     */
    @ApiOperation("删除纲")
    @DeleteMapping("/delete")
    //@PreAuthorize("hasAuthority('sys:mammalia:delete')")
    public BaseResponse<Integer> deleteById(Long id) {
        Mammalia mammalia = mammaliaService.findMammaliaBymammaliaId(id);
        mammalia.setDelectTag(1);
        //目
        Artiodactyla artiodactyla = new Artiodactyla();
        artiodactyla.setMammaliaId(id);
        List<Artiodactyla> artiodactylaByCondition = artiodactylaService.findArtiodactylaByCondition(artiodactyla);
        for (Artiodactyla temp: artiodactylaByCondition) {
            temp.setDelectTag(1);
            artiodactylaService.updateArtiodactylaByartiodactylaId(temp);
        }
        //科
        Bovidae bovidae = new Bovidae();
        bovidae.setMammaliaId(id);
        List<Bovidae> bovidaeByCondition = bovidaeService.findBovidaeByCondition(bovidae);
        for (Bovidae temp: bovidaeByCondition) {
            temp.setDelectTag(1);
            bovidaeService.updateBovidaeBybovidaeId(temp);
        }
        //属
        Bos bos = new Bos();
        bos.setMammaliaId(id);
        List<Bos> bosByCondition = bosService.findBosByCondition(bos);
        for (Bos temp: bosByCondition) {
            temp.setDelectTag(1);
            bosService.updateBosBybosId(temp);
        }
        //动物
        Biology biology = new Biology();
        biology.setMammaliaId(id);
        List<Biology> biologyByCondition = biologyService.findBiologyByCondition(biology);
        for (Biology temp: biologyByCondition) {
            temp.setDelectTag(1);
            biologyService.updateBiologyBybiologyId(temp);
        }
        redisCache.deleteObject("mammalia");
        return ResultUtils.success(this.mammaliaService.updateMammaliaBymammaliaId(mammalia));
    }


}