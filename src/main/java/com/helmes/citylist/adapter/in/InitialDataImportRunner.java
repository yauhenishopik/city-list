package com.helmes.citylist.adapter.in;

import com.helmes.citylist.domain.initial_data.InitialDataImporter;
import com.helmes.citylist.domain.repository.InitialDataRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class InitialDataImportRunner implements CommandLineRunner {

  private final List<InitialDataImporter> importers;
  private final InitialDataRepository initialDataRepository;

  @Override
  public void run(String... args) {
    importers
        .stream()
        .filter(importer -> !initialDataRepository.exists(importer.getImporterId()))
        .peek(InitialDataImporter::importData)
        .peek(importer -> initialDataRepository.saveNewEntity(importer.getImporterId()))
        .forEach(importer -> log.info("Initial data import finished for importer " + importer.getImporterId()));
  }
}
