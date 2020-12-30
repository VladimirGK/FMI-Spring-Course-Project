package com.autodeli.controller.consumable;

import com.autodeli.exception.InvalidEntityDataException;
import com.autodeli.service.consumable.BatteryService;
import com.autodeli.web.consumable.Battery;
import com.autodeli.utils.ErrorHandlingUtils;
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
  public ResponseEntity<Battery> addBattery(@Valid @RequestBody Battery battery, Errors errors) {
    if (errors.hasErrors()) {
      throw new InvalidEntityDataException("Invalid Battery data: ", ErrorHandlingUtils.getErrors(errors));
    }
    Battery created = batteryService.addBattery(battery);
    return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().pathSegment("{id}").buildAndExpand(created.getId()).toUri())
        .body(created);
  }

  @PutMapping("/{id:^[A-Fa-f0-9]{24}$}")
  public Battery updateBattery(@PathVariable("id") String id, @Valid @RequestBody Battery battery, Errors errors) {
    if (errors.hasErrors()) {
      throw new InvalidEntityDataException("Invalid Battery data: ", ErrorHandlingUtils.getErrors(errors));
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
