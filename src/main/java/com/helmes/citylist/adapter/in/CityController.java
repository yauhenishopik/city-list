package com.helmes.citylist.adapter.in;

import com.helmes.citylist.adapter.in.api.CityApi;
import com.helmes.citylist.adapter.in.api.dto.RestCityDto;
import com.helmes.citylist.adapter.in.api.dto.RestCitySearchDto;
import com.helmes.citylist.adapter.in.api.dto.RestCityUpdateDto;
import com.helmes.citylist.adapter.in.mapper.RestMapper;
import com.helmes.citylist.domain.dto.City;
import com.helmes.citylist.domain.dto.CitySearchDto;
import com.helmes.citylist.domain.service.CityDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CityController implements CityApi {

  private final RestMapper restMapper;
  private final CityDomainService cityDomainService;

  @Override
  public RestCityDto getById(Long id) {
    log.debug("Get city by id: {}", id);
    City domainCity = cityDomainService.get(id);
    return restMapper.toRest(domainCity);
  }

  @Override
  public Page<RestCityDto> searchPage(RestCitySearchDto searchDto, Pageable pageable) {
    log.debug("Search cities: {}", searchDto);
    CitySearchDto domainSearchDto = restMapper.toDomain(searchDto);
    Page<City> domainCities = cityDomainService.searchPage(domainSearchDto, pageable);
    return domainCities.map(restMapper::toRest);
  }

  @Override
  public RestCityDto update(RestCityUpdateDto searchDto) {
    log.debug("Update city: {}", searchDto);
    City updateDto = restMapper.toDomain(searchDto);
    City updatedDto = cityDomainService.update(updateDto);
    return restMapper.toRest(updatedDto);
  }
}
