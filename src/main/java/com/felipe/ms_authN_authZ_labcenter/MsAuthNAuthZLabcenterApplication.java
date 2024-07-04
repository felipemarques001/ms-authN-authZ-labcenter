package com.felipe.ms_authN_authZ_labcenter;

import com.felipe.ms_authN_authZ_labcenter.entities.User;
import com.felipe.ms_authN_authZ_labcenter.entities.UserRole;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

@SpringBootApplication
public class MsAuthNAuthZLabcenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsAuthNAuthZLabcenterApplication.class, args);

		var user = new User(UUID.randomUUID(), "admin", "admin", UserRole.ADMIN);
		System.out.println(user.getRole());
	}
}
