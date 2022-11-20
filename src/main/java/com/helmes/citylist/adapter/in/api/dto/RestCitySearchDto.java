package com.helmes.citylist.adapter.in.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class RestCitySearchDto {

  private String name;
}
