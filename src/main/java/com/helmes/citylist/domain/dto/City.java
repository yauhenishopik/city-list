package com.helmes.citylist.domain.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class City {
  private Long id;
  private String name;
  private String photoUrl;
}
