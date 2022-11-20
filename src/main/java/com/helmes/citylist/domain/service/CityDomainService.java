package com.helmes.citylist.domain.service;

import com.helmes.citylist.domain.dto.City;
import com.helmes.citylist.domain.dto.CitySearchDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CityDomainService {

  Page<City> searchPage(CitySearchDto searchDto, Pageable pageable);

  City update(City updateDto);

  City get(Long id);
}
