package com.tms.mocks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.tms.mocks.property.FileStorageProperties;

@SpringBootApplication
@EnableConfigurationProperties({
	FileStorageProperties.class
})
public class MocktestApplication {

	public static void main(String[] args) {
		SpringApplication.run(MocktestApplication.class, args);
	}

}
