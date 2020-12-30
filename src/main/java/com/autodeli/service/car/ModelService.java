package com.autodeli.service.car;

import com.autodeli.web.car.Brand;
import com.autodeli.web.car.Model;
import java.util.List;


public interface ModelService {
  List<Model> getAllModels();
  List<Model> getAllModelsByBrandName(String name);
  Model getModelById(String id);
  Model getModelByName(String name);
  Model addModel(Model model);
  Model updateModel(Model model);
  Model deleteModel(String id);
  long getCount();

}
