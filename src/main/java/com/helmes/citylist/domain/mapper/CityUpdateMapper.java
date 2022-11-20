package com.helmes.citylist.domain.mapper;

import com.helmes.citylist.domain.dto.City;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface CityUpdateMapper {

  void update(@MappingTarget City to, City from);
}
