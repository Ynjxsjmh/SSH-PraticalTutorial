package com.atcast.service;

import org.hibernate.criterion.DetachedCriteria;

import com.atcast.domain.Linkman;
import com.atcast.domain.PageBean;

public interface LinkmanService {

	PageBean<Linkman> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria);

}
