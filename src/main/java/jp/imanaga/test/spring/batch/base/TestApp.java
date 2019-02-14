package jp.imanaga.test.spring.batch.base;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import jp.imanaga.test.spring.batch.config.TestAppConfig;

public class TestApp {

	public static void main(String[] args) {
		try (ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(TestAppConfig.class)) {
			ctx.getBean(TestLauncher.class).exec();
		}
	}

}
