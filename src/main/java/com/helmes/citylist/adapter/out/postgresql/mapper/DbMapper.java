package com.helmes.citylist.adapter.out.postgresql.mapper;

import com.helmes.citylist.adapter.out.postgresql.dto.DbCityEntity;
import com.helmes.citylist.domain.dto.City;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface DbMapper {

  City toDomain(DbCityEntity city);

  DbCityEntity toDbDto(City city);
}
