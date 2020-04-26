package com.proposta.aceita.crmservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.time.ZoneOffset;
import java.util.TimeZone;

@EnableFeignClients
@SpringBootApplication(exclude = { UserDetailsServiceAutoConfiguration.class })
public class CrmServiceApplication {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone(ZoneOffset.UTC));

		SpringApplication.run(CrmServiceApplication.class, args);
	}

}
