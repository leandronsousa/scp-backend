package br.com.softplan.scpbackend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(classes = ScpBackendApplication.class)
@TestPropertySource(locations = "classpath:test.properties")
public class ScpBackendApplicationTests {

	@Test
	void contextLoads() {
	}

}
