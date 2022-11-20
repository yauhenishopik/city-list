package com.helmes.citylist.domain.initial_data;

public interface InitialDataImporter {

  String getImporterId();

  void importData();
}
