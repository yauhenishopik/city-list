package com.helmes.citylist.domain.servicelogic;

import com.helmes.citylist.domain.dto.City;
import com.helmes.citylist.domain.dto.CitySearchDto;
import com.helmes.citylist.domain.mapper.CityUpdateMapper;
import com.helmes.citylist.domain.repository.CityRepository;
import com.helmes.citylist.domain.service.CityDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CityDomainServiceImpl implements CityDomainService {

  private final CityRepository cityRepository;
  private final CityUpdateMapper cityUpdateMapper;

  @Override
  public Page<City> searchPage(CitySearchDto searchDto, Pageable pageable) {
    return cityRepository.searchPage(searchDto, pageable);
  }

  @Override
  public City update(City updateDto) {
    City existingCity = cityRepository.findByIdThrow(updateDto.getId());
    cityUpdateMapper.update(existingCity, updateDto);
    return cityRepository.save(existingCity);
  }

  @Override
  public City get(Long id) {
    return cityRepository.findByIdThrow(id);
  }
}
