package hu.zsoltn.releasemanager.service;

import hu.zsoltn.releasemanager.dto.DeploymentDto;
import hu.zsoltn.releasemanager.entity.DeploymentEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.function.BinaryOperator.maxBy;

@Service
public class DeploymentService {

  private final List<DeploymentEntity> entities = new LinkedList<>();

  public List<DeploymentDto> findDeployments(final int systemVersion) {
    return entities.stream()
        .filter(entity -> entity.getSystemVersion() <= systemVersion)
        .collect(Collectors.toMap(
            DeploymentEntity::getName,
            Function.identity(),
            maxBy(Comparator.comparing(DeploymentEntity::getSystemVersion))))
        .values()
        .stream()
        .map(entity -> new DeploymentDto(entity.getName(), entity.getVersion()))
        .sorted(Comparator.comparing(DeploymentDto::getName))
        .collect(Collectors.toList());
  }

  public int deploy(final DeploymentDto deployment) {
    final int currentSystemVersion = entities.stream()
        .map(DeploymentEntity::getSystemVersion)
        .max(Integer::compareTo)
        .orElse(0);

    final Optional<DeploymentEntity> existing = entities.stream()
        .filter(entity -> Objects.equals(entity.getName(), deployment.getName()))
        .filter(entity -> entity.getVersion() == deployment.getVersion())
        .findFirst();
    if(existing.isPresent()) {
      return currentSystemVersion;
    }

    final int updatedSystemVersion = currentSystemVersion + 1;
    entities.add(new DeploymentEntity(deployment.getName(), deployment.getVersion(), updatedSystemVersion));
    return updatedSystemVersion;
  }

}
