package jp.imanaga.test.spring.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersValidator;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.DefaultJobParametersValidator;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.MethodInvokingTaskletAdapter;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EnableBatchProcessing
@ComponentScan("jp.imanaga.test.spring.batch")
@PropertySource("file:${test}")
public class TestAppConfig {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private ApplicationContext ctx;

	@Autowired
	private Tasklet tasklet;

	@Bean
	public Step testStep() {
		return stepBuilderFactory.get("testStep").tasklet(tasklet).build();
	}

	@Bean
	public Job testJob(Step testStep) {
		return jobBuilderFactory.get("testJob").validator(parametersValidator()).incrementer(new RunIdIncrementer())
				.start(testStep).build();
	}

	@Bean
	public JobParametersValidator parametersValidator() {
		String[] required = new String[] { "jobId" };
		DefaultJobParametersValidator parametersValidator = new DefaultJobParametersValidator();
		parametersValidator.setRequiredKeys(required);
		return parametersValidator;
	}

	@Bean
	@StepScope
	public Tasklet tasklet(@Value("#{jobParameters['jobId']}") String jobId) {
		MethodInvokingTaskletAdapter tasklet = new MethodInvokingTaskletAdapter();
		tasklet.setTargetObject(ctx.getBean(jobId));
		tasklet.setTargetMethod("execute");
		return tasklet;
	}

}
