package com.tongshi.framework.api.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.tongshi.framework.api.annotation.ResponseVo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="基础接口")
@RestController
@RequestMapping("/base")
public class DemoController {

	@ResponseVo
	@GetMapping("/test1")
	@Operation(summary = "测试字符串")
	public String test(@Parameter(description = "测试", required = true) String s) {
		return s;
	}
	
	@ResponseVo
	@GetMapping("/test2")
	@Operation(summary = "测试数字")
	public int test(@Parameter(description = "测试", required = true) int s) {
		return s;
	}
	
	@ResponseVo
	@GetMapping("/test3")
	@Operation(summary = "测试日期")
	public Date test(@Parameter(description = "测试", required = true) Date s) {
		return s;
	}
	
	@Autowired
	WebApplicationContext applicationContext;
	
	@ResponseVo
	@GetMapping("/apis")
	public String getApi() {
		RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
		Map<RequestMappingInfo, HandlerMethod> methodMap = mapping.getHandlerMethods();
		List<String> urlList = new ArrayList<>();
		for (RequestMappingInfo info : methodMap.keySet()) {
			Set<String> urlSet = info.getPatternsCondition().getPatterns();
			Set<RequestMethod> methods = info.getMethodsCondition().getMethods();
			System.out.println(methods.toString());
			for (String url : urlSet) {
				urlList.add("" + url);
			}
		}
		return urlList.toString();
	}

	
}
