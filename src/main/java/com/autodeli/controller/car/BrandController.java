package com.autodeli.controller.car;

import com.autodeli.exception.InvalidEntityDataException;
import com.autodeli.service.car.AutoPartService;
import com.autodeli.service.car.BrandService;
import com.autodeli.service.car.EngineService;
import com.autodeli.service.car.ModelService;
import com.autodeli.utils.ErrorHandlingUtils;
import com.autodeli.web.car.AutoPart;
import com.autodeli.web.car.Brand;
import com.autodeli.web.car.Engine;
import com.autodeli.web.car.Model;
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
@RequestMapping("/api/brand")
public class BrandController {

  private final BrandService brandService;
  private final ModelService modelService;
  private final EngineService engineService;
  private final AutoPartService autoPartService;

  @Autowired
  public BrandController(BrandService brandService, ModelService modelService, EngineService engineService, AutoPartService autoPartService) {
    this.brandService = brandService;
    this.modelService = modelService;
    this.engineService = engineService;
    this.autoPartService = autoPartService;
  }

  @GetMapping
  public List<Brand> getAllBrands() {
    return brandService.getAllBrands();
  }

  @GetMapping("/{id:^[A-Fa-f0-9]{24}$}")
  public Brand getBrandById(@PathVariable("id") String id) {
    return brandService.getBrandById(id);
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping
  public ResponseEntity<Brand> addBrand(@Valid @RequestBody Brand brand, Errors errors) {
    if (errors.hasErrors()) {
      throw new InvalidEntityDataException("Invalid Brand data: ", ErrorHandlingUtils.getErrors(errors));
    }
    Brand created = brandService.addBrand(brand);
    return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().pathSegment("{id}").buildAndExpand(created.getId()).toUri())
        .body(created);
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PutMapping("/{id:^[A-Fa-f0-9]{24}$}")
  public Brand updateBrand(@PathVariable("id") String id, @Valid @RequestBody Brand brand, Errors errors) {
    if (errors.hasErrors()) {
      throw new InvalidEntityDataException("Invalid Brand data: ", ErrorHandlingUtils.getErrors(errors));
    }
    if (!id.equals(brand.getId())) {
      throw new InvalidEntityDataException("Brand ID:%s in the URL differs from ID:%s in the body.");
    }
    return brandService.updateBrand(brand);
  }

  @PreAuthorize("hasRole('ADMIN')")
  @DeleteMapping("/{id:^[A-Fa-f0-9]{24}$}")
  public Brand deleteBrand(@PathVariable("id") String id) {
    return brandService.deleteBrand(id);
  }

  @GetMapping("/{brand}/model")
  public List<Model> getAllModelsByBrand(@PathVariable("brand") String brandName) {
    return modelService.getAllModelsByBrandName(brandName);
  }

  @GetMapping("/{brand}/model/{model}")
  public List<Engine> getAllEnginesByBrandAndModel(@PathVariable("brand") String brandName, @PathVariable("model") String modelName) {
    return engineService.getAllEnginesByBrandAndModelName(brandName, modelName);
  }

  @GetMapping("/{brand}/model/{model}/engine/{engine}")
  public List<AutoPart> getAllSpecificAutoParts(@PathVariable("brand") String brandName, @PathVariable("model") String modelName,
      @PathVariable("engine") String engineName) {
    return autoPartService.getAllSpecifiedAutoParts(brandName, modelName, engineName);
  }



}
