package com.autodeli.controller;

import com.autodeli.service.car.AutoPartService;
import com.autodeli.service.consumable.BatteryService;
import com.autodeli.service.consumable.OilService;
import com.autodeli.service.consumable.SupplementService;
import com.autodeli.service.user.ShoppingCartService;
import com.autodeli.web.car.AutoPart;
import com.autodeli.web.consumable.Consumable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/cart")
public class ShoppingCartController {

  @Autowired
  private ShoppingCartService shoppingCartService;
  @Autowired
  private AutoPartService autoPartService;
  @Autowired
  private BatteryService batteryService;
  @Autowired
  private OilService oilService;
  @Autowired
  private SupplementService supplementService;


  @GetMapping("/autopart")
  public Set<AutoPart> getAllAutoParts() {
    return shoppingCartService.getAllAutoParts();
  }

  @GetMapping("/consumable")
  public Set<Consumable> getAllConsumables() {
    return shoppingCartService.getAllConsumables();
  }

  @GetMapping("/price")
  public double getTotalPrice() {
    return shoppingCartService.getTotalPrice();
  }

  @PostMapping("/autopart")
  public Set<AutoPart> addAutoPart(@RequestBody AutoPart autoPart) {
    shoppingCartService.addAutoPartToCart(autoPart);
    return getAllAutoParts();
  }

  @PostMapping("/consumable")
  public Set<Consumable> addConsumable(@RequestBody Consumable consumable) {
    shoppingCartService.addConsumableToCart(consumable);
    return getAllConsumables();
  }

  @DeleteMapping("/autopart/{id}")
  public AutoPart deleteAutoPart(@PathVariable("id") String id) {
    AutoPart removed = autoPartService.getAutoPartById(id);
    shoppingCartService.deleteAutoPartFromCart(removed);
    return removed;
  }

  @DeleteMapping("/consumable/{id}")
  public Consumable deleteConsumable(@PathVariable("id") String id) {
    Consumable removed = null;
    for(Consumable consumable : getAllConsumables()) {
      if(consumable.getId().equals(id)) {
        removed = consumable;
        break;
      }
    }
    shoppingCartService.deleteConsumableFromCart(removed);
    return removed;
  }

}
