package com.autodeli.web;

import com.autodeli.web.car.AutoPart;
import com.autodeli.web.consumable.Consumable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode
public class ShoppingCart {
  private Set<AutoPart> autoParts = new HashSet<>();
  private Set<Consumable> consumables = new HashSet<>();

  public void addAutoPart(AutoPart autoPart) {
    autoParts.add(autoPart);
  }
  public void addConsumable(Consumable consumable) {
    consumables.add(consumable);
  }
  public void removeAutoPart(AutoPart autoPart) {
    autoParts.remove(autoPart);
  }
  public void removeConsumable(Consumable consumable) {
    consumables.remove(consumable);
  }

  public double getTotalPrice() {
    return autoParts.stream().mapToDouble(AutoPart::getPrice).sum() + consumables.stream().mapToDouble(Consumable::getPrice).sum();
  }
}
