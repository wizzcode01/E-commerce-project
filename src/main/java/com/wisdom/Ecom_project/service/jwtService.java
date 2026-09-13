package com.wisdom.Ecom_project.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class jwtService {

    private String secretKey = ""; // A variable to hold our server's master security password.
    public jwtService(){
        try{
            // a built int java security tool. we tell it to use HmacSHA256 algorithm
            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
            // generate a strong, highly random cryptographic key object using keygen
            SecretKey sk = keyGen.generateKey();
            // turn that binary key object into a safe, readable text base64 so we can store it in our variable
            secretKey = Base64.getEncoder().encodeToString(sk.getEncoded());
        }catch(NoSuchAlgorithmException e){
            throw new RuntimeException(e);
        }
    }

    public String generateToken(String userEmail){
       // create a empty map to hold custom claims like role: admin or user
        Map<String, Object> claims = new HashMap<>();

        // Jwts.builder() is a builder tool provided by io.jsonwebtoken library
        return Jwts.builder()
                .claims()// open up the claims configurator
                .add(claims)// add our map of custom claims (currently empty).
                .subject(userEmail)// set the standard "sub" claim to the user email or username
                .issuedAt(new Date(System.currentTimeMillis()))// setting the time
                .expiration(new Date(System.currentTimeMillis() + 60 * 60 *30))// setting time expiration
                .and() // switches back to the main builder setting
                .signWith(getKey()) // we use our secret key to seal it
                .compact(); //squishes all this JSON data into a single long string seperated by dots
    }

    private SecretKey getKey(){
        // JJWT library cannot use plain text so we decode it back to raw bytes.
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        // we hand those bytes to the keys helper to turn them back into a secure cryptographic key object
        return Keys.hmacShaKeyFor(keyBytes);
    }
    // when a request comes in we have to read the token. these three methods works hand in hand
    public static String extractUserEmail(String token){
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver){
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parser()// initializes the library decoding machine for the system to unpack the token
                .verifyWith(getKey())// this reads the cryptographic key if it is valid
                .build()// this finishes configuring the parsing engine and compiles it to immutable execution object
                .parseSignedClaims(token) // returns claims that holds unpacked decoded key values stored in the token
                .getPayload();
    }

//    public String extractUsername(String token){
//        Claims claims = extractAllClaims(token);
//         return claims.getSubject();
//    }

    public Date extractExpireDate(String token){
        Claims claims = extractAllClaims(token);
        return claims.getExpiration();
    }

    public static boolean validateToken(String token, UserDetails userDetails){
        final String userEmail = extractUserEmail(token);
        return (userEmail.equals(userDetails.getUsername()));
    }

    private boolean isTokenExpired(String token){
        return extractExpireDate(token).before(new Date());
    }

 }
