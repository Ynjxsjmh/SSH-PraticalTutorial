package com.atcast.dao;

import java.util.List;

import com.atcast.domain.Dict;

public interface DictDao {

	List<Dict> findByCode(String dict_type_code);

}
