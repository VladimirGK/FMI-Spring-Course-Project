package com.autodeli.service.car;

import com.autodeli.web.car.Engine;
import com.autodeli.web.car.Model;

import java.util.List;

public interface EngineService {
  List<Engine> getAllEngines();
  List<Engine> getAllEnginesByBrandAndModelName(String brandName, String modelName);
  Engine getEngineById(String id);
  Engine getEngineByName(String name);
  Engine addEngine(Engine engine);
  Engine updateEngine(Engine engine);
  Engine deleteEngine(String id);
  long getCount();
}
