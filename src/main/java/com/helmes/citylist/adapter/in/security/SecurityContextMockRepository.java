package com.helmes.citylist.adapter.in.security;

import com.helmes.citylist.config.properties.CityListSecurityProperties;
import com.helmes.citylist.config.properties.SecurityClient;
import liquibase.repackaged.org.apache.commons.collections4.CollectionUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Simple mock for security repository.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class SecurityContextMockRepository implements SecurityContextRepository {

  private final CityListSecurityProperties securityProperties;

  @Override
  public SecurityContext loadContext(HttpRequestResponseHolder requestResponseHolder) {
    String authHeader = requestResponseHolder.getRequest().getHeader(HttpHeaders.AUTHORIZATION);
    return decodeAuthHeader(authHeader)
        .map(this::buildNewSecurityContext)
        .orElseGet(this::buildUnauthenticatedSecurityContext);
  }

  private SecurityContext buildUnauthenticatedSecurityContext() {
    return new SecurityContextImpl();
  }

  @Override
  public void saveContext(SecurityContext context, HttpServletRequest request, HttpServletResponse response) {
    // do nothing
  }

  @Override
  public boolean containsContext(HttpServletRequest request) {
    return StringUtils.isNotBlank(request.getHeader(HttpHeaders.AUTHORIZATION));
  }

  private Optional<SecurityClient> decodeAuthHeader(String authHeader) {
    return Optional.ofNullable(authHeader)
        .filter(header -> header.startsWith("Basic "))
        .map(header -> header.substring(6))
        .map(basicAuth -> basicAuth.getBytes(StandardCharsets.UTF_8))
        .map(this::findSecurityClient);
  }

  private SecurityClient findSecurityClient(byte[] basicAuth) {
    return securityProperties.getClients()
        .stream()
        .filter(client -> Arrays.equals(client.getClientAuth(), basicAuth))
        .findFirst()
        .orElse(null);
  }

  private SecurityContext buildNewSecurityContext(SecurityClient client) {
    List<SimpleGrantedAuthority> roles = buildRoles(client.getRoles());
    String clientId = new String(client.getClientId());
    var auth = new UsernamePasswordAuthenticationToken(clientId, null, roles);
    return new SecurityContextImpl(auth);
  }

  private List<SimpleGrantedAuthority> buildRoles(Collection<CityListSecurityRoles> roles) {
    return CollectionUtils.emptyIfNull(roles)
        .stream()
        .map(Enum::name)
        .map(SimpleGrantedAuthority::new)
        .collect(Collectors.toList());
  }
}
