package com.atcast.service;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atcast.dao.VisitDao;
import com.atcast.domain.PageBean;
import com.atcast.domain.Visit;

/**
 * 客户拜访业务层
 * 
 * @author Administrator
 */
@Service(value = "visitService")
@Transactional
public class VisitServiceImpl implements VisitService {

	@Resource(name = "visitDao")
	private VisitDao visitDao;

	/**
	 * 分页查询
	 */
	public PageBean<Visit> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria) {
		return visitDao.findByPage(pageCode, pageSize, criteria);
	}

	public void save(Visit visit) {
		visitDao.save(visit);
	}

}
