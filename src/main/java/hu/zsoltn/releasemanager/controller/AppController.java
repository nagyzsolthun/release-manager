package hu.zsoltn.releasemanager.controller;

import hu.zsoltn.releasemanager.domain.Deployment;
import hu.zsoltn.releasemanager.service.DeploymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AppController {

  private final DeploymentService deploymentService;

  @GetMapping("/services")
  public List<Deployment> getServices() {
    return deploymentService.findServices();
  }

}
