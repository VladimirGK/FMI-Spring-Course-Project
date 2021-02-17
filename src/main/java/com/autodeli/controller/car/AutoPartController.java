package com.autodeli.controller.car;

import com.autodeli.exception.InvalidEntityDataException;
import com.autodeli.service.car.AutoPartService;
import com.autodeli.service.car.BrandService;
import com.autodeli.service.car.EngineService;
import com.autodeli.service.car.ModelService;
import com.autodeli.utils.ErrorHandlingUtils;
import com.autodeli.web.car.AutoPart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/api/autopart")
public class AutoPartController {
  
  private final AutoPartService autoPartService;
  private final BrandService brandService;
  private final ModelService modelService;
  private final EngineService engineService;

  @Autowired
  public AutoPartController(AutoPartService autoPartService, BrandService brandService, ModelService modelService, EngineService engineService) {
    this.autoPartService = autoPartService;
    this.brandService = brandService;
    this.modelService = modelService;
    this.engineService = engineService;
  }

  @GetMapping
  public List<AutoPart> getAllAutoParts() {
    return autoPartService.getAllAutoParts();
  }

  @GetMapping("/{id:^[A-Fa-f0-9]{24}$}")
  public AutoPart getAutoPartById(@PathVariable("id") String id) {
    return autoPartService.getAutoPartById(id);
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping
  public ResponseEntity<AutoPart> addAutoPart(@Valid @RequestBody AutoPart autoPart, Errors errors) {
    if (errors.hasErrors() || modelService.getModelByName(autoPart.getModelName()) == null || brandService.getBrandByName(autoPart.getBrandName()) == null
            || engineService.getEngineByName(autoPart.getEngineName()) == null) {
      throw new InvalidEntityDataException("Invalid AutoPart data: ", ErrorHandlingUtils.getErrors(errors));
    }

    AutoPart created = autoPartService.addAutoPart(autoPart);
    return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().pathSegment("{id}").buildAndExpand(created.getId()).toUri())
        .body(created);
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PutMapping("/{id:^[A-Fa-f0-9]{24}$}")
  public AutoPart updateAutoPart(@PathVariable("id") String id, @Valid @RequestBody AutoPart autoPart, Errors errors) {
    if (errors.hasErrors()) {
      throw new InvalidEntityDataException("Invalid AutoPart data: ", ErrorHandlingUtils.getErrors(errors));
    }
    if (!id.equals(autoPart.getId())) {
      throw new InvalidEntityDataException("AutoPart ID:%s in the URL differs from ID:%s in the body.");
    }
    return autoPartService.updateAutoPart(autoPart);
  }

  @PreAuthorize("hasRole('ADMIN')")
  @DeleteMapping("/{id:^[A-Fa-f0-9]{24}$}")
  public AutoPart deleteAutoPart(@PathVariable("id") String id) {
    return autoPartService.deleteAutoPart(id);
  }
}
