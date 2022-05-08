package hu.zsoltn.releasemanager.controller;

import hu.zsoltn.releasemanager.dto.DeploymentDto;
import hu.zsoltn.releasemanager.service.DeploymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AppController {

  private final DeploymentService deploymentService;

  @GetMapping("/services")
  public List<DeploymentDto> getServices(final @RequestParam int systemVersion) {
    return deploymentService.findDeployments(systemVersion);
  }

  @PostMapping("/deploy")
  public int postDeploy(final @RequestBody DeploymentDto deployment) {
    return deploymentService.deploy(deployment);
  }

}
