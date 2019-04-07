package com.atcast.service;

import java.util.List;

import com.atcast.domain.Dict;

public interface DictService {

	List<Dict> findByCode(String dict_type_code);

}
