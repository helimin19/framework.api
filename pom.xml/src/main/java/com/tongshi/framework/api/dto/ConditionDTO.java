package com.tongshi.framework.api.dto;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
@Schema(title="条件DTO", description = "查询条件")
public class ConditionDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Schema(title = "字段名称", description = "字段名称")
	private String name;
	
	@Schema(title = "比较运算符", description = "比较运算符")
	private String operator;
	
	@Schema(title = "字段值", description = "字段值")
	private Object value;
}
