package hu.zsoltn.releasemanager.service;

import hu.zsoltn.releasemanager.db.DeploymentRepository;
import hu.zsoltn.releasemanager.dto.DeploymentDto;
import hu.zsoltn.releasemanager.entity.DeploymentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeploymentService {

  private final DeploymentRepository deploymentRepository;

  public List<DeploymentDto> findDeployments(final int systemVersion) {
    return deploymentRepository
        .findDeploymentsOfSystem(systemVersion)
        .stream()
        .map(DeploymentService::toDto)
        .collect(Collectors.toList());
  }

  public int deploy(final DeploymentDto deployment) {
    final int currentSystemVersion = deploymentRepository.findFirstByOrderBySystemVersionDesc()
        .map(DeploymentEntity::getSystemVersion)
        .orElse(0);

    final String name = deployment.getName();
    final int version = deployment.getVersion();
    final Optional<DeploymentEntity> existing = deploymentRepository
        .findFirstByNameOrderBySystemVersionDesc(name);
    if(existing.isPresent() && existing.get().getVersion() == version) {
      return currentSystemVersion;
    }

    final DeploymentEntity entity = deploymentRepository.save(toEntity(deployment, currentSystemVersion + 1));
    return entity.getSystemVersion();
  }

  private static DeploymentDto toDto(final DeploymentEntity entity) {
    return new DeploymentDto(entity.getName(), entity.getVersion());
  }

  private static DeploymentEntity toEntity(final DeploymentDto dto, final int systemVersion) {
    return new DeploymentEntity(systemVersion, dto.getName(), dto.getVersion());
  }

}
