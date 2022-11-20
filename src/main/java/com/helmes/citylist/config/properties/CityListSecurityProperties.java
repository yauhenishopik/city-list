package com.helmes.citylist.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@Data
@ConfigurationProperties("city-list.security")
public class CityListSecurityProperties {
  private String[] permittedPaths = new String[]{};
  private List<SecurityClient> clients = new ArrayList<>();
}
