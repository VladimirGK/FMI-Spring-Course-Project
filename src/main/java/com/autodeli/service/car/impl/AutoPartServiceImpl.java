package com.autodeli.service.car.impl;

import com.autodeli.exception.EntityNotFoundException;
import com.autodeli.repository.car.AutoPartRepository;
import com.autodeli.service.car.AutoPartService;
import com.autodeli.web.car.AutoPart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutoPartServiceImpl implements AutoPartService {

  private final AutoPartRepository autoPartRepository;

  @Autowired
  public AutoPartServiceImpl(AutoPartRepository autoPartRepository) {
    this.autoPartRepository = autoPartRepository;
  }

  @Override
  public List<AutoPart> getAllAutoParts() {
    return autoPartRepository.findAll();
  }

  @Override
  public List<AutoPart> getAllAutoPartsByEngine(String name) {
    return autoPartRepository.findAllByEngineName(name);
  }

  @Override
  public AutoPart getAutoPartById(String id) {
    return autoPartRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("Model with id=%s not found", id)));
  }

  @Override
  public AutoPart getAutoPartByName(String name) {
    return autoPartRepository.findByName(name);
  }

  @Override
  public AutoPart addAutoPart(AutoPart autoPart) {
    return autoPartRepository.insert(autoPart);
  }

  @Override
  public AutoPart updateAutoPart(AutoPart autoPart) {
    return autoPartRepository.save(autoPart);
  }

  @Override
  public AutoPart deleteAutoPart(String id) {
    AutoPart removed = getAutoPartById(id);
    autoPartRepository.delete(removed);
    return removed;
  }

  @Override
  public long getCount() {
    return autoPartRepository.count();
  }

  @Override
  public List<AutoPart> getAllSpecifiedAutoParts(String brandName, String modelName, String engineName) {
    return autoPartRepository.findAllByBrandNameAndModelNameAndEngineName(brandName, modelName, engineName);
  }
}
