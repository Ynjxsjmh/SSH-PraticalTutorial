package com.atcast.dao;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.atcast.domain.Dict;

/**
 * 持久层
 * @author Administrator
 */
public class DictDaoImpl extends HibernateDaoSupport implements DictDao {

	/**
	 * 通过客户类别编码查询字段
	 */
	public List<Dict> findByCode(String dict_type_code) {
		return (List<Dict>) this.getHibernateTemplate().find("from Dict where dict_type_code = ?", dict_type_code);
	}

}
