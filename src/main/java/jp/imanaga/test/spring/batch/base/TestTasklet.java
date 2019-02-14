package jp.imanaga.test.spring.batch.base;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import jp.imanaga.test.spring.batch.bean.TestBean;

@Component
@StepScope
public class TestTasklet implements Tasklet {

	@Value("${jobParameters['test2']}")
	private String test2;

	@Autowired
	private ApplicationContext ctx;

	@Autowired
	private TestBean testBean;

	public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
		System.out.println("TestTasklet");
		System.out.println(testBean.test);
		ctx.getBean("testService", TestService.class).exec();
		return RepeatStatus.FINISHED;
	}

}
