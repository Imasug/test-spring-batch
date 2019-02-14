package jp.imanaga.test.spring.batch.base;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestLauncher {

	@Autowired
	private Job job;

	@Autowired
	private JobLauncher jobLauncher;

	public void exec() {
		JobParameters jobParameters = new JobParametersBuilder().addString("jobId", "testTasklet")
				.addString("option", "option").toJobParameters();
		try {
			jobLauncher.run(job, jobParameters);
		} catch (JobParametersInvalidException e1) {
			e1.printStackTrace();
		} catch (Exception others) {
			others.printStackTrace();
		}
	}

}
