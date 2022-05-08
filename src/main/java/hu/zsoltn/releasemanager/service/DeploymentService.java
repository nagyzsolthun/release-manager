package hu.zsoltn.releasemanager.service;

import hu.zsoltn.releasemanager.domain.Deployment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeploymentService {

  public List<Deployment> findServices() {
    return List.of(new Deployment("Service A", 1), new Deployment("Service B", 2));
  }

}
