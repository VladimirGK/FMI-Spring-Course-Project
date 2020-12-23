package com.autodeli.autodeli.init;

import com.autodeli.autodeli.service.BatteryService;
import com.autodeli.autodeli.service.OilService;
import com.autodeli.autodeli.web.Battery;
import com.autodeli.autodeli.web.Oil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

  private final BatteryService batteryService;
  private final OilService oilService;

  @Autowired
  public DataInitializer(BatteryService batteryService, OilService oilService) {
    this.batteryService = batteryService;
    this.oilService = oilService;
  }

  @Override
  public void run(String... args) throws Exception {
    Battery battery = new Battery("Castrol 5W 40","Castrol",78,"htpps://www.photo.com/photo.png");
    batteryService.addBattery(battery);
    Oil oil = new Oil("Castrol 5W 40","Castrol",78,"htpps://www.photo.com/photo.png");
    oilService.addOil(oil);
  }
}
