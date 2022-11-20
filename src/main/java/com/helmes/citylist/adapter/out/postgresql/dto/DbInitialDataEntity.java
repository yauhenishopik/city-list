package com.helmes.citylist.adapter.out.postgresql.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "initial_data")
@NoArgsConstructor
@AllArgsConstructor
public class DbInitialDataEntity {
  @Id
  private String id;
}
