package com.zzw.animalserve.controller;

import com.zzw.animalserve.common.BaseResponse;
import com.zzw.animalserve.common.ResultUtils;
import com.zzw.animalserve.entity.Chordata;
import com.zzw.animalserve.entity.Publication;
import com.zzw.animalserve.entity.Tag;
import com.zzw.animalserve.entity.dto.ChordataDto;
import com.zzw.animalserve.entity.dto.PublicationDto;
import com.zzw.animalserve.entity.dto.TagDto;
import com.zzw.animalserve.entity.response.ChordataVO;
import com.zzw.animalserve.entity.response.TagVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.zzw.animalserve.service.TagService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Api(tags = "标签")
@RestController
@RequestMapping("/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @ApiOperation("标签列表")
    @GetMapping("/list")
    public BaseResponse<List<TagVO>> searchList(String name){
        Tag tag = new Tag();
        tag.setTagTitle(name);
        if(StringUtils.isAnyBlank(name)){
            List<Tag> allTag = tagService.findAllTag();
            List<TagVO> tagVOList = new ArrayList<>();
            for (Tag temp : allTag) {
                if(temp.getDelectTag() == 0){
                    TagVO tagVO = TagVO.entityToVO(temp);
                    tagVOList.add(tagVO);
                }
            }
            List<TagVO> tagVOS = new ArrayList<>();
            for (int i = 0 ; i<4; i++){
                TagVO tagVO = tagVOList.get(i);
                tagVOS.add(tagVO);
            }
            return ResultUtils.success(tagVOS);
        }
        List<Tag> tagByCondition = tagService.findTagByCondition(tag);
        List<TagVO> tagVOList = new ArrayList<>();
        for (Tag temp : tagByCondition) {
            if(temp.getDelectTag() == 0){
                TagVO tagVO = TagVO.entityToVO(temp);
                tagVOList.add(tagVO);
            }

        }
        List<TagVO> tagVOS = new ArrayList<>();
        for (int i = 0 ; i<4; i++){
            TagVO tagVO = tagVOList.get(i);
            tagVOS.add(tagVO);
        }
        return ResultUtils.success(tagVOS);
    }

    @ApiOperation("标签列表")
    @GetMapping("/listAll")
    public BaseResponse<List<TagVO>> searchAllList(String name){
        Tag tag = new Tag();
        tag.setTagTitle(name);
        if(StringUtils.isAnyBlank(name)){
            List<Tag> allTag = tagService.findAllTag();
            List<TagVO> tagVOList = new ArrayList<>();
            for (Tag temp : allTag) {
                if(temp.getDelectTag() == 0){
                    TagVO tagVO = TagVO.entityToVO(temp);
                    tagVOList.add(tagVO);
                }
            }
            return ResultUtils.success(tagVOList);
        }
        List<Tag> tagByCondition = tagService.findTagByCondition(tag);
        List<TagVO> tagVOList = new ArrayList<>();
        for (Tag temp : tagByCondition) {
            if(temp.getDelectTag() == 0){
                TagVO tagVO = TagVO.entityToVO(temp);
                tagVOList.add(tagVO);
            }

        }
        return ResultUtils.success(tagVOList);
    }

    /**
     * 新增数据
     *
     * @param tagDto 实体
     * @return 新增结果
     */
    @ApiOperation("新增出版物信息")
    @PostMapping("/save")
//    @PreAuthorize("hasAuthority('sys:public:save')")
    public BaseResponse<Integer> add(@RequestBody TagDto tagDto) {
        Tag tag = tagDto.toEntity();
        tag.setCreateTime(new Date());
        tag.setUpdateTime(new Date());
        tag.setDelectTag(0);
        return ResultUtils.success(this.tagService.addTag(tag));
    }

    @ApiOperation("单个标签信息")
    @GetMapping("searchId/{id}")
    public BaseResponse<TagVO> queryById(@PathVariable("id") Long id) {
        // todo 联接纲类
        Tag bytagId = this.tagService.findTagBytagId(id);
        TagVO tagVO = TagVO.entityToVO(bytagId);
        return ResultUtils.success(tagVO);
    }


    /**
     * 编辑数据
     *
     * @param tagDto 实体
     * @return 编辑结果
     */
    @ApiOperation("编辑标签信息")
    @PutMapping("/update")
    //@PreAuthorize("hasAuthority('sys:animalia:update')")
    public BaseResponse<Integer> edit(@RequestBody TagDto tagDto) {
        Tag tag = tagDto.toEntity();
        tag.setUpdateTime(new Date());
        return ResultUtils.success(this.tagService.updateTagBytagId(tag));
    }


    /**
     * 删除数据
     * @param id 主键
     * @return 删除是否成功
     */
    @ApiOperation("删除标签")
    @DeleteMapping("/delete")
    //@PreAuthorize("hasAuthority('sys:animalia:delete')")
    public BaseResponse<Integer> deleteById(Long id) {
        Tag tag = tagService.findTagBytagId(id);
        tag.setDelectTag(1);
        return ResultUtils.success(this.tagService.updateTagBytagId(tag));
    }


}