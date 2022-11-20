package com.helmes.citylist.adapter.in.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
public class RestCityUpdateDto {
  @NotNull
  private Long id;
  @NotBlank
  private String name;
  private String photoUrl;
}
