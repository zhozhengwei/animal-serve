package com.zzw.animalserve.controller;


import com.zzw.animalserve.common.BaseController;
import com.zzw.animalserve.common.BaseResponse;
import com.zzw.animalserve.common.ResultUtils;
import com.zzw.animalserve.entity.AssortSit;
import com.zzw.animalserve.entity.dto.AssortSitDto;
import com.zzw.animalserve.entity.response.AssortSitVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.zzw.animalserve.service.AssortSitService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Api(tags = "区域分类")
@RestController
@RequestMapping("/assortSit")
public class AssortSitController extends BaseController {

    @Autowired
    private AssortSitService assortSitService;

    /**
     * 分页查询
     * @param name
     * @return
     */
    @ApiOperation("界列表")
    @GetMapping("/list")
//    @PreAuthorize("hasAuthority('sys:animalia:lists')")
    public BaseResponse<List<AssortSitVO>> queryByPage(String name) {
        // todo 联接门类

        if(StringUtils.isAnyBlank(name)){
            List<AssortSit> allAssortSit = assortSitService.findAllAssortSit();
            List<AssortSitVO> assortSitVOList = new ArrayList<>();
            for (AssortSit temp:allAssortSit) {
                if(temp.getDelectTag() == 0){
                    AssortSitVO assortSitVO = AssortSitVO.entityToVO(temp);
                    assortSitVOList.add(assortSitVO);
                }
            }
            return ResultUtils.success(assortSitVOList);
        }
        AssortSit assortSit = new AssortSit();
        assortSit.setAssortSitName(name);
        List<AssortSit> assortSitByCondition = assortSitService.findAssortSitByCondition(assortSit);
        List<AssortSitVO> assortSitVOList = new ArrayList<>();
        for (AssortSit temp:assortSitByCondition) {
            if(temp.getDelectTag() == 0){
                AssortSitVO assortSitVO = AssortSitVO.entityToVO(temp);
                assortSitVOList.add(assortSitVO);
            }
        }
        return ResultUtils.success(assortSitVOList);
    }

    // Page<RoleVO> roles = roleService.page(getPage(), new QueryWrapper<Role>().like(StrUtil.isNotBlank(name), "name", name));

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiOperation("单个界信息")
    @GetMapping("searchId/{id}")
//    @PreAuthorize("hasAuthority('sys:animalia:sigln')")
    public BaseResponse<AssortSit> queryById(@PathVariable("id") Long id) {
        // todo 联接门类
        return ResultUtils.success(this.assortSitService.findAssortSitByassortSitId(id));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param AssortSitDto 主键
     * @return 单条数据
     */
    @ApiOperation("条件查询界信息")
    @PostMapping("/searchSome")
//    @PreAuthorize("hasAuthority('sys:animalia:sigln')")
    public BaseResponse<List<AssortSitVO>> queryBySome(@RequestBody AssortSitDto AssortSitDto) {
        // todo 联接门类
        AssortSit assortSit = AssortSitDto.toEntity();
        List<AssortSit> assortSitByCondition = this.assortSitService.findAssortSitByCondition(assortSit);
        List<AssortSitVO> assortSitVOList = new ArrayList<>();
        for (AssortSit temp : assortSitByCondition) {
            if(temp.getDelectTag() == 0){
                AssortSitVO assortSitVO = AssortSitVO.entityToVO(temp);
                assortSitVOList.add(assortSitVO);
            }
        }
        return ResultUtils.success(assortSitVOList);
    }

    /**
     * 新增数据
     *
     * @param assortSitDto 实体
     * @return 新增结果
     */
    @ApiOperation("新增界")
    @PostMapping("/save")
//    @PreAuthorize("hasAuthority('sys:animalia:save')")
    public BaseResponse<Integer> add(@RequestBody AssortSitDto assortSitDto) {
        AssortSit assortSit = assortSitDto.toEntity();
        assortSit.setCreateTime(new Date());
        assortSit.setUpdateTime(new Date());
        assortSit.setDelectTag(0);
        return ResultUtils.success(this.assortSitService.addAssortSit(assortSit));
    }

    /**
     * 编辑数据
     *
     * @param assortSitDto 实体
     * @return 编辑结果
     */
    @ApiOperation("编辑界信息")
    @PutMapping("/update")
//    @PreAuthorize("hasAuthority('sys:animalia:update')")
    public BaseResponse<Integer> edit(@RequestBody AssortSitDto assortSitDto) {
        AssortSit assortSit = assortSitDto.toEntity();
        assortSit.setUpdateTime(new Date());
        return ResultUtils.success(this.assortSitService.updateAssortSitByassortSitId(assortSit));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @ApiOperation("删除界")
    @DeleteMapping("/delete")
//    @PreAuthorize("hasAuthority('sys:animalia:delete')")
    public BaseResponse<Integer> deleteById(Long id) {
        AssortSit assortSit = assortSitService.findAssortSitByassortSitId(id);
        assortSit.setDelectTag(1);
        return ResultUtils.success(this.assortSitService.updateAssortSitByassortSitId(assortSit));
    }

}