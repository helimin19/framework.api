package com.tongshi.framework.api.service;

import java.util.List;

/**
 * 基础服务接口
 * @author 何利民
 *
 */
public interface BaseService<T, P> {
	
	/**
	 * 新增
	 * @param entity 需要新增的对象
	 * @return 是否成功
	 */
	boolean insert(T entity);
	
	/**
	 * 批量新增
	 * @param list 需要新增的对象列表
	 * @return 新增数量
	 */
	int insertBatch(List<T> list);
	
	/**
	 * 更新
	 * @param entity 需要更新的对象
	 * @return 是否成功
	 */
	boolean update(T entity);
	
	/**
	 * 批量更新
	 * @param list 需要更新的对象列表
	 * @return 更新数量
	 */
	int updateBatch(List<T> list);
	
	/**
	 * 删除
	 * @param primaryKey 主键
	 * @return 是否成功
	 */
	boolean delete(P primaryKey);
	
	/**
	 * 批量删除
	 * @param list 需要删除的主键列表
	 * @return 是否成功
	 */
	int deleteBatch(List<P> list);
	
	/**
	 * 按主键取得实例
	 * @param primaryKey 主键
	 * @return 实例
	 */
	T findByPrimaryKey(P primaryKey);
	
	/**
	 * 取得所有实例
	 * @return 实例列表
	 */
	List<T> findAll();
	
}
