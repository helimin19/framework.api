package com.tongshi.framework.api.interceptor;

import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tongshi.framework.api.vo.ResponseVo;

@RestControllerAdvice
@Order(Integer.MIN_VALUE)
public class GlobalResponseHandler implements ResponseBodyAdvice<Object> {

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		return returnType.hasMethodAnnotation(com.tongshi.framework.api.annotation.ResponseVo.class);
	}

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
			Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
			ServerHttpResponse response) {
		if (body instanceof String) {
			response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
			ObjectMapper om = new ObjectMapper();
			try {
				return om.writeValueAsString(new ResponseVo(body.toString()));
			} catch (JsonProcessingException e) {
				return ResponseVo.error(e.getMessage());
			}
		}
		return new ResponseVo(body);
	}

}
