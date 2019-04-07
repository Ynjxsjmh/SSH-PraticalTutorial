package cn.atcast.dao;

import org.hibernate.criterion.DetachedCriteria;

//T实体类型，PK主键类型
public interface BaseDao<T, PK> {

	// 创建DetachedCriteria
	public DetachedCriteria createDetachedCriteria();

	// 根据主键查询对象
	public T findById(PK id);

	// 插入记录
	public void insert(T entity);

	// 更新记录
	public void update(T entity);

	// 删除记录
	public void delete(T entity);

	// 根据主键删除
	public void deleteById(PK id);

}
