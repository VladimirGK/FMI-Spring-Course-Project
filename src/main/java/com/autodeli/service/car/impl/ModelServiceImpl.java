package com.autodeli.service.car.impl;

import com.autodeli.exception.EntityNotFoundException;
import com.autodeli.repository.car.ModelRepository;
import com.autodeli.service.car.BrandService;
import com.autodeli.service.car.ModelService;
import com.autodeli.web.car.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelServiceImpl implements ModelService {
  private final ModelRepository modelRepository;
  private final BrandService brandService;

  @Autowired
  public ModelServiceImpl(ModelRepository modelRepository, BrandService brandService) {
    this.modelRepository = modelRepository;
    this.brandService = brandService;
  }

  @Override
  public List<Model> getAllModels() {
    return modelRepository.findAll();
  }

  @Override
  public List<Model> getAllModelsByBrandName(String name) {
    return modelRepository.findAllByBrandName(name);
  }

  @Override
  public Model getModelById(String id) {
    return modelRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("Model with id=%s not found", id)));
  }

  @Override
  public Model getModelByName(String name) {
    return modelRepository.findByName(name);
  }

  @Override
  public Model addModel(Model model) {
    return modelRepository.insert(model);
  }

  @Override
  public Model updateModel(Model model) {
    return modelRepository.save(model);
  }

  @Override
  public Model deleteModel(String id) {
    Model removed = getModelById(id);
    modelRepository.delete(removed);
    return removed;
  }

  @Override
  public long getCount() {
    return modelRepository.count();
  }
}
