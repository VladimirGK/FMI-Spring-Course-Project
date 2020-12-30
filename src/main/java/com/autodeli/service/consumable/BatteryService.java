package com.autodeli.service.consumable;

import com.autodeli.web.consumable.Battery;

import java.util.List;

public interface BatteryService {

  List<Battery> getAllBatteries();

  Battery getBatteryById(String id);

  Battery addBattery(Battery battery);

  Battery updateBattery(Battery battery);

  Battery deleteBattery(String id);

  long getCount();
}
