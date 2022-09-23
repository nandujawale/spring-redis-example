package com.github.jawale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest
class UserServiceTest {

	private static GenericContainer<?> redis;

	@Autowired
	private UserService userService;

	@BeforeAll
	public static void beforeAll() {
		redis = new GenericContainer<>(DockerImageName.parse("redis:5.0.3-alpine"))
				.withExposedPorts(6379);
		redis.start();
		System.setProperty("spring.redis.host", redis.getHost());
		System.setProperty("spring.redis.port", redis.getMappedPort(6379).toString());
	}

	@AfterAll
	public static void afterAll() {
		redis.stop();
	}

	@Test
	void testUpdateStoredUser() {
		userService.save(new User("jawale", 0, 0, 0, 2, 2, 2));

		User user = userService.getUser("jawale");
		assertThat(user).isNotNull();

		user.setLostCounter(user.getLostCounter() + 1);
		userService.save(user);

		user = userService.getUser("jawale");
		assertEquals(1, user.getLostCounter());
	}
}
