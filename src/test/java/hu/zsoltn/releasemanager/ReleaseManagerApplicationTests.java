package hu.zsoltn.releasemanager;

import hu.zsoltn.releasemanager.domain.Deployment;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;


import java.util.Arrays;
import java.util.List;

import static hu.zsoltn.releasemanager.Constants.DEPLOYMENT_A_1;
import static hu.zsoltn.releasemanager.Constants.DEPLOYMENT_B_2;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;

@SpringBootTest(webEnvironment = DEFINED_PORT)
class ReleaseManagerApplicationTests {

	@LocalServerPort
	private int port;

	private TestRestTemplate restTemplate = new TestRestTemplate();

	@Test
	void testGetServices() {
		final String url = "http://localhost:" + port + "/services";

		final List<Deployment> expected = List.of(DEPLOYMENT_A_1, DEPLOYMENT_B_2);
		final List<Deployment> actual = Arrays.asList(restTemplate.getForObject(url, Deployment[].class));
		assertEquals(expected, actual);
	}

}
