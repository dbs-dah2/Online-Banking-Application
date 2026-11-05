package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.dbs.bank.BankingApplication;

@SpringBootTest(classes = BankingApplication.class, properties = {
		"spring.datasource.url=jdbc:h2:mem:banking-test;DB_CLOSE_DELAY=-1",
		"spring.datasource.driver-class-name=org.h2.Driver",
		"spring.datasource.username=sa",
		"spring.datasource.password=",
		"spring.jpa.hibernate.ddl-auto=create-drop"
})
class BankingApplicationTests {

	@Test
	void contextLoads() {
	}

}
