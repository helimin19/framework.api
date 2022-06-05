package com.tongshi.framework.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.tongshi.framework.api.annotation.ResponseVo;
import com.tongshi.framework.api.service.BaseService;
import com.tongshi.framework.api.validation.ValidList;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

/**
 * 基础接口
 * @author 何利民
 *
 */
@Validated
public abstract class BaseController<T, P> {

	protected abstract BaseService<T, P> getService();

	@ResponseVo
	@PostMapping("/insert")
	@Operation(summary = "新增对象")
	public boolean insert(@Valid @RequestBody T entity) {
		return getService().insert(entity);
	}
	
	@ResponseVo
	@PostMapping("/insertBatch")
	@Operation(summary = "批量新增对象")
	public int insertBatch(@Valid @RequestBody ValidList<T> list) {
		return getService().insertBatch(list);
	}
	
	@ResponseVo
	@PostMapping("/update")
	@Operation(summary = "更新对象")
	public boolean update(@Valid @RequestBody T entity) {
		return getService().update(entity);
	}
	
	@ResponseVo
	@PostMapping("/updateBatch")
	@Operation(summary = "批量更新对象")
	public int updateBatch(@Valid @RequestBody ValidList<T> list) {
		return getService().updateBatch(list);
	}
	
	@ResponseVo
	@PostMapping("/delete")
	@Operation(summary = "删除对象")
	public boolean delete(@RequestParam(required=true) P primaryKey) {
		return getService().delete(primaryKey);
	}
	
	@ResponseVo
	@PostMapping("/deleteBatch")
	@Operation(summary = "批量删除对象")
	public int deleteBatch(@Valid @RequestBody ValidList<P> list) {
		return getService().deleteBatch(list);
	}
	
	@ResponseVo
	@GetMapping("/findByPrimaryKey")
	@Operation(summary = "按主键取得对象")
	public T findByPrimaryKey(@Parameter(description = "主键", required = true) P primaryKey) {
		return getService().findByPrimaryKey(primaryKey);
	}
	
	@ResponseVo
	@GetMapping("/findAll")
	@Operation(summary = "取得所有对象")
	public List<T> findAll() {
		return getService().findAll();
	}
	
}
