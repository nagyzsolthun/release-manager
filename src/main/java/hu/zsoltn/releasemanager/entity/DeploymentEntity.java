package hu.zsoltn.releasemanager.entity;

import lombok.Data;

@Data
public class DeploymentEntity {
  private final String name;
  private final int version;
  private final int systemVersion;  // system version when deployed
}
