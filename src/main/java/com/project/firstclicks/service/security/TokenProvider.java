package com.project.firstclicks.service.security;

import java.security.Key;
import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class TokenProvider {
	
	private Key key;
	
	public TokenProvider() {
		String secret = "eyJhbGciOiJIUzI1NiJ9eyJSb2xlIjoiQWRtaW4iLCJJc3N1ZXIiOiJJc3N1ZXIiLCJVc2VybmFtZSI6IkphdmFJblVzZSIsImV4cCI";
		key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
	}
	
	public String createAccessToken(Authentication authentication) {
		String role = authentication
				.getAuthorities()
				.stream()
				.findFirst()
				.orElseThrow(RuntimeException::new)
				.getAuthority();
		
		return Jwts
			.builder()
			.setSubject(authentication.getName())
			.claim("role", role)
			.signWith(key,SignatureAlgorithm.HS512)
			.setExpiration(new Date(System.currentTimeMillis() + 300000))
			.compact();
	}
}
