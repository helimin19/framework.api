package com.tongshi.framework.api.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@NoArgsConstructor
@Schema(title="条件组DTO", description = "查询条件")
public class ConditionGroupDTO implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Schema(title = "条件列表", description = "条件列表(同一列表中使用and)")
	private List<ConditionDTO> items = new ArrayList<>();
	
	public ConditionGroupDTO(String name, String operator, Object value) {
		this.items.add(new ConditionDTO(name, operator, value));
	}
	
	public void add(String name, String operator, Object value) {
		this.items.add(new ConditionDTO(name, operator, value));
	}
	
}
