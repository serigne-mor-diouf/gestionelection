package com.dioufserignemor.gmail.gestionelection.securite;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

    @Value("${app.jwt.secret}") // Assurez-vous d'avoir une propriété app.jwt.secret définie dans votre application.properties ou application.yml
    private String secretKey;

    @Value("${app.jwt.expiration-in-ms}")
    private long validityInMilliseconds;

    public String generateToken(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public String resolveToken(jakarta.servlet.http.HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            // Gérer les exceptions (par exemple, TokenExpiredException, SignatureException, etc.)
            return false;
        }
    }

    public Authentication getAuthentication(String token) {
    Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();

    String username = claims.getSubject();
    Collection<?> roles = claims.get("roles", Collection.class);

    Collection<SimpleGrantedAuthority> authorities = null;
    if (roles != null) {
        authorities = roles.stream()
                .map(role -> new SimpleGrantedAuthority((String) role))
                .collect(Collectors.toList());
    }

    return new UsernamePasswordAuthenticationToken(username, null, authorities);
}
}
