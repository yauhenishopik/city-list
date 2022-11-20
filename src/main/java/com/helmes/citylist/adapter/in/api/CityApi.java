package com.helmes.citylist.adapter.in.api;

import com.helmes.citylist.adapter.in.api.dto.RestCityDto;
import com.helmes.citylist.adapter.in.api.dto.RestCitySearchDto;
import com.helmes.citylist.adapter.in.api.dto.RestCityUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface CityApi extends ApiVersionedRestService {

  @GetMapping("/city/{id}")
  RestCityDto getById(@PathVariable("id") Long id);

  @PostMapping("/city/search")
  Page<RestCityDto> searchPage(@RequestBody @Valid RestCitySearchDto searchDto, Pageable pageable);

  @PutMapping("/city")
  @PreAuthorize("hasRole('ALLOW_EDIT')")
  RestCityDto update(@RequestBody @Valid RestCityUpdateDto searchDto);

}
