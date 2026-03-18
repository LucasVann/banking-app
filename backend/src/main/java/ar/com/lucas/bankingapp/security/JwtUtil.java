package ar.com.lucas.bankingapp.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private long expiration;

	private SecretKey getKey() {
		return Keys.hmacShaKeyFor(secret.getBytes());
	}

	public String generateToken(String username) {
		return Jwts.builder().subject(username).issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + expiration)).signWith(getKey()).compact();
	}

	public String extractUsername(String token) {
		return Jwts.parser().verifyWith(getKey()).build().parseSignedClaims(token).getPayload().getSubject();
	}

	public boolean isValid(String token) {
		try {
			Jwts.parser().verifyWith(getKey()).build().parseSignedClaims(token);
			return true;
		} catch (JwtException e) {
			return false;
		}
	}
}