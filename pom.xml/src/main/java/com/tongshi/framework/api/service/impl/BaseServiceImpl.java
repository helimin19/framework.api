package com.tongshi.framework.api.service.impl;

import java.util.List;

import com.tongshi.framework.api.mapper.BaseMapper;
import com.tongshi.framework.api.service.BaseService;

/**
 * 基础服务接口实现
 * @author 何利民
 *
 */
public abstract class BaseServiceImpl<T, P> implements BaseService<T, P> {

	protected abstract BaseMapper<T, P> getMapper();
	
	@Override
	public boolean insert(T entity) {
		return getMapper().insert(entity);
	}

	@Override
	public int insertBatch(List<T> list) {
		return getMapper().insertBatch(list);
	}

	@Override
	public boolean update(T entity) {
		return getMapper().update(entity);
	}

	@Override
	public int updateBatch(List<T> list) {
		return getMapper().updateBatch(list);
	}

	@Override
	public boolean delete(P primaryKey) {
		return getMapper().delete(primaryKey);
	}

	@Override
	public int deleteBatch(List<P> list) {
		return getMapper().deleteBatch(list);
	}

	@Override
	public T findByPrimaryKey(P primaryKey) {
		return getMapper().findByPrimaryKey(primaryKey);
	}

	@Override
	public List<T> findAll() {
		return getMapper().findAll();
	}

}
