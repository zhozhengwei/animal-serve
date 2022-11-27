package com.zzw.animalserve.controller;

import cn.hutool.core.lang.UUID;
import com.google.code.kaptcha.Producer;
import com.zzw.animalserve.common.BaseController;
import com.zzw.animalserve.common.BaseResponse;
import com.zzw.animalserve.common.ResultUtils;
import com.zzw.animalserve.common.lang.Const;
import com.zzw.animalserve.common.result.Result;
import cn.hutool.core.map.MapUtil;
import com.zzw.animalserve.entity.dto.AuthMemberDto;
import com.zzw.animalserve.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;


@Slf4j
@RestController
@RequestMapping("auth")
@Api(tags = "系统授权接口")
public class AuthController extends BaseController {
    @Autowired
    private Producer producer;

    @Autowired
    private AuthService authService;

    @ApiOperation("请求图片验证码")
    @GetMapping("/captcha")
    public BaseResponse<Object> captcha(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String code = producer.createText();
        String key = UUID.randomUUID().toString();
        BufferedImage image = producer.createImage(code);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image,"jpg",outputStream);
        BASE64Encoder encoder = new BASE64Encoder();
        String str="data:image/jpeg;base64,";
        String base64Img = str + encoder.encode(outputStream.toByteArray());

        //存储到redis中
        redisUtil.hset(Const.CAPTCHA_KEY,key,code,120);
        log.info("验证码 -- {} --{}",key,code);
        return ResultUtils.success(
                MapUtil.builder()
                        .put("key",key)
                        .put("captchaImg",base64Img)
                        .build()
        );
    }


    @ApiOperation("发送邮箱验证码")
    @PostMapping(value = "/getEmailCode")
    public BaseResponse<Object> getEmailCode(@RequestBody AuthMemberDto auth) {
        log.info("------>"+auth.getEmail());
        authService.sendMailCode(auth.getEmail());
        HttpStatus status = HttpStatus.OK;
        return ResultUtils.success(status);
    }


}
