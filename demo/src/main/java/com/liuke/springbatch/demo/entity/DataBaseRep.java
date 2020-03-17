package com.liuke.springbatch.demo.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface DataBaseRep extends JpaRepository<Emp, String>{
	
	@Modifying
    @Transactional
    @Query(value="insert into tb_emp(id,name,sex,age,email,address) "
    		+ " values(emp.id,emp.name,emp.sex,emp.age,emp.email,emp.address)",nativeQuery = true)
    Integer insertData(Emp emp);

}
