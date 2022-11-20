package com.helmes.citylist.adapter.out;

import com.helmes.citylist.adapter.out.postgresql.InitialDataPostgresqlRepository;
import com.helmes.citylist.adapter.out.postgresql.dto.DbInitialDataEntity;
import com.helmes.citylist.domain.repository.InitialDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitialDataDbRepository implements InitialDataRepository {

  private final InitialDataPostgresqlRepository initialDataPostgresqlRepository;

  @Override
  public boolean exists(String id) {
    return initialDataPostgresqlRepository.existsById(id);
  }

  @Override
  public void saveNewEntity(String id) {
    initialDataPostgresqlRepository.save(new DbInitialDataEntity(id));
  }
}
