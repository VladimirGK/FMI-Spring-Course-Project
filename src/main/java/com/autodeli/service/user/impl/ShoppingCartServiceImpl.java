package com.autodeli.service.user.impl;

import com.autodeli.service.user.ShoppingCartService;
import com.autodeli.service.user.UserService;
import com.autodeli.web.ShoppingCart;
import com.autodeli.web.car.AutoPart;
import com.autodeli.web.consumable.Consumable;
import com.autodeli.web.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

  private ShoppingCart shoppingCart;
  @Autowired
  private UserService userService;

  private User logged;

  private void setUp() {
    String username = SecurityContextHolder.getContext().getAuthentication().getName();
    logged = userService.getUserByUsername(username);
    this.shoppingCart = logged.getShoppingCart();
  }

  @Override
  public Set<AutoPart> getAllAutoParts() {
    setUp();
    return shoppingCart.getAutoParts();
  }

  @Override
  public Set<Consumable> getAllConsumables() {
    setUp();
    return shoppingCart.getConsumables();
  }

  @Override
  public void addAutoPartToCart(AutoPart autoPart) {
    setUp();
    shoppingCart.addAutoPart(autoPart);
    logged.setShoppingCart(shoppingCart);
    userService.updateUser(logged);
  }

  @Override
  public void addConsumableToCart(Consumable consumable) {
    setUp();
    shoppingCart.addConsumable(consumable);
    logged.setShoppingCart(shoppingCart);
  }

  @Override
  public void deleteAutoPartFromCart(AutoPart autoPart) {
    setUp();
    shoppingCart.removeAutoPart(autoPart);
    logged.setShoppingCart(shoppingCart);

  }

  @Override
  public void deleteConsumableFromCart(Consumable consumable) {
    setUp();
    shoppingCart.removeConsumable(consumable);
    logged.setShoppingCart(shoppingCart);
  }

  @Override
  public double getTotalPrice() {
    setUp();
    return shoppingCart.getTotalPrice();
  }
}
