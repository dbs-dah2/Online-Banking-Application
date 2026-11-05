package com.dbs.bank.lambda;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.spring.SpringBootLambdaContainerHandler;
import com.amazonaws.serverless.proxy.spring.SpringBootProxyHandlerBuilder;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.dbs.bank.BankingApplication;

/**
 * AWS Lambda entry point that serves the existing Spring MVC controllers
 * from API Gateway REST API proxy events.
 */
public class StreamLambdaHandler implements RequestStreamHandler {

	private static final SpringBootLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> HANDLER;

	static {
		try {
			// Spring Boot with JPA can exceed Lambda's 10 second init phase; async init
			// lets startup continue into the first invocation instead of failing.
			HANDLER = new SpringBootProxyHandlerBuilder<AwsProxyRequest>()
					.defaultProxy()
					.asyncInit()
					.springBootApplication(BankingApplication.class)
					.buildAndInitialize();
		} catch (ContainerInitializationException e) {
			throw new RuntimeException("Could not initialize Spring Boot application", e);
		}
	}

	@Override
	public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context)
			throws IOException {
		HANDLER.proxyStream(inputStream, outputStream, context);
	}
}
