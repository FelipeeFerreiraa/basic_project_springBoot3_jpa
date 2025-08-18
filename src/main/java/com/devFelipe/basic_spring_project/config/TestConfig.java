package com.devFelipe.basic_spring_project.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.devFelipe.basic_spring_project.entities.User;
import com.devFelipe.basic_spring_project.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		User u1 = new User(null, "Arnaldo Silva", "arnaldo@gmail.com", "99993333", "123456789");
		User u2 = new User(null, "Beraldo Costa", "beraldo@gmail.com", "2221111", "987654321");
		User u3 = new User(null, "Ceraldo Ferreira", "ceraldo@gmail.com", "33332255", "147258369");

		userRepository.saveAll(Arrays.asList(u1, u2, u3));
	}

}
