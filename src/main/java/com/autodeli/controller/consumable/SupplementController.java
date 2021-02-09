package com.autodeli.controller.consumable;

import com.autodeli.exception.InvalidEntityDataException;
import com.autodeli.service.consumable.SupplementService;
import com.autodeli.web.consumable.Supplement;
import com.autodeli.utils.ErrorHandlingUtils;
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
@RequestMapping("/api/supplement")
public class SupplementController {

  private final SupplementService supplementService;

  @Autowired
  public SupplementController(SupplementService supplementService) {
    this.supplementService = supplementService;
  }

  @GetMapping
  public List<Supplement> getAllSupplements() {
    return supplementService.getAllSupplements();
  }

  @GetMapping("/{id:^[A-Fa-f0-9]{24}$}")
  public Supplement getSupplementById(@PathVariable("id") String id) {
    return supplementService.getSupplementById(id);
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping
  public ResponseEntity<Supplement> addSupplements(@Valid @RequestBody Supplement supplement, Errors errors) {
    if (errors.hasErrors()) {
      throw new InvalidEntityDataException("Invalid supplement data: ", ErrorHandlingUtils.getErrors(errors));
    }
    Supplement created = supplementService.addSupplement(supplement);
    return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().pathSegment("{id}").buildAndExpand(created.getId()).toUri())
        .body(created);
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PutMapping("/{id:^[A-Fa-f0-9]{24}$}")
  public Supplement updateSupplement(@PathVariable("id") String id, @Valid @RequestBody Supplement supplement, Errors errors) {
    if (errors.hasErrors()) {
      throw new InvalidEntityDataException("Invalid supplement data: ", ErrorHandlingUtils.getErrors(errors));
    }
    if (!id.equals(supplement.getId())) {
      throw new InvalidEntityDataException("Supplement ID:%s in the URL differs from ID:%s in the body.");
    }
    return supplementService.updateSupplement(supplement);
  }

  @PreAuthorize("hasRole('ADMIN')")
  @DeleteMapping("/{id:^[A-Fa-f0-9]{24}$}")
  public Supplement deleteSupplement(@PathVariable("id") String id) {
    return supplementService.deleteSupplement(id);
  }
}
