package cn.atcast.crm.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.atcast.crm.domain.BaseDict;

public interface BaseDictDao {
	
	//数据字典列表查询
	public List<BaseDict> findBaseDictList(DetachedCriteria detachedCriteria);

}
