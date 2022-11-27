package com.zzw.animalserve.security;

import cn.hutool.json.JSONUtil;

import com.zzw.animalserve.common.result.Result;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//token验证，无权限数据返回的处理器
@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

		response.setContentType("application/json;charset=UTF-8");
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);

		ServletOutputStream outputStream = response.getOutputStream();

		Result result = Result.fail(accessDeniedException.getMessage());  //返回结果对象， 赋值accessDeniedException 没有权限异常的信息

		outputStream.write(JSONUtil.toJsonStr(result).getBytes("UTF-8"));  //响应回 vue程序

		outputStream.flush();
		outputStream.close();

	}
}
