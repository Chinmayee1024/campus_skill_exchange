package com.chinmayee.campusskill.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.chinmayee.campusskill.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@Service

public class JwtService {
	private final SecretKey secretKey;
	private final long jwtExpiration;

	public JwtService(@Value("${app.jwt.secret}") String secret, @Value("${app.jwt.expiration}") long jwtExpiration) {

		this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
		this.jwtExpiration = jwtExpiration;
	}
	
	

	public String generateToken(User user) {
		Date now = new Date();
		Date expiration = new Date(now.getTime() + jwtExpiration);

		return Jwts.builder()
				.subject(user.getEmail())
				.claim("userId", user.getId())
				.claim("role", user.getRole())
				.issuedAt(now)
				.expiration(expiration)
				.signWith(secretKey)
				.compact(); // Converts the JWT into a single string

	}

	
	
	
	public String extractEmail(String token) {

		return extractAllClaims(token).getSubject();

	}

	private Claims extractAllClaims(String token) {

		return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload();
	}

	public boolean isTokenValid(String token, String email) {
		try {
			Claims claims = extractAllClaims(token);
			String tokenEmail = claims.getSubject();
			Date expiration = claims.getExpiration();

			return tokenEmail.equals(email) && expiration.after(new Date());

		} catch (Exception exception) {
			return false;

		}
	}

}
