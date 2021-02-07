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
//    autoPartRepository.deleteAll();
//    engineRepository.deleteAll();
//    modelRepository.deleteAll();
//    brandRepository.deleteAll();
//    userRepository.deleteAll();
//    oilRepository.deleteAll();
//    supplementRepository.deleteAll();
//    batteryRepository.deleteAll();

//    User admin = new User("admin", "admin", "admin", "admin@admin.com", encoder.encode("asdqwe123"));
//    admin.setRoles(new HashSet<>(Arrays.asList(ADMIN, USER)));
//    userRepository.save(admin);
//
//    brandRepository.save(new Brand("Audi", "https://www.photo.png"));
//    brandRepository.save(new Brand("Mercedes-Benz", "https://www.photo.png"));
//    brandRepository.save(new Brand("BMW", "https://www.photo.png"));
//    brandRepository.save(new Brand("Mazda", "https://www.photo.png"));
//    brandRepository.save(new Brand("Opel", "https://www.photo.png"));
//
//    modelRepository.save(new Model("A4", "Audi"));
//    modelRepository.save(new Model("A6", "Audi"));
//    modelRepository.save(new Model("A8", "Audi"));
//    modelRepository.save(new Model("M4", "BMW"));
//    modelRepository.save(new Model("M6", "BMW"));
//    modelRepository.save(new Model("S-CLASS", "Mercedes-Benz"));
//    modelRepository.save(new Model("G-GLASS", "Mercedes-Benz"));
//
//    engineRepository.save(new Engine("1.9TDI", "Audi", "A4"));
//    engineRepository.save(new Engine("2.5TDI", "Audi", "A4"));
//    engineRepository.save(new Engine("3.2TFSI", "BMW", "M4"));
//    engineRepository.save(new Engine("4.2TFSI", "BMW", "M6"));
//    engineRepository.save(new Engine("2.2CDI", "Mercedes-Benz", "S-CLASS"));
//
//    autoPartRepository.save(new AutoPart("Водна помпа", "Audi", "A4", "1.9TDI", "https://previews.123rf.com/images/notsuperstar/notsuperstar1711/notsuperstar171100279/90500392-car-water-pump-isolated-on-a-white-background.jpg", 102.20));
//    autoPartRepository.save(new AutoPart("Турбо", "Audi", "A4", "1.9TDI", "https://car-images.bauersecure.com/pagefiles/94153/turbo_050.jpg", 220.20));
//    autoPartRepository.save(new AutoPart("Трансмисия", "Audi", "A4", "1.9TDI", "https://i.ytimg.com/vi/RQWejyx0gi8/hqdefault.jpg", 102.20));
//    autoPartRepository.save(new AutoPart("Гуми", "Audi", "A4", "1.9TDI", "https://di-uploads-pod7.dealerinspire.com/usedcarking/uploads/2018/08/tires.png", 102.20));

  }
}
