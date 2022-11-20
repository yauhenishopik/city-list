package com.helmes.citylist.adapter.in.api;


import org.springframework.web.bind.annotation.RequestMapping;

import static com.helmes.citylist.config.properties.AppInfoProperties.API_PREFIX_PATTERN;

@RequestMapping(API_PREFIX_PATTERN)
public interface ApiVersionedRestService {
}
