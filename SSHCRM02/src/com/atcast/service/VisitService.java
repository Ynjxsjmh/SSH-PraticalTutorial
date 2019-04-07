package com.atcast.service;

import org.hibernate.criterion.DetachedCriteria;

import com.atcast.domain.PageBean;
import com.atcast.domain.Visit;

public interface VisitService {

	PageBean<Visit> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria);

	void save(Visit visit);

}
