package com.helmes.citylist.domain.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CitySearchDto {

  private String name;
}
