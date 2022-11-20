package com.helmes.citylist.adapter.in.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class RestCityDto {
  private Long id;
  private String name;
  private String photoUrl;
}
