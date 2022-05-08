package hu.zsoltn.releasemanager.service;

import hu.zsoltn.releasemanager.dto.DeploymentDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static hu.zsoltn.releasemanager.Constants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeploymentServiceTest {

  private DeploymentService deploymentService;

  @BeforeEach
  public void init() {
    deploymentService = new DeploymentService();
  }

  @Test
  public void testDeployOne() {
    final int systemVersion = deploymentService.deploy(DEPLOYMENT_DTO_A_1);
    final List<DeploymentDto> deployments = deploymentService.findDeployments(1);
    assertEquals(1, systemVersion);
    assertEquals(List.of(DEPLOYMENT_DTO_A_1), deployments);
  }

  @Test
  public void testDeployTwo() {
    final int systemVersion1 = deploymentService.deploy(DEPLOYMENT_DTO_A_1);
    final int systemVersion2 = deploymentService.deploy(DEPLOYMENT_DTO_B_2);
    final List<DeploymentDto> deployments1 = deploymentService.findDeployments(1);
    final List<DeploymentDto> deployments2 = deploymentService.findDeployments(2);
    assertEquals(1, systemVersion1);
    assertEquals(2, systemVersion2);
    assertEquals(List.of(DEPLOYMENT_DTO_A_1), deployments1);
    assertEquals(List.of(DEPLOYMENT_DTO_A_1, DEPLOYMENT_DTO_B_2), deployments2);
  }

  @Test
  public void testDeployDuplicates() {
    final int systemVersion1 = deploymentService.deploy(DEPLOYMENT_DTO_A_1);
    final int systemVersion2 = deploymentService.deploy(DEPLOYMENT_DTO_A_1);
    final List<DeploymentDto> deployments = deploymentService.findDeployments(1);
    assertEquals(1, systemVersion1);
    assertEquals(1, systemVersion2);
    assertEquals(List.of(DEPLOYMENT_DTO_A_1), deployments);
  }

  @Test
  public void testDeployDowngrade() {
    final int systemVersion1 = deploymentService.deploy(DEPLOYMENT_DTO_A_2);
    final int systemVersion2 = deploymentService.deploy(DEPLOYMENT_DTO_A_1);
    final List<DeploymentDto> deployments1 = deploymentService.findDeployments(1);
    final List<DeploymentDto> deployments2 = deploymentService.findDeployments(2);
    assertEquals(1, systemVersion1);
    assertEquals(2, systemVersion2);
    assertEquals(List.of(DEPLOYMENT_DTO_A_2), deployments1);
    assertEquals(List.of(DEPLOYMENT_DTO_A_1), deployments2);
  }
}
