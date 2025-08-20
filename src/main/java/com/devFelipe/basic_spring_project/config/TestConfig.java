package com.devFelipe.basic_spring_project.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.devFelipe.basic_spring_project.entities.Order;
import com.devFelipe.basic_spring_project.entities.User;
import com.devFelipe.basic_spring_project.entities.enums.OrderStatus;
import com.devFelipe.basic_spring_project.repositories.OrderRepository;
import com.devFelipe.basic_spring_project.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Override
	public void run(String... args) throws Exception {
		User u1 = new User(null, "Arnaldo Silva", "arnaldo@gmail.com", "99993333", "123456789");
		User u2 = new User(null, "Beraldo Costa", "beraldo@gmail.com", "2221111", "987654321");
		User u3 = new User(null, "Ceraldo Ferreira", "ceraldo@gmail.com", "33332255", "147258369");

		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), u1, OrderStatus.PAID);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), u2, OrderStatus.WAITING_PAYMENT);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), u1, OrderStatus.WAITING_PAYMENT);

		userRepository.saveAll(Arrays.asList(u1, u2, u3));

		orderRepository.saveAll(Arrays.asList(o1, o2, o3));
	}

}
