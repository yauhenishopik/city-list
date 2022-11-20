package com.helmes.citylist.adapter.out.postgresql.search;

import com.helmes.citylist.adapter.out.postgresql.dto.DbCityEntity;
import com.helmes.citylist.domain.dto.CitySearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.helmes.citylist.adapter.out.postgresql.dto.DbCityEntity.COLUMN_NAME;

@RequiredArgsConstructor
public class CitySearchSpecification implements Specification<DbCityEntity> {

  private final CitySearchDto searchDto;

  @Override
  public Predicate toPredicate(Root<DbCityEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
    Predicate namePredicate = Optional.of(searchDto)
        .map(CitySearchDto::getName)
        .map(String::toLowerCase)
        .map(name -> builder.like(builder.lower(root.get(COLUMN_NAME)), name + "%"))
        .orElse(null);

    return builder.and(
        Stream.of(namePredicate).filter(Objects::nonNull).collect(Collectors.toList()).toArray(new Predicate[]{})
    );
  }
}
