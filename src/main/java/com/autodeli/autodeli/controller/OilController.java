package com.autodeli.autodeli.controller;

import com.autodeli.autodeli.exception.InvalidEntityDataException;
import com.autodeli.autodeli.service.OilService;
import com.autodeli.autodeli.web.Oil;
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

import static com.autodeli.autodeli.utils.ErrorHandlingUtils.getErrors;

@RestController
@RequestMapping("/api/oil")
public class OilController {

  private final OilService oilService;

  @Autowired
  public OilController(OilService oilService) {
    this.oilService = oilService;
  }

  @GetMapping
  public List<Oil> getAllOils() {
    return oilService.getAllOils();
  }

  @GetMapping("/{id:^[A-Fa-f0-9]{24}$}")
  public Oil getOilById(@PathVariable("id") String id) {
    return oilService.getOilById(id);
  }

  @PostMapping
  public ResponseEntity<Oil> addOils(@Valid @RequestBody Oil oil, Errors errors) {
    if (errors.hasErrors()) {
      throw new InvalidEntityDataException("Invalid Oil data: ", getErrors(errors));
    }
    Oil created = oilService.addOil(oil);
    return ResponseEntity.created(
        ServletUriComponentsBuilder.fromCurrentRequest()
            .pathSegment("{id}").buildAndExpand(created.getId()).toUri()
    ).body(created);
  }

  @PutMapping("/{id:^[A-Fa-f0-9]{24}$}")
  public Oil updateOil(@PathVariable("id") String id, @Valid @RequestBody Oil oil, Errors errors) {
    if (errors.hasErrors()) {
      throw new InvalidEntityDataException("Invalid Oil data: ", getErrors(errors));
    }
    if (!id.equals(oil.getId())) {
      throw new InvalidEntityDataException("Oil ID:%s in the URL differs from ID:%s in the body.");
    }
    return oilService.updateOil(oil);
  }

  @DeleteMapping("/{id:^[A-Fa-f0-9]{24}$}")
  public Oil deleteOil(@PathVariable("id") String id) {
    return oilService.deleteOil(id);
  }
}