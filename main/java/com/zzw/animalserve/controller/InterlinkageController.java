package com.zzw.animalserve.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import com.zzw.animalserve.common.BaseController;
import com.zzw.animalserve.common.BaseResponse;
import com.zzw.animalserve.common.ResultUtils;
import com.zzw.animalserve.entity.Interlinkage;
import com.zzw.animalserve.entity.dto.InterlinkageDto;
import com.zzw.animalserve.entity.response.InterlinkageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.zzw.animalserve.service.InterlinkageService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Api(tags = "外部链接")
@RestController
@RequestMapping("/interlinkage")
public class InterlinkageController extends BaseController {

    @Autowired
    private InterlinkageService interlinkageService;

    /**
     * 分页查询
     * @param name
     * @return
     */
    @ApiOperation("链接列表")
    @GetMapping("/list")
//    @PreAuthorize("hasAuthority('sys:link:lists')")
    public BaseResponse<List<InterlinkageVO>> queryByPage(String name) {
        if(StringUtils.isAnyBlank(name)){
            List<Interlinkage> allInterlinkage = interlinkageService.findAllInterlinkage();
            List<InterlinkageVO> interlinkageVOList = new ArrayList<InterlinkageVO>();
            for (Interlinkage temp:allInterlinkage) {
                if(temp.getDelectTag() == 0){
                    InterlinkageVO interlinkageVO = InterlinkageVO.entityToVO(temp);
                    interlinkageVOList.add(interlinkageVO);
                }
            }
            return ResultUtils.success(interlinkageVOList);
        }else{
            Interlinkage interlinkage = new Interlinkage();
            interlinkage.setInterlinkageName(name);
            List<Interlinkage> interlinkageByCondition = interlinkageService.findInterlinkageByCondition(interlinkage);
            List<InterlinkageVO> interlinkageVOList = new ArrayList<InterlinkageVO>();
            for (Interlinkage temp:interlinkageByCondition) {
                if(temp.getDelectTag() == 0){
                    InterlinkageVO interlinkageVO = InterlinkageVO.entityToVO(temp);
                    interlinkageVOList.add(interlinkageVO);
                }
            }
            return ResultUtils.success(interlinkageVOList);
        }
    }

    @ApiOperation("条件查询")
    @PostMapping("/listSearch")
//    @PreAuthorize("hasAuthority('sys:link:lists')")
    public BaseResponse<List<InterlinkageVO>> queryBySearch(@RequestBody InterlinkageDto interlinkageDto) {
        Interlinkage interlinkage = interlinkageDto.toEntity();
        List<Interlinkage> interlinkageByCondition = interlinkageService.findInterlinkageByCondition(interlinkage);
        List<InterlinkageVO> interlinkageVOList = new ArrayList<InterlinkageVO>();
        for (Interlinkage temp:interlinkageByCondition) {
            if(temp.getDelectTag() == 0){
                InterlinkageVO interlinkageVO = InterlinkageVO.entityToVO(temp);
                interlinkageVOList.add(interlinkageVO);
            }
        }
        return ResultUtils.success(interlinkageVOList);
    }


    /**
     * 分页查询
     * @param pageNum
     * @return
     */
    @ApiOperation("前台链接列表")
    @GetMapping("/listAll/{pageNum}")
//    @PreAuthorize("hasAuthority('sys:link:lists')")
    public BaseResponse<PageInfo<InterlinkageVO>> queryByPagefindAll(@PathVariable("pageNum") Integer pageNum) {
        //第几页为null时,显示第一页
        if (pageNum == null) {
            pageNum = 1;
        }
        PageInfo<InterlinkageVO> pageList = interlinkageService.findPageList(pageNum);
        return ResultUtils.success(pageList);
    }

    // Page<RoleVO> roles = roleService.page(getPage(), new QueryWrapper<Role>().like(StrUtil.isNotBlank(name), "name", name));

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiOperation("单个链接")
    @GetMapping("/searchId/{id}")
//    @PreAuthorize("hasAuthority('sys:link:sigln')")
    public BaseResponse<InterlinkageVO> queryById(@PathVariable("id") Long id) {
        Interlinkage interlinkageByinterlinkageId = this.interlinkageService.findInterlinkageByinterlinkageId(id);
        InterlinkageVO interlinkageVO = InterlinkageVO.entityToVO(interlinkageByinterlinkageId);
        return ResultUtils.success(interlinkageVO);
    }

    /**
     * 新增数据
     *
     * @param interlinkageDto 实体
     * @return 新增结果
     */
    @ApiOperation("新增链接")
    @PostMapping("/save")
//    @PreAuthorize("hasAuthority('sys:link:save')")
    public BaseResponse<Integer> add(@RequestBody InterlinkageDto interlinkageDto) {
        Interlinkage interlinkage = interlinkageDto.toEntity();
        interlinkage.setCreateTime(new Date());
        interlinkage.setUpdateTime(new Date());
        interlinkage.setDelectTag(0);
        Integer integer = interlinkageService.addInterlinkage(interlinkage);
        return ResultUtils.success(integer);
    }

    /**
     * 编辑数据
     *
     * @param interlinkageDto 实体
     * @return 编辑结果
     */
    @ApiOperation("编辑链接信息")
    @PutMapping("/update")
//    @PreAuthorize("hasAuthority('sys:link:update')")
    public BaseResponse<Integer> edit(@RequestBody InterlinkageDto interlinkageDto) {
        Interlinkage interlinkage = interlinkageDto.toEntity();
        interlinkage.setUpdateTime(new Date());
        return ResultUtils.success(this.interlinkageService.updateInterlinkageByinterlinkageId(interlinkage));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @ApiOperation("删除链接")
    @DeleteMapping("/delete")
//    @PreAuthorize("hasAuthority('sys:link:delete')")
    public BaseResponse<Integer> deleteById(Long id) {
        Interlinkage applyMember = interlinkageService.findInterlinkageByinterlinkageId(id);
        applyMember.setDelectTag(1);
        return ResultUtils.success(this.interlinkageService.updateInterlinkageByinterlinkageId(applyMember));
    }


}