package com.zzw.animalserve.controller;

import com.zzw.animalserve.common.BaseResponse;
import com.zzw.animalserve.common.ErrorCode;
import com.zzw.animalserve.common.ResultUtils;
import com.zzw.animalserve.entity.VideoInformation;
import com.zzw.animalserve.entity.dto.VideoDto;
import com.zzw.animalserve.exception.BusinessException;
import com.zzw.animalserve.service.VideoInformationService;
import com.zzw.animalserve.utils.TencentCOS;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 视频上传接口
 */
@Api(tags = "视频上传")
@Controller
@RequestMapping("/video")
public class VideoUploadController {
    @Autowired
    private VideoInformationService videoService;

    @ApiOperation("视频上传(有信息)")
    @RequestMapping("/upload")
    @ResponseBody
    public BaseResponse<Object> upload(@RequestBody VideoDto videoDto,@RequestPart("file")  MultipartFile multipartFile) throws Exception {
        System.out.println("======================================");
        System.out.println("传入文件:"+multipartFile);
        System.out.println("======================================");

        //获取文件的名称
        String fileName = multipartFile.getOriginalFilename();

        System.out.println("======================================");
        System.out.println("文件的名称:"+fileName);
        System.out.println("======================================");

        //判断有无后缀
        if (fileName.lastIndexOf(".") < 0)
//            return new ForumResult(500, "上传图片格式不正确", null);

            throw new BusinessException(ErrorCode.PARAMS_ERROR,"上传图片格式不正确");

        //获取文件后缀
        String prefix = fileName.substring(fileName.lastIndexOf("."));

        //如果不是图片
        if ( !prefix.equalsIgnoreCase(".gif") &&  !prefix.equalsIgnoreCase(".mp4")) {
//            return new ForumResult(500, "上传图片格式不正确", null);
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"上传图片格式不正确");
        }

        //使用uuid作为文件名，防止生成的临时文件重复
        final File excelFile = File.createTempFile("imagesFile-" + System.currentTimeMillis(), prefix);
        //将Multifile转换成File
        multipartFile.transferTo(excelFile);

        //调用腾讯云工具上传文件
        String videoName = TencentCOS.uploadfile(excelFile, "视频");

        System.out.println("======================================");
        System.out.println("调用腾讯云工具上传文件:"+videoName);
        System.out.println("======================================");
        System.out.println("最终的路径");
        String videoUrl="https://zzwvideo-1308302031.cos.ap-shanghai.myqcloud.com/"+videoName;
        System.out.println(videoUrl);

        //程序结束时，删除临时文件
        deleteFile(excelFile);

        //存入图片名称，用于网页显示
        //model.addAttribute("imageName", imageName);

        /*
        更新数据库
        * */
        VideoInformation video=new VideoInformation();
        video.setVideoInformationContent(videoName+':'+videoDto.getContent());
        video.setVideoInformationUrl(videoUrl);
        video.setCreateTime(new Date());
        video.setUpdateTime(new Date());
        video.setDelectTag(0);
        video.setVideoInformationType(1);
        Integer integer = videoService.addVideoInformation(video);

        //Map<Integer, String> map = new HashMap<>();
        //map.put(integer,videoUrl);
        //返回成功信息
        //return new ForumResult(200, "头像更换成功", imageName);
        return ResultUtils.success(integer);
    }

    @ApiOperation("视频上传")
    @RequestMapping("/uploadVideo")
    @ResponseBody
    public BaseResponse<Object> uploadVideo(@RequestPart("file")  MultipartFile multipartFile) throws Exception {
        System.out.println("======================================");
        System.out.println("传入文件:"+multipartFile);
        System.out.println("======================================");

        //获取文件的名称
        String fileName = multipartFile.getOriginalFilename();

        System.out.println("======================================");
        System.out.println("文件的名称:"+fileName);
        System.out.println("======================================");

        //判断有无后缀
        if (fileName.lastIndexOf(".") < 0)
//            return new ForumResult(500, "上传图片格式不正确", null);

            throw new BusinessException(ErrorCode.PARAMS_ERROR,"上传图片格式不正确");

        //获取文件后缀
        String prefix = fileName.substring(fileName.lastIndexOf("."));

        //如果不是图片
        if ( !prefix.equalsIgnoreCase(".gif") &&  !prefix.equalsIgnoreCase(".mp4")) {
//            return new ForumResult(500, "上传图片格式不正确", null);
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"上传图片格式不正确");
        }

        //使用uuid作为文件名，防止生成的临时文件重复
        final File excelFile = File.createTempFile("imagesFile-" + System.currentTimeMillis(), prefix);
        //将Multifile转换成File
        multipartFile.transferTo(excelFile);

        //调用腾讯云工具上传文件
        String videoName = TencentCOS.uploadfile(excelFile, "视频");

        System.out.println("======================================");
        System.out.println("调用腾讯云工具上传文件:"+videoName);
        System.out.println("======================================");
        System.out.println("最终的路径");
        String videoUrl="https://zzwvideo-1308302031.cos.ap-shanghai.myqcloud.com/"+videoName;
        System.out.println(videoUrl);

        //程序结束时，删除临时文件
        deleteFile(excelFile);

        //存入图片名称，用于网页显示
//        model.addAttribute("imageName", imageName);

        /*
        更新数据库
        * */
//        VideoInformation video=new VideoInformation();
//        video.setVideoInformationContent(videoName);
//        video.setVideoInformationUrl(videoUrl);
//        video.setCreateTime(new Date());
//        video.setUpdateTime(new Date());
//        video.setDelectTag(0);
//        video.setVideoInformationType(1);
//        Integer integer = videoService.addVideoInformation(video);
        //返回成功信息
//        return new ForumResult(200, "头像更换成功", imageName);
        return ResultUtils.success(videoUrl);
    }

    /**
     * 删除临时文件
     *
     * @param files 临时文件，可变参数
     */
    private void deleteFile(File... files) {
        for (File file : files) {
            if (file.exists()) {
                file.delete();
            }
        }
    }
}