package com.autodeli.controller.user;

import com.autodeli.service.car.AutoPartService;
import com.autodeli.service.consumable.BatteryService;
import com.autodeli.service.consumable.OilService;
import com.autodeli.service.consumable.SupplementService;
import com.autodeli.service.user.ShoppingCartService;
import com.autodeli.web.car.AutoPart;
import com.autodeli.web.consumable.Battery;
import com.autodeli.web.consumable.Oil;
import com.autodeli.web.consumable.Supplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/cart")
@PreAuthorize("hasRole('USER')")
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
  public List<AutoPart> getAllAutoParts() {
    return shoppingCartService.getAllAutoParts();
  }

  @GetMapping("/battery")
  public List<Battery> getAllBatteries() {
    return shoppingCartService.getAllBatteries();
  }

  @GetMapping("/oil")
  public List<Oil> getAllOils() {
    return shoppingCartService.getAllOils();
  }

  @GetMapping("/supplement")
  public List<Supplement> getAllSupplements() {
    return shoppingCartService.getAllSupplements();
  }

  @GetMapping("/price")
  public double getTotalPrice() {
    return shoppingCartService.getTotalPrice();
  }

  @PostMapping("/autopart")
  public List<AutoPart> addAutoPart(@RequestBody AutoPart autoPart) {
    shoppingCartService.addAutoPartToCart(autoPart);
    return getAllAutoParts();
  }

  @PostMapping("/battery")
  public List<Battery> addBattery(@RequestBody Battery battery) {
    shoppingCartService.addBatteryToCart(battery);
    return getAllBatteries();
  }

  @PostMapping("/oil")
  public List<Oil> addOil(@RequestBody Oil oil) {
    shoppingCartService.addOilToCart(oil);
    return getAllOils();
  }

  @PostMapping("/supplement")
  public List<Supplement> addSupplement(@RequestBody Supplement supplement) {
    shoppingCartService.addSupplementToCart(supplement);
    return getAllSupplements();
  }

  @DeleteMapping("/autopart/{id}")
  public AutoPart deleteAutoPart(@PathVariable("id") String id) {
    AutoPart removed = autoPartService.getAutoPartById(id);
    shoppingCartService.deleteAutoPartFromCart(removed);
    return removed;
  }

  @DeleteMapping("/battery/{id}")
  public Battery deleteBattery(@PathVariable("id") String id) {
    Battery removed = batteryService.getBatteryById(id);
    shoppingCartService.deleteBatteryFromCart(removed);
    return removed;
  }

  @DeleteMapping("/oil/{id}")
  public Oil deleteOil(@PathVariable("id") String id) {
    Oil removed = oilService.getOilById(id);
    shoppingCartService.deleteOilFromCart(removed);
    return removed;
  }

  @DeleteMapping("/supplement/{id}")
  public Supplement deleteSupplement(@PathVariable("id") String id) {
    Supplement removed = supplementService.getSupplementById(id);
    shoppingCartService.deleteSupplementFromCart(removed);
    return removed;
  }

}
