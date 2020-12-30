package com.autodeli.service.car;

import com.autodeli.web.car.AutoPart;

import java.util.List;

public interface AutoPartService {
  List<AutoPart> getAllAutoParts();
  List<AutoPart> getAllAutoPartsByEngine(String name);
  AutoPart getAutoPartById(String id);
  AutoPart getAutoPartByName(String name);
  AutoPart addAutoPart(AutoPart autoPart);
  AutoPart updateAutoPart(AutoPart autoPart);
  AutoPart deleteAutoPart(String id);
  long getCount();

  List<AutoPart> getAllSpecifiedAutoParts(String brandName, String modelName, String engineName);
}
