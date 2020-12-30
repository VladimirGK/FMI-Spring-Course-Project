package com.autodeli.service.car.impl;

import com.autodeli.exception.EntityNotFoundException;
import com.autodeli.repository.car.EngineRepository;
import com.autodeli.service.car.EngineService;
import com.autodeli.web.car.Engine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EngineServiceImpl implements EngineService {
  private final EngineRepository engineRepository;

  @Autowired
  public EngineServiceImpl(EngineRepository engineRepository) {
    this.engineRepository = engineRepository;
  }

  @Override
  public List<Engine> getAllEngines() {
    return engineRepository.findAll();
  }

  @Override
  public List<Engine> getAllEnginesByBrandAndModelName(String brandName, String modelName) {
    return engineRepository.findAllByBrandNameAndModelName(brandName, modelName);
  }

  @Override
  public Engine getEngineById(String id) {
    return engineRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("Model with id=%s not found", id)));
  }

  @Override
  public Engine getEngineByName(String name) {
    return engineRepository.findByName(name);
  }

  @Override
  public Engine addEngine(Engine engine) {
    return engineRepository.insert(engine);
  }

  @Override
  public Engine updateEngine(Engine engine) {
    return engineRepository.save(engine);
  }

  @Override
  public Engine deleteEngine(String id) {
    Engine removed = getEngineById(id);
    engineRepository.delete(removed);
    return removed;
  }

  @Override
  public long getCount() {
    return engineRepository.count();
  }
}
