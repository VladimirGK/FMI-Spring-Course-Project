package com.autodeli.autodeli.service;

import com.autodeli.autodeli.web.Oil;

import java.util.List;

public interface OilService {

  List<Oil> getAllOils();

  Oil getOilById(String id);

  Oil addOil(Oil oil);

  Oil updateOil(Oil oil);

  Oil deleteOil(String id);

  long getCount();
}
