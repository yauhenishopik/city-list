package com.helmes.citylist.domain.initial_data;

import com.helmes.citylist.domain.dto.City;
import com.helmes.citylist.domain.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStreamReader;

@Component
@RequiredArgsConstructor
public class InitialCitiesImporter implements InitialDataImporter {

  private static final String DATA_FILE_PATH = "initial_data/cities.csv";
  private static final String IMPORTER_ID = "cities";

  private final CityRepository cityRepository;

  @Override
  public String getImporterId() {
    return IMPORTER_ID;
  }

  @Override
  @SneakyThrows
  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public void importData() {
    try (CSVParser citiesParser = createCitiesParser()) {
      citiesParser.getRecords()
          .stream()
          .map(this::parseDbCity)
          .forEach(cityRepository::save);
    }
  }

  private City parseDbCity(CSVRecord line) {
    return new City()
        .setId(Long.valueOf(line.get("id")))
        .setName(line.get("name"))
        .setPhotoUrl(line.get("photo"));
  }

  private CSVParser createCitiesParser() throws IOException {
    return new CSVParser(
        new InputStreamReader(new ClassPathResource(DATA_FILE_PATH).getInputStream()),
        CSVFormat.EXCEL
            .withFirstRecordAsHeader()
            .withIgnoreHeaderCase()
            .withIgnoreEmptyLines()
            .withIgnoreSurroundingSpaces()
            .withNullString("")
            .withTrim()
    );
  }
}
