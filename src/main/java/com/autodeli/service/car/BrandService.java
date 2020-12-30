package com.autodeli.service.car;

import com.autodeli.web.car.Brand;

import java.util.List;

public interface BrandService {
  List<Brand> getAllBrands();
  Brand getBrandById(String id);
  Brand getBrandByName(String name);
  Brand addBrand(Brand brand);
  Brand updateBrand(Brand brand);
  Brand deleteBrand(String id);
  long getCount();
}
