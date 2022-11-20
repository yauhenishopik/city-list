package com.helmes.citylist.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.constraints.NotNull;

@Data
@ConfigurationProperties("info")
public class AppInfoProperties {

  public static final String API_PREFIX_PATTERN = "#{'/api/v' + '${info.version}'.split('\\.')[0]}";

  private @NotNull String name;
  private @NotNull String version;
  private @NotNull String description;

  public String getShortVersion() {
    return "v" + version.split("\\.")[0];
  }

  public String getApiPrefix() {
    return "/api/" + getShortVersion();
  }
}
