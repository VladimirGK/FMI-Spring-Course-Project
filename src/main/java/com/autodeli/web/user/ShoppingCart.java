package com.autodeli.web.user;

import com.autodeli.web.car.AutoPart;
import com.autodeli.web.consumable.Battery;
import com.autodeli.web.consumable.Consumable;
import com.autodeli.web.consumable.Oil;
import com.autodeli.web.consumable.Supplement;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@EqualsAndHashCode
public class ShoppingCart {
  private List<AutoPart> autoParts = new ArrayList<>();
  private List<Battery> batteries = new ArrayList<>();
  private List<Oil> oils = new ArrayList<>();
  private List<Supplement> supplements = new ArrayList<>();

  public void addAutoPart(AutoPart autoPart) {
    autoParts.add(autoPart);
  }

  public void addBattery(Battery battery) {
    batteries.add(battery);
  }

  public void addOil(Oil oil) {
    oils.add(oil);
  }

  public void addSupplement(Supplement supplement) {
    supplements.add(supplement);
  }

  public void removeAutoPart(AutoPart autoPart) {
    autoParts.remove(autoPart);
  }

  public void removeBattery(Battery battery) {
    batteries.remove(battery);
  }

  public void removeOil(Oil oil) {
    oils.remove(oil);
  }

  public void removeSupplement(Supplement supplement) {
    supplements.remove(supplement);
  }

  public double getTotalPrice() {
    return autoParts.stream().mapToDouble(AutoPart::getPrice).sum() + batteries.stream().mapToDouble(Battery::getPrice).sum() +
            oils.stream().mapToDouble(Oil::getPrice).sum() + supplements.stream().mapToDouble(Supplement::getPrice).sum();
  }
}
