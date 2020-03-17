package com.liuke.springbatch.demo.config;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;

import com.liuke.springbatch.demo.com.MyItemWriter;
import com.liuke.springbatch.demo.entity.Emp;

//传入参数就要实现监听器接口
@Configuration
public class MyDataBaseReadJob implements StepExecutionListener{
	@Autowired
	private JobBuilderFactory  jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	private DataSource dataSource;
	
	//寫入到普通文件
	@Autowired
	@Qualifier("fileItemWriter")
	private  ItemWriter<Emp>  fileItemWriter;
	
	//寫入到xml文件
	@Autowired
	@Qualifier("xmlfileWriter")
	private  ItemWriter<Emp>  xmlfileWriter;
	
	/*@Autowired
	@Qualifier("myskipListener")
	private  SkipListener<Emp,Emp>  myskipListener;
	*/
	@Autowired
	@Qualifier("myitemProcessor")
	private  ItemProcessor<Emp,Emp>  myitemProcessor;
	
	
	private  Map<String, JobParameter> jobParameters=new HashMap<>();
/*	@Bean
	public Job myWriterdemoJob() {
		return jobBuilderFactory.get("myWriterdemoJob")
				.start(writedemoStep1())
				.build();
	}*/
	@Bean
	public Job databaseWriterJob9() {
		return jobBuilderFactory.get("databaseWriterJob9")
				.start(databaseWriteStep6())
				.build();
	}
	/*@Bean
	public Step writedemoStep1() {
		return stepBuilderFactory
				.get("writedemoStep1")
				.<Emp,Emp>chunk(2)
				.reader(jdbcPagingItemReader())
				.writer(writer1())
				.build();
	}*/
	@Bean
	public Step databaseWriteStep6() {
		
		return stepBuilderFactory
				.get("databaseWriteStep5")
				.listener(this)
				/*.<Emp,Emp>chunk(2)
				.reader(jdbcPagingItemReader())
				.processor(myitemProcessor)
				.writer(xmlfileWriter)
				.build();*/
				// 使用jobParameters 参数必须用tasklet方法不然要报错
				.tasklet(new Tasklet() {
					@Override
					public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
						System.out.println("job id:"+jobParameters.get("id").getValue());
						return RepeatStatus.FINISHED;
					}
				}).build();
	}
	@Bean
	public MyItemWriter databaseWriter() {
		return new MyItemWriter();
	}
	/*@Bean
	public MyItemWriter writer1() {
		return new MyItemWriter();
	}
	
	@Bean
	public MyItemReader reader1() {
		return new MyItemReader(Arrays.asList("111","222","234"));
	}*/

	@Bean
	public JdbcPagingItemReader<Emp> jdbcPagingItemReader() {
		JdbcPagingItemReader<Emp> jdbcPagingItemReader=new JdbcPagingItemReader<Emp>();
		jdbcPagingItemReader.setDataSource(dataSource);
		jdbcPagingItemReader.setFetchSize(2);
		//轉換對象
		jdbcPagingItemReader.setRowMapper(new RowMapper<Emp>(){

			@Override
			public Emp mapRow(ResultSet rs, int rowNum) throws SQLException {
				Emp emp=new Emp();
				emp.setAddress(rs.getString("address"));
				//emp.setId(rs.getInt("id"));
				emp.setAge(rs.getInt("age"));
				emp.setEmail(rs.getString("email"));
				emp.setSex(rs.getString("sex"));
				emp.setName(rs.getString("name"));
				return emp;
			}
			
		});
		//指定sql語句
		MySqlPagingQueryProvider queryProvider=new MySqlPagingQueryProvider();
		queryProvider.setSelectClause("id,name,sex,age,address,email");
		queryProvider.setFromClause("from tb_emp");
		//排序
		Map<String, Order> map=new HashMap<>(1);
		map.put("id",Order.ASCENDING);
		queryProvider.setSortKeys(map);
		
		jdbcPagingItemReader.setQueryProvider(queryProvider);
		return jdbcPagingItemReader;
	}
	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
				return ExitStatus.COMPLETED;
		
	}
	@Override
	public void beforeStep(StepExecution stepExecution) {
		jobParameters=stepExecution.getJobExecution().getJobParameters().getParameters();
	}
}
