package com.helmes.citylist.domain.repository;

import com.helmes.citylist.domain.dto.City;
import com.helmes.citylist.domain.dto.CitySearchDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CityRepository {
  Page<City> searchPage(CitySearchDto searchDto, Pageable pageable);

  Optional<City> findById(Long id);

  City findByIdThrow(Long id);

  City save(City city);
}
