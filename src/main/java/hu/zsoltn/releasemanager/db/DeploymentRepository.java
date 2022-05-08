package hu.zsoltn.releasemanager.db;

import hu.zsoltn.releasemanager.entity.DeploymentEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface DeploymentRepository extends CrudRepository<DeploymentEntity, Integer> {

  @Query("SELECT d FROM DeploymentEntity AS d " +
      "WHERE d.systemVersion <= :systemVersion " +
      "AND d.systemVersion = (SELECT MAX(dd.systemVersion) " +
          "FROM DeploymentEntity dd " +
          "WHERE dd.systemVersion <= :systemVersion " +
          "AND dd.name = d.name) " +
      "ORDER BY d.name")
  List<DeploymentEntity> findDeploymentsOfSystem(int systemVersion);
  Optional<DeploymentEntity> findFirstByOrderBySystemVersionDesc();
  Optional<DeploymentEntity> findFirstByNameOrderBySystemVersionDesc(String name);
}
