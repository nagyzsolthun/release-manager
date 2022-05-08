package hu.zsoltn.releasemanager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "deployment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeploymentEntity {
  @Id
  private int systemVersion;
  private String name;
  private int version;
}
