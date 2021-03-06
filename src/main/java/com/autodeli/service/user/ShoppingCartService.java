package com.autodeli.service.user;

import com.autodeli.web.car.AutoPart;
import com.autodeli.web.consumable.Battery;
import com.autodeli.web.consumable.Oil;
import com.autodeli.web.consumable.Supplement;

import java.util.List;

public interface ShoppingCartService {

  List<AutoPart> getAllAutoParts();

  List<Battery> getAllBatteries();

  List<Oil> getAllOils();

  List<Supplement> getAllSupplements();

  void addAutoPartToCart(AutoPart autoPart);

  void deleteAutoPartFromCart(AutoPart autoPart);

  double getTotalPrice();

  void addBatteryToCart(Battery battery);

  void addOilToCart(Oil oil);

  void addSupplementToCart(Supplement supplement);

  void deleteBatteryFromCart(Battery removed);

  void deleteOilFromCart(Oil removed);

  void deleteSupplementFromCart(Supplement removed);
}
