package com.autodeli.controller.car;

import com.autodeli.exception.InvalidEntityDataException;
import com.autodeli.service.car.EngineService;
import com.autodeli.utils.ErrorHandlingUtils;
import com.autodeli.web.car.Engine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/engine")
public class EngineController {

  private final EngineService engineService;


  @Autowired
  public EngineController(EngineService EngineService) {
    this.engineService = EngineService;
  }

  @GetMapping
  public List<Engine> getAllEngines() {
    return engineService.getAllEngines();
  }

  @GetMapping("/{id:^[A-Fa-f0-9]{24}$}")
  public Engine getEngineById(@PathVariable("id") String id) {
    return engineService.getEngineById(id);
  }

  @PostMapping
  public ResponseEntity<Engine> addEngine(@Valid @RequestBody Engine Engine, Errors errors) {
    if (errors.hasErrors()) {
      throw new InvalidEntityDataException("Invalid Engine data: ", ErrorHandlingUtils.getErrors(errors));
    }
    Engine created = engineService.addEngine(Engine);
    return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().pathSegment("{id}").buildAndExpand(created.getId()).toUri())
        .body(created);
  }

  @PutMapping("/{id:^[A-Fa-f0-9]{24}$}")
  public Engine updateEngine(@PathVariable("id") String id, @Valid @RequestBody Engine Engine, Errors errors) {
    if (errors.hasErrors()) {
      throw new InvalidEntityDataException("Invalid Engine data: ", ErrorHandlingUtils.getErrors(errors));
    }
    if (!id.equals(Engine.getId())) {
      throw new InvalidEntityDataException("Engine ID:%s in the URL differs from ID:%s in the body.");
    }
    return engineService.updateEngine(Engine);
  }

  @DeleteMapping("/{id:^[A-Fa-f0-9]{24}$}")
  public Engine deleteEngine(@PathVariable("id") String id) {
    return engineService.deleteEngine(id);
  }
}
