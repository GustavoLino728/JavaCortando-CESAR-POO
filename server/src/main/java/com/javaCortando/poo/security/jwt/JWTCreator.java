package com.javaCortando.poo.security.jwt;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class JWTCreator {
    private static final Logger logger = LoggerFactory.getLogger(JWTCreator.class);

    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String ROLES_AUTHORITIES = "authorities";

    public static String create(String prefix, String key, JWTObject jwtObject) {
        logger.info("Criando token JWT para usuário: {}", jwtObject.getSubject());
        String token = Jwts.builder()
                .setSubject(jwtObject.getSubject())
                .setIssuedAt(jwtObject.getIssueedAT())
                .setExpiration(jwtObject.getExpiration())
                .claim(ROLES_AUTHORITIES, checkRoles(jwtObject.getRoles()))
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
        return prefix + token;
    }

    public static JWTObject create(String token, String prefix, String key)
            throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException {

        logger.info("Processando token JWT");
        JWTObject object = new JWTObject();

        if (token.startsWith(prefix)) {
            token = token.substring(prefix.length()).trim();
        }

        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(token)
                    .getBody();

            object.setSubject(claims.getSubject());
            object.setExpiration(claims.getExpiration());
            object.setIssueedAT(claims.getIssuedAt());
            object.setRoles((List<String>) claims.get(ROLES_AUTHORITIES));

            logger.info("Token JWT processado com sucesso para usuário: {}", object.getSubject());
            return object;
        } catch (Exception e) {
            logger.error("Erro ao processar token JWT: {}", e.getMessage());
            throw e;
        }
    }

    private static List<String> checkRoles(List<String> roles) {
        return roles.stream()
                .map(s -> "ROLE_".concat(s.replaceAll("^ROLE_", "")))
                .collect(Collectors.toUnmodifiableList());
    }
}
