package hu.zsoltn.releasemanager.repository;

import hu.zsoltn.releasemanager.db.DeploymentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static hu.zsoltn.releasemanager.Constants.*;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;

@SpringBootTest(webEnvironment = DEFINED_PORT)
public class DeploymentRepositoryTest {

  @Autowired
  private DeploymentRepository deploymentRepository;

  @AfterEach
  public void clean() {
    deploymentRepository.deleteAll();
  }

  @Test
  public void testFindSingleEntity() {
    deploymentRepository.save(DEPLOYMENT_ENTITY_1_A_1);
    deploymentRepository.save(DEPLOYMENT_ENTITY_2_A_2);
    assertEquals(of(DEPLOYMENT_ENTITY_2_A_2), deploymentRepository.findFirstByOrderBySystemVersionDesc());
    assertEquals(of(DEPLOYMENT_ENTITY_2_A_2), deploymentRepository.findFirstByNameOrderBySystemVersionDesc("Service A"));
    assertEquals(empty(), deploymentRepository.findFirstByNameOrderBySystemVersionDesc("Service B"));
  }

  @Test
  public void testFindDeploymentsOfSystem() {
    deploymentRepository.save(DEPLOYMENT_ENTITY_1_A_1);
    deploymentRepository.save(DEPLOYMENT_ENTITY_2_A_2);
    deploymentRepository.save(DEPLOYMENT_ENTITY_3_B_1);
    deploymentRepository.save(DEPLOYMENT_ENTITY_4_B_2);

    assertEquals(List.of(DEPLOYMENT_ENTITY_1_A_1),
        deploymentRepository.findDeploymentsOfSystem(1));
    assertEquals(List.of(DEPLOYMENT_ENTITY_2_A_2),
        deploymentRepository.findDeploymentsOfSystem(2));
    assertEquals(List.of(DEPLOYMENT_ENTITY_2_A_2, DEPLOYMENT_ENTITY_3_B_1),
        deploymentRepository.findDeploymentsOfSystem(3));
    assertEquals(List.of(DEPLOYMENT_ENTITY_2_A_2, DEPLOYMENT_ENTITY_4_B_2),
        deploymentRepository.findDeploymentsOfSystem(4));
  }

}
