package com.helmes.citylist.adapter.in.mapper;

import com.helmes.citylist.adapter.in.api.dto.RestCityDto;
import com.helmes.citylist.adapter.in.api.dto.RestCitySearchDto;
import com.helmes.citylist.adapter.in.api.dto.RestCityUpdateDto;
import com.helmes.citylist.domain.dto.City;
import com.helmes.citylist.domain.dto.CitySearchDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface RestMapper {

  RestCityDto toRest(City city);

  CitySearchDto toDomain(RestCitySearchDto restDto);

  City toDomain(RestCityUpdateDto restDto);
}
