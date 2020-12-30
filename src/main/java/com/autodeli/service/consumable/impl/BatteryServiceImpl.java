package com.autodeli.service.consumable.impl;

import com.autodeli.exception.EntityNotFoundException;
import com.autodeli.repository.consumable.BatteryRepository;
import com.autodeli.service.consumable.BatteryService;
import com.autodeli.web.consumable.Battery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BatteryServiceImpl implements BatteryService {

  private final BatteryRepository batteryRepository;

  @Autowired
  public BatteryServiceImpl(BatteryRepository batteryRepository) {
    this.batteryRepository = batteryRepository;
  }

  @Override
  public List<Battery> getAllBatteries() {
    return batteryRepository.findAll();
  }

  @Override
  public Battery getBatteryById(String id) {
    return batteryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("Battery with id=%s not found", id)));
  }

  @Override
  public Battery addBattery(Battery battery) {
    return batteryRepository.insert(battery);
  }

  @Override
  public Battery updateBattery(Battery battery) {
    battery.setModified(LocalDateTime.now());
    return batteryRepository.save(battery);
  }

  @Override
  public Battery deleteBattery(String id) {
    Battery removed = getBatteryById(id);
    batteryRepository.delete(removed);
    return removed;
  }

  @Override
  public long getCount() {
    return batteryRepository.count();
  }
}
