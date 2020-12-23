package com.autodeli.autodeli.service;

import com.autodeli.autodeli.web.Battery;

import java.util.List;

public interface BatteryService {

  List<Battery> getAllBatteries();

  Battery getBatteryById(String id);

  Battery addBattery(Battery battery);

  Battery updateBattery(Battery battery);

  Battery deleteBattery(String id);

  long getCount();
}
