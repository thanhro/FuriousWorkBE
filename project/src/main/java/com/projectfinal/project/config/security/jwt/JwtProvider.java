package com.projectfinal.project.config.security.jwt;

import com.projectfinal.project.config.security.services.UserPrinciple;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    @Value("${enterprise.web.jwtSecret}")
    private String jwtSecret;

    @Value("${enterprise.web.jwtExpiration}")
    private String jwtExpiration;

    public String generateJwtToken(Authentication authentication){

        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();

//        Date now = new Date();
//        Date expiryDate = new Date(now.getTime() + jwtExpiration);
//        System.out.println(now.getTime());

        return Jwts.builder()
                .setSubject(userPrinciple.getEmail())
                .setIssuedAt(new Date())
//                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getEmailFromJwtToken(String token){
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature -> Message: {} ", e);
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token -> Message: {}", e);
        } catch (ExpiredJwtException e) {
            logger.error("Expired JWT token -> Message: {}", e);
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported JWT token -> Message: {}", e);
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty -> Message: {}", e);
        }

        return false;
    }
}
