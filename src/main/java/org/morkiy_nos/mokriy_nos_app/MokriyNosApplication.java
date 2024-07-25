package org.morkiy_nos.mokriy_nos_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class MokriyNosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MokriyNosApplication.class, args);
	}

}
