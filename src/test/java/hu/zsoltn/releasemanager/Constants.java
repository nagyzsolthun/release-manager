package hu.zsoltn.releasemanager;

import hu.zsoltn.releasemanager.dto.DeploymentDto;
import hu.zsoltn.releasemanager.entity.DeploymentEntity;

public class Constants {
  public static final DeploymentDto DEPLOYMENT_DTO_A_1 = new DeploymentDto("Service A", 1);
  public static final DeploymentDto DEPLOYMENT_DTO_A_2 = new DeploymentDto("Service A", 2);
  public static final DeploymentDto DEPLOYMENT_DTO_B_1 = new DeploymentDto("Service B", 1);
  public static final DeploymentDto DEPLOYMENT_DTO_B_2 = new DeploymentDto("Service B", 2);

  public static final DeploymentEntity DEPLOYMENT_ENTITY_1_A_1 = new DeploymentEntity(1, "Service A", 1);
  public static final DeploymentEntity DEPLOYMENT_ENTITY_2_A_2 = new DeploymentEntity(2, "Service A", 2);
  public static final DeploymentEntity DEPLOYMENT_ENTITY_3_B_1 = new DeploymentEntity(3, "Service B", 1);
  public static final DeploymentEntity DEPLOYMENT_ENTITY_4_B_2 = new DeploymentEntity(4, "Service B", 2);

}
