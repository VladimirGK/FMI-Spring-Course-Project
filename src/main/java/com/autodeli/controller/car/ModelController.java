package com.autodeli.controller.car;

import com.autodeli.exception.InvalidEntityDataException;
import com.autodeli.service.car.ModelService;
import com.autodeli.utils.ErrorHandlingUtils;
import com.autodeli.web.car.Model;
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
@RequestMapping("/api/model")
public class ModelController {

  private final ModelService modelService;

  @Autowired
  public ModelController(ModelService modelService) {
    this.modelService = modelService;
  }

  @GetMapping
  public List<Model> getAllModels() {
    return modelService.getAllModels();
  }

  @GetMapping("/{id:^[A-Fa-f0-9]{24}$}")
  public Model getModelById(@PathVariable("id") String id) {
    return modelService.getModelById(id);
  }

  @PostMapping
  public ResponseEntity<Model> addModel(@Valid @RequestBody Model model, Errors errors) {
    if (errors.hasErrors()) {
      throw new InvalidEntityDataException("Invalid Model data: ", ErrorHandlingUtils.getErrors(errors));
    }
    Model created = modelService.addModel(model);
    return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().pathSegment("{id}").buildAndExpand(created.getId()).toUri())
        .body(created);
  }

  @PutMapping("/{id:^[A-Fa-f0-9]{24}$}")
  public Model updateModel(@PathVariable("id") String id, @Valid @RequestBody Model model, Errors errors) {
    if (errors.hasErrors()) {
      throw new InvalidEntityDataException("Invalid Model data: ", ErrorHandlingUtils.getErrors(errors));
    }
    if (!id.equals(model.getId())) {
      throw new InvalidEntityDataException("Model ID:%s in the URL differs from ID:%s in the body.");
    }
    return modelService.updateModel(model);
  }

  @DeleteMapping("/{id:^[A-Fa-f0-9]{24}$}")
  public Model deleteModel(@PathVariable("id") String id) {
    return modelService.deleteModel(id);
  }
}
