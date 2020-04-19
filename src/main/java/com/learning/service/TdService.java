package com.learning.service;

import java.util.List;

import com.learning.dto.TdDTO;
import com.learning.model.Td;

public interface TdService extends CrudService<Td, TdDTO> {
	List<TdDTO> findByCour(Long courId);
	
	
	
}
