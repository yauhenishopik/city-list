package com.helmes.citylist.adapter.out.postgresql;

import com.helmes.citylist.adapter.out.postgresql.dto.DbInitialDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InitialDataPostgresqlRepository extends JpaRepository<DbInitialDataEntity, String> {
}