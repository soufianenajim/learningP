package com.learning.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.learning.model.Module;
public interface ModuleRepository extends JpaRepository<Module,Long > {

}
