package com.helmes.citylist.domain.repository;

public interface InitialDataRepository {

  boolean exists(String id);

  void saveNewEntity(String id);
}
