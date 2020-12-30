package com.autodeli.service.consumable.impl;

import com.autodeli.exception.EntityNotFoundException;
import com.autodeli.repository.consumable.SupplementRepository;
import com.autodeli.service.consumable.SupplementService;
import com.autodeli.web.consumable.Supplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplementServiceImpl implements SupplementService {

  private final SupplementRepository supplementRepository;

  @Autowired
  public SupplementServiceImpl(SupplementRepository supplementRepository) {
    this.supplementRepository = supplementRepository;
  }

  @Override
  public List<Supplement> getAllSupplements() {
    return supplementRepository.findAll();
  }

  @Override
  public Supplement getSupplementById(String id) {
    return supplementRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("Supplement with id=%s not found", id)));
  }

  @Override
  public Supplement addSupplement(Supplement supplement) {
    return supplementRepository.insert(supplement);
  }

  @Override
  public Supplement updateSupplement(Supplement supplement) {
    return supplementRepository.save(supplement);
  }

  @Override
  public Supplement deleteSupplement(String id) {
    Supplement removed = getSupplementById(id);
    supplementRepository.delete(removed);
    return removed;
  }

  @Override
  public long getCount() {
    return supplementRepository.count();
  }
}
