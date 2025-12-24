package com.flintzy.socialmedia.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
//@ConfigurationProperties(prefix = "jwt")
public class JwtUtil {
	
	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiration}")
	private long expiration;

	public String generateToken(String email) {
		return Jwts.builder()
				.setSubject(email.trim().toLowerCase())
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(Keys.hmacShaKeyFor(secret.getBytes()), SignatureAlgorithm.HS256)
				.compact();
	}

	public String extractEmial(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(secret.getBytes())
				.build()
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
	}
}
