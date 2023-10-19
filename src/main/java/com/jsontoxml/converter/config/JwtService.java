package com.jsontoxml.converter.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private static final String SECRET_KEY = "6A576E5A7234743777217A25432A462D4A614E645267556B5870327335763878";

    /*
     * IN order to extracting, mainpulating,updating etc the token we need add some new dependencies
     * jjwt-api,jjwt-impl,jjwt-jackson */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);

    }

    /*
    Before Step 2:(iii)
     * TO Extract single claim*/

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /*
    Before Step 2:
            (i)
     * Before extractUserName methos we need to etract all the claim and another method to extract single claim*/

    /*SigningKey
    *Signing keys are used to sign ID tokens, access tokens, SAML assertions, and WS-Fed assertions sent to your application or API.
    *
    * The signing key is a JSON web key (JWK) that contains a well-known public key used to validate the signature of a signed JSON web token (JWT).
    *
    * WORKING:
    * When a user signs in to your application, we create a token that contains information about the user and sign the token using its private key before we send it back to your application. Auth0 secures the private key, which is unique per tenant.
   To verify that the token is valid and originated from Auth0, your application validates the token’s signature using the public key. We provide other application security key management capabilities through both our Dashboard and Management API.

Auth0 will notify you periodically if you haven't rotated your key in more than 365 days. Auth0 recommends that you rotate keys regularly to ensure that in case of a security breach you will be ready to take the actions needed.
    *
    *
    * when we try to create , decode  a token we need to use the signing key*/
    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey()) // In this we need sign the token so we need to create the key
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


    /*
    Before Step 2
        (ii)
    * this method will decode the secret key and return the secret key
    *
    * Creates a new SecretKey instance for use with HMAC-SHA algorithms based on the specified key byte array.
    Params:
    bytes – the key byte array
    Returns:
    a new SecretKey instance for use with HMAC-SHA algorithms based on the specified key byte array.
    Throws:
    WeakKeyException – if the key byte array length is less than 256 bits (32 bytes) as mandated by the JWT JWA Specification (RFC 7518, Section 3.2) */
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }


    /*
     * TO GENERATE TOKEN
     * */

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts.builder().setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256) /*The getSignInKey method will give the signature*/
                .compact(); /* It is the method which generate and return the token in response*/

    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

}
