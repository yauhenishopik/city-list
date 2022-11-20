package com.helmes.citylist.adapter.out.postgresql;

import com.helmes.citylist.adapter.out.postgresql.dto.DbCityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CityPostgresqlRepository extends JpaRepository<DbCityEntity, Long>, JpaSpecificationExecutor<DbCityEntity> {
}
