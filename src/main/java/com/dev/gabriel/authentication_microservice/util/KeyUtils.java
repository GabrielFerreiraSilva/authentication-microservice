package com.dev.gabriel.authentication_microservice.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class KeyUtils {
  private KeyUtils() {}

  public static Key decodeKey(String path)
      throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
    String content = new String(Files.readAllBytes(new File(path).toPath()));
    content = content.replace("\r", "").replace("\n", "");
    KeyFactory factory = KeyFactory.getInstance("RSA");
    Key key = null;

    if (content.contains("PRIVATE")) {
      content =
          content
              .replace("-----BEGIN PRIVATE KEY-----", "")
              .replace("-----END PRIVATE KEY-----", "");

      byte[] keyBytes = Base64.getDecoder().decode(content);
      PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
      key = factory.generatePrivate(keySpec);
    } else if (content.contains("PUBLIC")) {
      content =
          content.replace("-----BEGIN PUBLIC KEY-----", "").replace("-----END PUBLIC KEY-----", "");

      byte[] keyBytes = Base64.getDecoder().decode(content);
      X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
      key = factory.generatePublic(keySpec);
    }

    return key;
  }
}
