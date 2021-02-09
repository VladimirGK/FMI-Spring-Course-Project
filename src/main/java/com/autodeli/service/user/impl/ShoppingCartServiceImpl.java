package com.autodeli.service.user.impl;

import com.autodeli.service.user.ShoppingCartService;
import com.autodeli.service.user.UserService;
import com.autodeli.web.user.ShoppingCart;
import com.autodeli.web.car.AutoPart;
import com.autodeli.web.consumable.Battery;
import com.autodeli.web.consumable.Oil;
import com.autodeli.web.consumable.Supplement;
import com.autodeli.web.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    private UserService userService;

    private ShoppingCart shoppingCart;
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
    public Set<Battery> getAllBatteries() {
        setUp();
        return shoppingCart.getBatteries();
    }

    @Override
    public Set<Oil> getAllOils() {
        setUp();
        return shoppingCart.getOils();
    }

    @Override
    public Set<Supplement> getAllSupplements() {
        setUp();
        return shoppingCart.getSupplements();
    }

    @Override
    public void addAutoPartToCart(AutoPart autoPart) {
        setUp();
        shoppingCart.addAutoPart(autoPart);
        logged.setShoppingCart(shoppingCart);
        userService.updateUser(logged);
    }

    @Override
    public void deleteAutoPartFromCart(AutoPart autoPart) {
        setUp();
        shoppingCart.removeAutoPart(autoPart);
        logged.setShoppingCart(shoppingCart);
        userService.updateUser(logged);
    }

    @Override
    public double getTotalPrice() {
        setUp();
        return shoppingCart.getTotalPrice();
    }

    @Override
    public void addBatteryToCart(Battery battery) {
        setUp();
        shoppingCart.addBattery(battery);
        logged.setShoppingCart(shoppingCart);
        userService.updateUser(logged);
    }

    @Override
    public void addOilToCart(Oil oil) {
        setUp();
        shoppingCart.addOil(oil);
        logged.setShoppingCart(shoppingCart);
        userService.updateUser(logged);
    }

    @Override
    public void addSupplementToCart(Supplement supplement) {
        setUp();
        shoppingCart.addSupplement(supplement);
        logged.setShoppingCart(shoppingCart);
        userService.updateUser(logged);
    }

    @Override
    public void deleteBatteryFromCart(Battery removed) {
        setUp();
        shoppingCart.removeBattery(removed);
        logged.setShoppingCart(shoppingCart);
        userService.updateUser(logged);
    }

    @Override
    public void deleteOilFromCart(Oil removed) {
        setUp();
        shoppingCart.removeOil(removed);
        logged.setShoppingCart(shoppingCart);
        userService.updateUser(logged);
    }

    @Override
    public void deleteSupplementFromCart(Supplement removed) {
        setUp();
        shoppingCart.removeSupplement(removed);
        logged.setShoppingCart(shoppingCart);
        userService.updateUser(logged);
    }
}
