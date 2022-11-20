package com.helmes.citylist.config;

import com.helmes.citylist.config.properties.AppInfoProperties;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(AppInfoProperties.class)
public class SpringdocConfig {

  @Bean
  public GroupedOpenApi triageAnalysisApi(
      AppInfoProperties appInfoProperties
  ) {
    return GroupedOpenApi.builder()
        .group("city-list-api")
        .displayName("CityList API")
        .addOpenApiCustomiser(openApi -> addInfo(appInfoProperties, openApi))
        .addOpenApiCustomiser(this::addSecurity)
        .pathsToMatch("/**")
        .build();
  }

  private void addInfo(AppInfoProperties appInfoProperties, OpenAPI openApi) {
    Info appInfo = new Info()
        .title(appInfoProperties.getName())
        .description(appInfoProperties.getDescription())
        .version(appInfoProperties.getVersion());
    openApi.info(appInfo);
  }

  private void addSecurity(OpenAPI openApi) {
    String auth = "Basic Auth";
    openApi
        .addSecurityItem(new SecurityRequirement().addList(auth))
        .components(
            openApi.getComponents()
                .addSecuritySchemes(auth,
                    new SecurityScheme()
                        .name(auth)
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("basic")));
  }
}
