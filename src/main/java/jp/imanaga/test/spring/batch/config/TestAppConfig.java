package jp.imanaga.test.spring.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import jp.imanaga.test.spring.batch.base.TestTasklet;
import jp.imanaga.test.spring.batch.bean.TestBean;

@Configuration
@EnableBatchProcessing
@ComponentScan("jp.imanaga.test.spring.batch")
@PropertySource("file:./test.properties")
public class TestAppConfig {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private TestTasklet testTasklet;

	@Bean
	public Step testStep() {
		// TODO Error Handler?
		return stepBuilderFactory.get("testStep").tasklet(testTasklet).build();
	}

	@Bean
	public Job testJob(Step testStep) {
		// TODO Job Parameters Validator?
		// TODO Listener?
		return jobBuilderFactory.get("testJob").incrementer(new RunIdIncrementer()).start(testStep).build();
	}

	@Bean
	public TestBean testBean(@Value("${test}") String test) {
		TestBean testBean = new TestBean();
		testBean.test = test;
		return testBean;
	}

}
