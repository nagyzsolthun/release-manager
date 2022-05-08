package hu.zsoltn.releasemanager;

import hu.zsoltn.releasemanager.dto.DeploymentDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;


import java.util.Arrays;
import java.util.List;

import static hu.zsoltn.releasemanager.Constants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;

@SpringBootTest(webEnvironment = DEFINED_PORT)
class ReleaseManagerApplicationTests {

	@LocalServerPort
	private int port;

	private TestRestTemplate restTemplate = new TestRestTemplate();

	@Test
	void testExample() {
		final String deployUrl = "http://localhost:" + port + "/deploy";
		final String deploymentsUrlTemplate = "http://localhost:" + port + "/services?systemVersion=%s";
		final int systemVersion1 = restTemplate.postForEntity(deployUrl, DEPLOYMENT_DTO_A_1, Integer.class).getBody();
		final int systemVersion2 = restTemplate.postForEntity(deployUrl, DEPLOYMENT_DTO_B_1, Integer.class).getBody();
		final int systemVersion3 = restTemplate.postForEntity(deployUrl, DEPLOYMENT_DTO_A_2, Integer.class).getBody();
		final int systemVersion4 = restTemplate.postForEntity(deployUrl, DEPLOYMENT_DTO_B_1, Integer.class).getBody();

		final List<DeploymentDto> deployments1 = Arrays.asList(
				restTemplate.getForObject(String.format(deploymentsUrlTemplate, 2), DeploymentDto[].class));
		final List<DeploymentDto> deployments2 = Arrays.asList(
				restTemplate.getForObject(String.format(deploymentsUrlTemplate, 3), DeploymentDto[].class));
		assertEquals(1, systemVersion1);
		assertEquals(2, systemVersion2);
		assertEquals(3, systemVersion3);
		assertEquals(3, systemVersion4);
		assertEquals(List.of(DEPLOYMENT_DTO_A_1, DEPLOYMENT_DTO_B_1), deployments1);
		assertEquals(List.of(DEPLOYMENT_DTO_A_2, DEPLOYMENT_DTO_B_1), deployments2);
	}

}
