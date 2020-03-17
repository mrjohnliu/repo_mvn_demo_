package com.liuke.springbatch.demo.com;

import javax.sql.DataSource;

import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.liuke.springbatch.demo.entity.Emp;
@Configuration
public class MyDatabaseWriter {
	
	@Autowired
	private DataSource dataSource;
	
	@Bean
	public JdbcBatchItemWriter<Emp> writeIntodb() throws Exception {
		//items 對象就是要寫出的數據
		String sql=" insert into tb_emp(id,name,email,sex,age,address) values (:id,:name,:email,:sex,:age,:address)";
		JdbcBatchItemWriter<Emp> jdbcBatchItemWriter=new JdbcBatchItemWriter<Emp>();
		jdbcBatchItemWriter.setDataSource(dataSource);
		jdbcBatchItemWriter.setSql(sql);
		jdbcBatchItemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider());
		return 		jdbcBatchItemWriter;
	}
}
