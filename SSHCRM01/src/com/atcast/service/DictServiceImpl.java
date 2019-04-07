package com.atcast.service;

import java.util.List;

import com.atcast.dao.DictDao;
import com.atcast.domain.Dict;

/**
 * 字典
 * 
 * @author Administrator
 */

public class DictServiceImpl implements DictService {

	private DictDao dictDao;

	public void setDictDao(DictDao dictDao) {
		this.dictDao = dictDao;
	}

	/**
	 * 通过客户类别编码查询字段
	 */
	public List<Dict> findByCode(String dict_type_code) {
		return dictDao.findByCode(dict_type_code);
	}

}
