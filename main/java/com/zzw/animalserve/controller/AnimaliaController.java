package com.zzw.animalserve.controller;


import com.zzw.animalserve.common.BaseController;
import com.zzw.animalserve.common.BaseResponse;
import com.zzw.animalserve.common.ResultUtils;
import com.zzw.animalserve.entity.*;
import com.zzw.animalserve.entity.dto.AnimaliaDto;
import com.zzw.animalserve.entity.response.AnimaliaVO;
import com.zzw.animalserve.mapper.ChordataMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.zzw.animalserve.service.AnimaliaService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@Api(tags = "界")
@RestController
@RequestMapping("/animalia")
public class AnimaliaController extends BaseController {

    @Autowired
    private AnimaliaService animaliaService;



    /**
     * 分页查询
     * @param
     * @return
     */
    @ApiOperation("全部界列表")
    @GetMapping("/listAll")
    //@PreAuthorize("hasAuthority('sys:animalia:lists')")
    public BaseResponse<List<AnimaliaVO>> queryByPageList() {
        if(redisCache.getCacheList("animalia").size() == 0){
            List<Animalia> allAnimalia = animaliaService.findAllAnimalia();
            List<AnimaliaVO> animaliaVOList = new ArrayList<>();
            for (Animalia animalia : allAnimalia) {
                if(animalia.getDelectTag() == 0){
                    AnimaliaVO animaliaVO = AnimaliaVO.entityToVO(animalia);
                    animaliaVOList.add(animaliaVO);
                }
            }
            redisCache.setCacheList("animalia",animaliaVOList);
        }
        List<AnimaliaVO> temp = redisCache.getCacheList("animalia");
        return ResultUtils.success(temp);
    }

    @ApiOperation("全部界列表")
    @PostMapping("/listSearch")
    //@PreAuthorize("hasAuthority('sys:animalia:lists')")
    public BaseResponse<List<AnimaliaVO>> queryByPageList(@RequestBody AnimaliaDto animaliaDto) {
        Animalia animalia1 = animaliaDto.toEntity();
        List<Animalia> allAnimalia = animaliaService.findAnimaliaByCondition(animalia1);
        List<AnimaliaVO> animaliaVOList = new ArrayList<>();
        for (Animalia animalia : allAnimalia) {
            if(animalia.getDelectTag() == 0){
                AnimaliaVO animaliaVO = AnimaliaVO.entityToVO(animalia);
                animaliaVOList.add(animaliaVO);
            }
        }
        return ResultUtils.success(animaliaVOList);
    }

    // Page<RoleVO> roles = roleService.page(getPage(), new QueryWrapper<Role>().like(StrUtil.isNotBlank(name), "name", name));

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiOperation("单个界信息")
    @GetMapping("/searchId/{id}")
    //@PreAuthorize("hasAuthority('sys:animalia:sigln')")
    public BaseResponse<AnimaliaVO> queryById(@PathVariable("id") Long id) {
        Animalia animaliaByanimaliaId = this.animaliaService.findAnimaliaByanimaliaId(id);
        AnimaliaVO animaliaVO = AnimaliaVO.entityToVO(animaliaByanimaliaId);
        return ResultUtils.success(animaliaVO);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param animaliaDto 主键
     * @return 单条数据
     */
    @ApiOperation("条件查询界信息")
    @PostMapping("/searchSome")
    //@PreAuthorize("hasAuthority('sys:animalia:sigln')")
    public BaseResponse<List<AnimaliaVO>> queryBySome(@RequestBody AnimaliaDto animaliaDto) {
        Animalia animalia = animaliaDto.toEntity();
        List<Animalia> animaliaByCondition = this.animaliaService.findAnimaliaByCondition(animalia);
        List<AnimaliaVO> animaliaVOList = new ArrayList<>();
        for (Animalia temp : animaliaByCondition) {
            if(temp.getDelectTag() == 0){
                AnimaliaVO animaliaVO = AnimaliaVO.entityToVO(temp);
                animaliaVOList.add(animaliaVO);
            }
        }
        return ResultUtils.success(animaliaVOList);
    }

    /**
     * 新增数据
     *
     * @param animaliaDto 实体
     * @return 新增结果
     */
    @ApiOperation("新增界")
    @PostMapping("/save")
    //@PreAuthorize("hasAuthority('sys:animalia:save')")
    public BaseResponse<Integer> add(@RequestBody AnimaliaDto animaliaDto) {
        Animalia animalia = animaliaDto.toEntity();
        animalia.setCreateTime(new Date());
        animalia.setUpdateTime(new Date());
        animalia.setDelectTag(0);
        redisCache.deleteObject("animalia");
        return ResultUtils.success(this.animaliaService.addAnimalia(animalia));
    }

    /**
     * 编辑数据
     *
     * @param animaliaDto 实体
     * @return 编辑结果
     */
    @ApiOperation("编辑界信息")
    @PutMapping("/update")
    //@PreAuthorize("hasAuthority('sys:animalia:update')")
    public BaseResponse<Integer> edit(@RequestBody AnimaliaDto animaliaDto) {
        Animalia animalia = animaliaDto.toEntity();
        animalia.setUpdateTime(new Date());
        redisCache.deleteObject("animalia");
        return ResultUtils.success(this.animaliaService.updateAnimaliaByanimaliaId(animalia));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @ApiOperation("删除界")
    @DeleteMapping("/delete")
    //@PreAuthorize("hasAuthority('sys:animalia:delete')")
    public BaseResponse<Integer> deleteById(Long id) {
        Animalia animalia = animaliaService.findAnimaliaByanimaliaId(id);
        animalia.setDelectTag(1);
        //门
        Chordata chordata = new Chordata();
        chordata.setAnimaliaId(id);
        List<Chordata> chordataByCondition = chordataService.findChordataByCondition(chordata);
        for (Chordata temp: chordataByCondition) {
            temp.setDelectTag(1);
            chordataService.updateChordataBychordataId(temp);
        }
        //纲
        Mammalia mammalia = new Mammalia();
        mammalia.setAnimaliaId(id);
        List<Mammalia> mammaliaByCondition = mammaliaService.findMammaliaByCondition(mammalia);
        for (Mammalia temp:mammaliaByCondition) {
            temp.setDelectTag(1);
            mammaliaService.updateMammaliaBymammaliaId(temp);
        }
        //目
        Artiodactyla artiodactyla = new Artiodactyla();
        artiodactyla.setAnimaliaId(id);
        List<Artiodactyla> artiodactylaByCondition = artiodactylaService.findArtiodactylaByCondition(artiodactyla);
        for (Artiodactyla temp: artiodactylaByCondition) {
            temp.setDelectTag(1);
            artiodactylaService.updateArtiodactylaByartiodactylaId(temp);
        }
        //科
        Bovidae bovidae = new Bovidae();
        bovidae.setAnimaliaId(id);
        List<Bovidae> bovidaeByCondition = bovidaeService.findBovidaeByCondition(bovidae);
        for (Bovidae temp: bovidaeByCondition) {
            temp.setDelectTag(1);
            bovidaeService.updateBovidaeBybovidaeId(temp);
        }
        //属
        Bos bos = new Bos();
        bos.setAnimaliaId(id);
        List<Bos> bosByCondition = bosService.findBosByCondition(bos);
        for (Bos temp: bosByCondition) {
            temp.setDelectTag(1);
            bosService.updateBosBybosId(temp);
        }
        //动物
        Biology biology = new Biology();
        biology.setAnimaliaId(id);
        List<Biology> biologyByCondition = biologyService.findBiologyByCondition(biology);
        for (Biology temp: biologyByCondition) {
            temp.setDelectTag(1);
            biologyService.updateBiologyBybiologyId(temp);
        }
        redisCache.deleteObject("animalia");
        return ResultUtils.success(this.animaliaService.updateAnimaliaByanimaliaId(animalia));
    }

}