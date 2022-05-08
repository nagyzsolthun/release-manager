package hu.zsoltn.releasemanager.service;

import hu.zsoltn.releasemanager.db.DeploymentRepository;
import hu.zsoltn.releasemanager.dto.DeploymentDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static hu.zsoltn.releasemanager.Constants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DeploymentServiceTest {

  @Mock
  private DeploymentRepository deploymentRepository;

  private DeploymentService deploymentService;

  @BeforeEach
  public void init() {
    deploymentService = new DeploymentService(deploymentRepository);
  }

  @Test
  public void testDeployExisting() {
    when(deploymentRepository.findFirstByOrderBySystemVersionDesc())
        .thenReturn(Optional.of(DEPLOYMENT_ENTITY_1_A_1));
    when(deploymentRepository.findFirstByNameOrderBySystemVersionDesc("Service A"))
        .thenReturn(Optional.of(DEPLOYMENT_ENTITY_1_A_1));
    final int systemVersion = deploymentService.deploy(DEPLOYMENT_DTO_A_1);
    assertEquals(1, systemVersion);
  }

  @Test
  public void testDeployUpdate() {
    when(deploymentRepository.findFirstByOrderBySystemVersionDesc())
        .thenReturn(Optional.of(DEPLOYMENT_ENTITY_1_A_1));
    when(deploymentRepository.findFirstByNameOrderBySystemVersionDesc("Service A"))
        .thenReturn(Optional.of(DEPLOYMENT_ENTITY_1_A_1));
    when(deploymentRepository.save(DEPLOYMENT_ENTITY_2_A_2))
        .thenReturn(DEPLOYMENT_ENTITY_2_A_2);
    final int systemVersion = deploymentService.deploy(DEPLOYMENT_DTO_A_2);
    assertEquals(2, systemVersion);
  }

  @Test
  public void testFindDeployments() {
    when(deploymentRepository.findDeploymentsOfSystem(4))
        .thenReturn(List.of(DEPLOYMENT_ENTITY_1_A_1, DEPLOYMENT_ENTITY_4_B_2));
    final List<DeploymentDto> dtos = deploymentService.findDeployments(4);
    assertEquals(List.of(DEPLOYMENT_DTO_A_1, DEPLOYMENT_DTO_B_2), dtos);
  }

}
