package com.example.myrh.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

@Service

public class JwtService {

    JwtService(){
        initializeKeyPair();
    }
    private KeyPair keyPair;
    //private static final String KeyPair =
      //      "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJJc3N1ZXIiOiJJc3N1ZXIiLCJVc2VybmFtZSI6IkphdmFJblVzZSIsImV4cCI6MTcwNDE5NDc2NywiaWF0IjoxNzA0MTk0NzY3fQ.AaaX-zHwoBpHKAyKlF5GtPSgEmyFibPpyT6TeyVJg14";


    private void initializeKeyPair() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC");
            keyPairGenerator.initialize(256);
            keyPair = keyPairGenerator.generateKeyPair();
        } catch (Exception e) {
            throw new RuntimeException("Error initializing ECDSA key pair", e);
        }
    }
    public String extractUserEmail(String token) {
        return extractClaim(token,Claims::getSubject) ;
    }

    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(
                                UserDetails userDetails){
        return generateToken(new HashMap<>() , userDetails);
    }
    // todo method to check if the token belongs to this User
    public boolean isTokenValid(String token , UserDetails userDetails){
        final String userName = extractUserEmail(token);
        return userName.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpirationToken(token).before(new Date());
    }

    private Date extractExpirationToken(String token) {
        return extractClaim(token,Claims::getExpiration);
    }

    public String generateToken(Map<String , Object> extractClaims,
                                UserDetails userDetails){
        if (keyPair == null) {
            throw new RuntimeException("ECDSA key pair is not initialized");
        }
        return Jwts.builder().
                setClaims(extractClaims)
                .setSubject(userDetails.
                        getUsername())
                .claim("authorities", userDetails.getAuthorities())
                // help us to check if the token is still valid or not
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*24))
                .signWith(keyPair.getPrivate(), SignatureAlgorithm.ES256)
                .compact();


    }


    // todo get all claims in the token
    private Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(keyPair.getPublic())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

//    private Key getSignInKey() {
//            byte[] keyBytes = Decoders.BASE64.decode();
//        return Keys.hmacShaKeyFor(keyBytes);
//    }
}
