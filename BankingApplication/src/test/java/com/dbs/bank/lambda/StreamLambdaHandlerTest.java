package com.dbs.bank.lambda;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.amazonaws.serverless.proxy.internal.LambdaContainerHandler;
import com.amazonaws.serverless.proxy.internal.testutils.AwsProxyRequestBuilder;
import com.amazonaws.serverless.proxy.internal.testutils.MockLambdaContext;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;

class StreamLambdaHandlerTest {

	private static StreamLambdaHandler handler;

	@BeforeAll
	static void startHandler() {
		System.setProperty("spring.datasource.url", "jdbc:h2:mem:lambda-test;DB_CLOSE_DELAY=-1");
		System.setProperty("spring.datasource.driver-class-name", "org.h2.Driver");
		System.setProperty("spring.datasource.username", "sa");
		System.setProperty("spring.datasource.password", "");
		System.setProperty("spring.jpa.hibernate.ddl-auto", "create-drop");
		handler = new StreamLambdaHandler();
	}

	@Test
	void createsAndReadsBranchThroughApiGatewayEvents() throws IOException {
		AwsProxyResponse created = invoke(new AwsProxyRequestBuilder("/api/v1/branch", "POST")
				.json()
				.body("{\"branchName\":\"Marina Bay\",\"city\":\"Singapore\",\"state\":\"SG\",\"pincode\":\"018989\"}"));
		assertEquals(200, created.getStatusCode());
		assertTrue(created.getBody().contains("\"branchName\":\"Marina Bay\""), created.getBody());

		AwsProxyResponse list = invoke(new AwsProxyRequestBuilder("/api/v1/branch", "GET")
				.header("Accept", "application/json"));
		assertEquals(200, list.getStatusCode());
		assertTrue(list.getBody().contains("\"branchName\":\"Marina Bay\""), list.getBody());
	}

	@Test
	void returnsNotFoundForUnknownRoute() throws IOException {
		AwsProxyResponse response = invoke(new AwsProxyRequestBuilder("/api/v1/does-not-exist", "GET")
				.header("Accept", "application/json"));
		assertEquals(404, response.getStatusCode());
	}

	@Test
	void servesOpenApiDocument() throws IOException {
		AwsProxyResponse response = invoke(new AwsProxyRequestBuilder("/v3/api-docs", "GET")
				.header("Accept", "application/json"));
		assertEquals(200, response.getStatusCode());
		assertTrue(response.getBody().contains("/api/v1/branch"), response.getBody());
	}

	private AwsProxyResponse invoke(AwsProxyRequestBuilder request) throws IOException {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		handler.handleRequest(request.buildStream(), output, new MockLambdaContext());
		return LambdaContainerHandler.getObjectMapper().readValue(output.toByteArray(), AwsProxyResponse.class);
	}
}
