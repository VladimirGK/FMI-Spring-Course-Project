package com.autodeli.autodeli.controller;

import com.autodeli.autodeli.exception.InvalidEntityDataException;
import com.autodeli.autodeli.service.BatteryService;
import com.autodeli.autodeli.web.Battery;
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
@RequestMapping("/api/battery")
public class BatteryController {

  private final BatteryService batteryService;

  @Autowired
  public BatteryController(BatteryService batteryService) {
    this.batteryService = batteryService;
  }

  @GetMapping
  public List<Battery> getAllBatteries() {
    return batteryService.getAllBatteries();
  }

  @GetMapping("/{id:^[A-Fa-f0-9]{24}$}")
  public Battery getBatteryById(@PathVariable("id") String id) {
    return batteryService.getBatteryById(id);
  }

  @PostMapping
  public ResponseEntity<Battery> addBatteries(@Valid @RequestBody Battery battery, Errors errors) {
    if (errors.hasErrors()) {
      throw new InvalidEntityDataException("Invalid Battery data: ", getErrors(errors));
    }
    Battery created = batteryService.addBattery(battery);
    return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().pathSegment("{id}").buildAndExpand(created.getId()).toUri())
        .body(created);
  }

  @PutMapping("/{id:^[A-Fa-f0-9]{24}$}")
  public Battery updateBattery(@PathVariable("id") String id, @Valid @RequestBody Battery battery, Errors errors) {
    if (errors.hasErrors()) {
      throw new InvalidEntityDataException("Invalid Battery data: ", getErrors(errors));
    }
    if (!id.equals(battery.getId())) {
      throw new InvalidEntityDataException("Battery ID:%s in the URL differs from ID:%s in the body.");
    }
    return batteryService.updateBattery(battery);
  }

  @DeleteMapping("/{id:^[A-Fa-f0-9]{24}$}")
  public Battery deleteBattery(@PathVariable("id") String id) {
    return batteryService.deleteBattery(id);
  }
}
