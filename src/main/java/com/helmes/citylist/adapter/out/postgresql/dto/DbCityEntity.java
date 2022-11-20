package com.helmes.citylist.adapter.out.postgresql.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "city")
@Accessors(chain = true)
public class DbCityEntity {

  public static final String COLUMN_ID = "id";
  public static final String COLUMN_NAME = "name";
  public static final String COLUMN_PHOTO_URL = "photo_url";

  @Id
  @Column(name = COLUMN_ID)
  @SequenceGenerator(name = "city_id_seq", sequenceName = "city_id_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "city_id_seq")
  private Long id;

  @NotNull
  @Column(name = COLUMN_NAME)
  private String name;

  @Column(name = COLUMN_PHOTO_URL)
  private String photoUrl;
}
