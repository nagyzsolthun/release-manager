package hu.zsoltn.releasemanager.service;

import hu.zsoltn.releasemanager.domain.Deployment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static hu.zsoltn.releasemanager.Constants.DEPLOYMENT_A_1;
import static hu.zsoltn.releasemanager.Constants.DEPLOYMENT_B_2;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeploymentServiceTest {

  private DeploymentService deploymentService;

  @BeforeEach
  public void init() {
    deploymentService = new DeploymentService();
  }

  @Test
  public void testFindServices() {
    final List<Deployment> expected = List.of(DEPLOYMENT_A_1, DEPLOYMENT_B_2);
    final List<Deployment> actual = deploymentService.findServices();
    assertEquals(expected, actual);
  }
}
