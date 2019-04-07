package cn.atcast.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.atcast.domain.BaseDict;
import cn.atcast.pojo.BaseDictVo;

public interface BaseDictDao {

	// 创建一个DetachedCriteria
	DetachedCriteria createDetachedCriteria();

	// 查询数据字典信息
	public List<BaseDict> findBaseDictList(BaseDictVo baseDictVo);

}
