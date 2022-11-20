package com.helmes.citylist.adapter.out;

import com.helmes.citylist.adapter.out.postgresql.CityPostgresqlRepository;
import com.helmes.citylist.adapter.out.postgresql.dto.DbCityEntity;
import com.helmes.citylist.adapter.out.postgresql.mapper.DbMapper;
import com.helmes.citylist.adapter.out.postgresql.search.CitySearchSpecification;
import com.helmes.citylist.domain.dto.City;
import com.helmes.citylist.domain.dto.CitySearchDto;
import com.helmes.citylist.domain.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CityDbRepository implements CityRepository {

  private final DbMapper dbMapper;
  private final CityPostgresqlRepository postgresqlRepository;

  @Override
  public Page<City> searchPage(CitySearchDto searchDto, Pageable pageable) {
    CitySearchSpecification searchSpecification = new CitySearchSpecification(searchDto);
    Page<DbCityEntity> dbCityEntities = postgresqlRepository.findAll(searchSpecification, pageable);
    return dbCityEntities.map(dbMapper::toDomain);
  }

  @Override
  public Optional<City> findById(Long id) {
    return postgresqlRepository.findById(id).map(dbMapper::toDomain);
  }

  @Override
  public City findByIdThrow(Long id) {
    return findById(id).orElseThrow(() -> new EntityNotFoundException("City not found for id: " + id));
  }

  @Override
  public City save(City city) {
    DbCityEntity dbCityEntity = dbMapper.toDbDto(city);
    DbCityEntity savedCity = postgresqlRepository.save(dbCityEntity);
    return dbMapper.toDomain(savedCity);
  }
}
