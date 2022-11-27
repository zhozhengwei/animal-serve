package com.zzw.animalserve.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import com.zzw.animalserve.common.BaseController;
import com.zzw.animalserve.common.BaseResponse;
import com.zzw.animalserve.common.ErrorCode;
import com.zzw.animalserve.common.ResultUtils;
import com.zzw.animalserve.entity.Book;
import com.zzw.animalserve.entity.ImageInformation;
import com.zzw.animalserve.entity.dto.ImageInformationDto;
import com.zzw.animalserve.entity.dto.VideoDto;
import com.zzw.animalserve.entity.response.ImageInformationVO;
import com.zzw.animalserve.entity.response.VideoVO;
import com.zzw.animalserve.exception.BusinessException;
import com.zzw.animalserve.utils.COSStorageUtil;
import com.zzw.animalserve.utils.TimeFormat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.zzw.animalserve.service.ImageInformationService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

@Api(tags = "图库")
@RestController
@RequestMapping("/imageInformation")
public class ImageInformationController extends BaseController {

    @Autowired
    private ImageInformationService imageInformationService;


    /**
     * 分页查询
     * @param imageInformationDto
     * @return
     */
    @ApiOperation("前台图片分页条件列表")
    @PostMapping("/listQuery")
//    @PreAuthorize("hasAuthority('sys:article:lists')")
    public BaseResponse<PageInfo<ImageInformationVO>> queryByPage(@RequestBody ImageInformationDto imageInformationDto, Integer pageNum) {
        if(imageInformationDto.getType() == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"分页数据请携带参数");
        }
        //第几页为null时,显示第一页
        if (pageNum == null) {
            pageNum = 1;
        }
        PageInfo<ImageInformationVO> page = imageInformationService.findPage(pageNum, imageInformationDto);

        return ResultUtils.success(page);
    }

    @ApiOperation("前台图片分页条件列表")
    @PostMapping("/listSearch")
//    @PreAuthorize("hasAuthority('sys:article:lists')")
    public BaseResponse<PageInfo<ImageInformationVO>> queryByClassType(@RequestBody ImageInformationDto imageInformationDto, Integer pageNum) {
        //第几页为null时,显示第一页
        if (pageNum == null) {
            pageNum = 1;
        }
        PageInfo<ImageInformationVO> page = imageInformationService.findClassType(pageNum, imageInformationDto.getName());

        return ResultUtils.success(page);
    }


    /**
     * 分页查询
     *
     * @param name
     * @return
     */
    @ApiOperation("图片列表")
    @GetMapping("/list")
//    @PreAuthorize("hasAuthority('sys:image:lists')")
    public BaseResponse<List<ImageInformationVO>> queryByPage(String name) {
        if(StringUtils.isAnyBlank(name)){
            List<ImageInformation> allImageInformation = imageInformationService.findAllImageInformation();
            List<ImageInformationVO> imageInformationVOList = new ArrayList<>();
            for (ImageInformation temp:allImageInformation) {
                if(temp.getDelectTag() == 0){
                    ImageInformationVO imageInformationVO = ImageInformationVO.entityToVO(temp);
                    imageInformationVOList.add(imageInformationVO);
                }
            }
            return ResultUtils.success(imageInformationVOList);
        }else {
            ImageInformation imageInformation = new ImageInformation();
            imageInformation.setImageInformationName(name);
            List<ImageInformation> imageInformationByCondition = imageInformationService.findImageInformationByCondition(imageInformation);
            List<ImageInformationVO> imageInformationVOList = new ArrayList<>();
            for (ImageInformation temp:imageInformationByCondition) {
                if(temp.getDelectTag() == 0){
                    ImageInformationVO imageInformationVO = ImageInformationVO.entityToVO(temp);
                    imageInformationVOList.add(imageInformationVO);
                }
            }
            return ResultUtils.success(imageInformationVOList);
        }
    }

    @ApiOperation("条件查询")
    @PostMapping("/listSearchImage")
//    @PreAuthorize("hasAuthority('sys:image:lists')")
    public BaseResponse<List<ImageInformationVO>> queryBySearch(@RequestBody ImageInformationDto imageInformationDto) {
        ImageInformation imageInformation = imageInformationDto.toEntity();
        List<ImageInformation> imageInformationByCondition = imageInformationService.findImageInformationByCondition(imageInformation);
        List<ImageInformationVO> imageInformationVOList = new ArrayList<>();
        for (ImageInformation temp:imageInformationByCondition) {
            if(temp.getDelectTag() == 0){
                ImageInformationVO imageInformationVO = ImageInformationVO.entityToVO(temp);
                imageInformationVOList.add(imageInformationVO);
            }
        }
        return ResultUtils.success(imageInformationVOList);
    }

    // Page<RoleVO> roles = roleService.page(getPage(), new QueryWrapper<Role>().like(StrUtil.isNotBlank(name), "name", name));

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiOperation("单个图片信息")
    @GetMapping("/searchId/{id}")
//    @PreAuthorize("hasAuthority('sys:image:sigln')")
    public BaseResponse<ImageInformationVO> queryById(@PathVariable("id") Long id) {
        ImageInformation imageInformationByimageInformationId = this.imageInformationService.findImageInformationByimageInformationId(id);
        ImageInformationVO imageInformationVO = ImageInformationVO.entityToVO(imageInformationByimageInformationId);
        return ResultUtils.success(imageInformationVO);
    }

    /**
     * 新增数据
     *
     * @param imageInformationDto 实体
     * @return 新增结果
     */
    @ApiOperation("新增图片信息")
    @PostMapping("/save")
//    @PreAuthorize("hasAuthority('sys:image:save')")
    public BaseResponse<Integer> add(@RequestBody ImageInformationDto imageInformationDto) {
        ImageInformation imageInformation = imageInformationDto.toEntity();
        System.out.println("======imageInformation=======>"+imageInformation.toString());
        if(StringUtils.isAllBlank(imageInformation.getImageInformationUrl())){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "图片上传不能为空！");
        }
        imageInformation.setCreateTime(TimeFormat.now());
        imageInformation.setUpdateTime(TimeFormat.now());
        imageInformation.setDelectTag(0);
        Integer save = imageInformationService.addImageInformation(imageInformation);
        return ResultUtils.success(save);
    }

    /**
     * 新增数据
     *
     * @param file 实体
     * @return 新增结果
     */
    @ApiOperation("新增图片")
    @PostMapping(value = "/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    @PreAuthorize("hasAuthority('sys:image:save')")
    public BaseResponse<Object> upLoad(@RequestPart("file") MultipartFile file) {
        if(file.getName() == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "图片上传不能为空！");
        }
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "图片上传不能为空！");
        }
        String contentType = file.getContentType();
        String originalFilename = file.getOriginalFilename();
        String ext = originalFilename.substring(originalFilename.lastIndexOf("."), originalFilename.length());
        //拼接好的路径
        String result = COSStorageUtil.upLoad(inputStream, ext, contentType);

        ImageInformation imageInformation = new ImageInformation();
        imageInformation.setImageInformationName(file.getName());
        imageInformation.setImageInformationUrl(result);
        imageInformation.setImageStatus(1);
        imageInformation.setImageInformationContent(file.getName());
        imageInformation.setImageClass("0");
        imageInformation.setCreateTime(TimeFormat.now());
        imageInformation.setUpdateTime(TimeFormat.now());
        imageInformation.setDelectTag(0);
        System.out.println("========图片信息==========>"+imageInformation.toString());
        Integer save = imageInformationService.addImageInformation(imageInformation);
        return ResultUtils.success(result);
    }

    /**
     * 新增数据
     *
     * @param file 实体
     * @return 新增结果
     */
    @ApiOperation("多次新增图片")
    @PostMapping("/letUpload")
//    @PreAuthorize("hasAuthority('sys:image:save')")
    public BaseResponse<Object> upLoadLet(@RequestPart("file") MultipartFile file) {
        ImageInformation imageInformation = new ImageInformation();
        imageInformation.setImageInformationName(file.getName());
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "图片上传不能为空！");
        }
        String contentType = file.getContentType();
        String originalFilename = file.getOriginalFilename();
        String ext = originalFilename.substring(originalFilename.lastIndexOf("."), originalFilename.length());
        //拼接好的路径
        String result = COSStorageUtil.upLoad(inputStream, ext, contentType);
        imageInformation.setImageInformationUrl(result);
        imageInformation.setImageStatus(0);
        //todo 动物多张图片
        //返回的是id
        Integer integer = imageInformationService.addImageInformation(imageInformation);
        Map<Integer,String> map = new HashMap<>();
        map.put(integer,result);
        return ResultUtils.success(map);
    }


    /**
     * 编辑数据
     *
     * @param imageInformationDto 实体
     * @return 编辑结果
     */
    @ApiOperation("编辑图片信息")
    @PutMapping("/update")
//    @PreAuthorize("hasAuthority('sys:image:update')")
    public BaseResponse<Integer> edit(@RequestBody ImageInformationDto imageInformationDto) {
        ImageInformation imageInformation = imageInformationDto.toEntity();
        if(StringUtils.isAllBlank(imageInformation.getImageInformationUrl())){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "图片上传不能为空！");
        }
        imageInformation.setUpdateTime(TimeFormat.now());
        return ResultUtils.success(this.imageInformationService.updateImageInformationByimageInformationId(imageInformation));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @ApiOperation("删除图片")
    @DeleteMapping("/delete")
//    @PreAuthorize("hasAuthority('sys:image:delete')")
    public BaseResponse<Integer> deleteById(Long id) {
        ImageInformation imageInformation = imageInformationService.findImageInformationByimageInformationId(id);
        imageInformation.setDelectTag(1);
        return ResultUtils.success(this.imageInformationService.updateImageInformationByimageInformationId(imageInformation));
    }


}