package com.autodeli.service.car.impl;

import com.autodeli.exception.EntityNotFoundException;
import com.autodeli.repository.car.BrandRepository;
import com.autodeli.service.car.BrandService;
import com.autodeli.web.car.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

  private final BrandRepository brandRepository;

  @Autowired
  public BrandServiceImpl(BrandRepository brandRepository) {
    this.brandRepository = brandRepository;
  }

  @Override
  public List<Brand> getAllBrands() {
    return brandRepository.findAll();
  }

  @Override
  public Brand getBrandById(String id) {
    return brandRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("Brand with id=%s not found", id)));
  }

  @Override
  public Brand getBrandByName(String name) {
    return brandRepository.findBrandByName(name);
  }

  @Override
  public Brand addBrand(Brand brand) {
    return brandRepository.insert(brand);
  }

  @Override
  public Brand updateBrand(Brand brand) {
    return brandRepository.save(brand);
  }

  @Override
  public Brand deleteBrand(String id) {
    Brand removed = getBrandById(id);
    brandRepository.delete(removed);
    return removed;
  }

  @Override
  public long getCount() {
    return brandRepository.count();
  }
}
