package com.autodeli.autodeli.service.impl;

import com.autodeli.autodeli.exception.EntityNotFoundException;
import com.autodeli.autodeli.repository.OilRepository;
import com.autodeli.autodeli.service.OilService;
import com.autodeli.autodeli.web.Oil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OilServiceImpl implements OilService {

  private final OilRepository oilRepository;

  public OilServiceImpl(OilRepository oilRepository) {
    this.oilRepository = oilRepository;
  }

  @Override
  public List<Oil> getAllOils() {
    return oilRepository.findAll();
  }

  @Override
  public Oil getOilById(String id) {
    return oilRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("Battery with id=%s not found", id)));
  }

  @Override
  public Oil addOil(Oil oil) {
    return oilRepository.insert(oil);
  }

  @Override
  public Oil updateOil(Oil oil) {
    return oilRepository.save(oil);
  }

  @Override
  public Oil deleteOil(String id) {
    Oil removed = getOilById(id);
    oilRepository.delete(removed);
    return removed;
  }

  @Override
  public long getCount() {
    return oilRepository.count();
  }
}
