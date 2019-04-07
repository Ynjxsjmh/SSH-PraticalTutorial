package cn.atcast.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import cn.atcast.dao.BaseDictDao;
import cn.atcast.domain.BaseDict;
import cn.atcast.pojo.BaseDictVo;

@Repository("baseDictDao")
public class BaseDictDaoImpl extends HibernateDaoSupport implements BaseDictDao {

	@Autowired
	public void setHT(HibernateTemplate hibernateTemplate) {
		// 将CustomerDao 中注入进来 的hibernateTemplate给父类的setHibernateTemplate方法传入
		this.setHibernateTemplate(hibernateTemplate);
	}

	@Override
	public List<BaseDict> findBaseDictList(BaseDictVo baseDictVo) {
		DetachedCriteria detachedCriteria = this.createDetachedCriteria();
		// 拼装 查询条件
		if (baseDictVo != null) {
			if (StringUtils.isNotEmpty(baseDictVo.getDictTypeCode())) {
				detachedCriteria.add(Restrictions.eq("dictTypeCode", baseDictVo.getDictTypeCode()));
			}
		}
		return (List<BaseDict>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
	}

	@Override
	public DetachedCriteria createDetachedCriteria() {
		return DetachedCriteria.forClass(BaseDict.class);
	}

}
