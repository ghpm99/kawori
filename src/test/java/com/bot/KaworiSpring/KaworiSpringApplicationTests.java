package com.bot.KaworiSpring;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class KaworiSpringApplicationTests {

	@Test
	void contextLoads() {
		Date date = new Date(1596153600000l);
		System.out.println(date);
	}

}
