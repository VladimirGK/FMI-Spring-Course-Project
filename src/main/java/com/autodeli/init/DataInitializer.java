package com.autodeli.init;

import com.autodeli.repository.consumable.BatteryRepository;
import com.autodeli.repository.consumable.OilRepository;
import com.autodeli.repository.consumable.SupplementRepository;
import com.autodeli.repository.user.UserRepository;
import com.autodeli.repository.car.AutoPartRepository;
import com.autodeli.repository.car.BrandRepository;
import com.autodeli.repository.car.EngineRepository;
import com.autodeli.repository.car.ModelRepository;
import com.autodeli.web.user.User;
import com.autodeli.web.car.AutoPart;
import com.autodeli.web.car.Brand;
import com.autodeli.web.car.Engine;
import com.autodeli.web.car.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;

import static com.autodeli.web.user.Role.ADMIN;
import static com.autodeli.web.user.Role.USER;

@Component
public class DataInitializer implements CommandLineRunner {

  private final AutoPartRepository autoPartRepository;
  private final EngineRepository engineRepository;
  private final ModelRepository modelRepository;
  private final BrandRepository brandRepository;

  private final OilRepository oilRepository;
  private final SupplementRepository supplementRepository;
  private final BatteryRepository batteryRepository;

  private final UserRepository userRepository;

  PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

  @Autowired
  public DataInitializer(AutoPartRepository autoPartRepository, EngineRepository engineRepository, ModelRepository modelRepository,
      BrandRepository brandRepository, OilRepository oilRepository, SupplementRepository supplementRepository, BatteryRepository batteryRepository,
      UserRepository userRepository) {
    this.autoPartRepository = autoPartRepository;
    this.engineRepository = engineRepository;
    this.modelRepository = modelRepository;
    this.brandRepository = brandRepository;
    this.oilRepository = oilRepository;
    this.supplementRepository = supplementRepository;
    this.batteryRepository = batteryRepository;
    this.userRepository = userRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    Brand audi = new Brand("Audi", "https://www.photo.png");
    Model a4 = new Model("A4", "Audi");
    Engine TDI1_9 = new Engine("A4", "1.9TDI", "A4");
    AutoPart waterPomp = new AutoPart("Water Pomp", "Audi", "A4", "1.9TDI", "https://www.waterpomp.png", 102.20);
    AutoPart turbo = new AutoPart("Turbo", "Audi", "A4", "1.9TDI", "http://www.tofast4you.com", 232);

//    autoPartRepository.deleteAll();
//    engineRepository.deleteAll();
//    modelRepository.deleteAll();
//    brandRepository.deleteAll();
//    userRepository.deleteAll();
//    oilRepository.deleteAll();
//    supplementRepository.deleteAll();
//    batteryRepository.deleteAll();

    User admin = new User("admin", "admin", "admin", encoder.encode("admin123"),
        new HashSet<>(Arrays.asList(ADMIN, USER)));
//    userRepository.save(admin);


  }
}
