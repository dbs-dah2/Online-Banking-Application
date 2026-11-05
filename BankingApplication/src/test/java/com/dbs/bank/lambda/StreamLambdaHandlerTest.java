package com.dbs.bank.lambda;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.serverless.proxy.internal.LambdaContainerHandler;
import com.amazonaws.serverless.proxy.internal.testutils.AwsProxyRequestBuilder;
import com.amazonaws.serverless.proxy.internal.testutils.MockLambdaContext;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;

public class StreamLambdaHandlerTest {

	private static StreamLambdaHandler handler;

	@BeforeClass
	public static void startHandler() {
		System.setProperty("spring.datasource.url", "jdbc:h2:mem:lambda-test;DB_CLOSE_DELAY=-1");
		System.setProperty("spring.datasource.driver-class-name", "org.h2.Driver");
		System.setProperty("spring.datasource.username", "sa");
		System.setProperty("spring.datasource.password", "");
		System.setProperty("spring.jpa.database-platform", "org.hibernate.dialect.H2Dialect");
		System.setProperty("spring.jpa.hibernate.ddl-auto", "create-drop");
		handler = new StreamLambdaHandler();
	}

	@Test
	public void createsAndReadsBranchThroughApiGatewayEvents() throws IOException {
		AwsProxyResponse created = invoke(new AwsProxyRequestBuilder("/api/v1/branch", "POST")
				.json()
				.body("{\"branchName\":\"Marina Bay\",\"city\":\"Singapore\",\"state\":\"SG\",\"pincode\":\"018989\"}"));
		assertEquals(200, created.getStatusCode());
		assertTrue(created.getBody(), created.getBody().contains("\"branchName\":\"Marina Bay\""));

		AwsProxyResponse list = invoke(new AwsProxyRequestBuilder("/api/v1/branch", "GET")
				.header("Accept", "application/json"));
		assertEquals(200, list.getStatusCode());
		assertTrue(list.getBody(), list.getBody().contains("\"branchName\":\"Marina Bay\""));
	}

	@Test
	public void returnsNotFoundForUnknownRoute() throws IOException {
		AwsProxyResponse response = invoke(new AwsProxyRequestBuilder("/api/v1/does-not-exist", "GET")
				.header("Accept", "application/json"));
		assertEquals(404, response.getStatusCode());
	}

	private AwsProxyResponse invoke(AwsProxyRequestBuilder request) throws IOException {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		handler.handleRequest(request.buildStream(), output, new MockLambdaContext());
		return LambdaContainerHandler.getObjectMapper().readValue(output.toByteArray(), AwsProxyResponse.class);
	}
}
