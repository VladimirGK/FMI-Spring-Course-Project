package com.autodeli.autodeli.service;

import com.autodeli.autodeli.web.Supplement;

import java.util.List;

public interface SupplementService {

  List<Supplement> getAllSupplements();

  Supplement getSupplementById(String id);

  Supplement addSupplement(Supplement supplement);

  Supplement updateSupplement(Supplement supplement);

  Supplement deleteSupplement(String id);

  long getCount();
}
