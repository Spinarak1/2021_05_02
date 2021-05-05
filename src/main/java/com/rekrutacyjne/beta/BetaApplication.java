package com.rekrutacyjne.beta;

import com.rekrutacyjne.beta.serviceImpl.PostServiceImpl;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

@SpringBootApplication
@EnableAutoConfiguration
public class BetaApplication {

	public static void main(String[] args) throws IOException, JSONException, KeyManagementException, NoSuchAlgorithmException {
		SpringApplication.run(BetaApplication.class, args);
	}

}
