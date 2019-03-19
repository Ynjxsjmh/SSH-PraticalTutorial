package cn.atcast.crm.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import cn.atcast.crm.dao.BaseDictDao;
import cn.atcast.crm.dao.impl.BaseDictDaoImpl;
import cn.atcast.crm.domain.BaseDict;
import cn.atcast.crm.service.SystemService;

public class SystemServiceImpl implements SystemService{

	@Override
	public List<BaseDict> findBaseDictListByType(String typecode) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(BaseDict.class);
		detachedCriteria.add(Restrictions.eq("dictTypeCode", typecode));
		BaseDictDao baseDictDao = new BaseDictDaoImpl();
		
		return baseDictDao.findBaseDictList(detachedCriteria);
	}

}
