package com.helmes.citylist.config.properties;

import com.helmes.citylist.adapter.in.security.CityListSecurityRoles;
import lombok.Data;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Set;

@Data
public class SecurityClient {
  private char[] clientId;
  private char[] clientSecret;
  private Set<CityListSecurityRoles> roles;

  public byte[] getClientAuth() {
    String decodedClientAuth = String.valueOf(clientId) + ":" + String.valueOf(clientSecret);
    return Base64.getEncoder().encode(decodedClientAuth.getBytes(StandardCharsets.UTF_8));
  }
}
