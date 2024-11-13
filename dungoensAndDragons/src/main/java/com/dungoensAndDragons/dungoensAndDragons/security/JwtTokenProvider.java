package com.dungoensAndDragons.dungoensAndDragons.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.dungoensAndDragons.dungoensAndDragons.service.UserService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class JwtTokenProvider {

    @Value("${app.jwtSecret}")
    private String jwtSecret;

    @Value("${app.jwtExpirationMs}")
    private int jwtExpirationMs;

    @Autowired
    UserService userService;

    // Generate JWT token
    public String generateToken(Authentication authentication) {
	return Jwts.builder().setSubject(authentication.getName()).setIssuedAt(new Date())
		.setExpiration(new Date(new Date().getTime() + jwtExpirationMs))
		.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
    }

    // Validate JWT token
    public boolean validateToken(String token) {
	try {
	    Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
	    return true;
	} catch (JwtException | IllegalArgumentException e) {
	    System.err.println("Invalid JWT token: " + e.getMessage());
	}
	return false;
    }

    // Method to extract JWT token from the request's Authorization header
    public String resolveToken(HttpServletRequest request) {
	String bearerToken = request.getHeader("Authorization");
	if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
	    return bearerToken.substring(7); // Extract token after 'Bearer '
	}
	return null;
    }

    public String getUsernameFromToken(String token) {
	Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
	return claims.getSubject();
    }

//    public Authentication getAuthentication(String token) {
//	UserDetails userDetails = (UserDetails) userService.getUserByUsername(getUsernameFromToken(token));
//	return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
//    }
}
