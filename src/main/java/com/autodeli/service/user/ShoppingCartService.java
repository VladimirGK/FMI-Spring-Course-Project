package com.autodeli.service.user;

import com.autodeli.web.car.AutoPart;
import com.autodeli.web.consumable.Consumable;

import java.util.Set;

public interface ShoppingCartService {

  Set<AutoPart> getAllAutoParts();

  Set<Consumable> getAllConsumables();

  void addAutoPartToCart(AutoPart autoPart);

  void addConsumableToCart(Consumable consumable);

  void deleteAutoPartFromCart(AutoPart autoPart);

  void deleteConsumableFromCart(Consumable consumable);

  double getTotalPrice();
}
