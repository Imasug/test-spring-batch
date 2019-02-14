package jp.imanaga.test.spring.batch.service;

import org.springframework.stereotype.Component;

import jp.imanaga.test.spring.batch.base.TestService;

@Component("testService")
public class TestServiceImpl implements TestService {

	@Override
	public void exec() {
		System.out.println("testService");
	}

}
