package com.ascending.training.service;

import com.ascending.training.model.Role;
import com.ascending.training.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.Set;

@Service
public class JWTService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private final String SECRET_KEY = System.getProperty("secret.key");
    private final String ISSUER = "MinfengWu";
    private final long EXPEIRATION_TIME = 86400 * 1000;

    public String generateToken(User user){
        /*
        Step 1: decide signature algorithm
        Step 2: hard code secret key -- late ruse VM option to pass in the key
        Step 3: sign JWT with Key
        Step 4: organize our payload: Claims ---> map with predefined key, add your own custom key/value pairs
        Step 5: set claims JWT api
        Step 6: generate the token
         */

        //Step 1: decide signature algorithm
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        //Step 2: hard code secret key -- late ruse VM option to pass in the key

        //Step 3: sign JWT with Key
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        Claims claims = Jwts.claims();
        claims.setId(String.valueOf(user.getId()));
        claims.setSubject(user.getName());
        claims.setIssuedAt(new Date(System.currentTimeMillis()));
        claims.setIssuer(ISSUER);
        claims.setExpiration(new Date(System.currentTimeMillis() + EXPEIRATION_TIME));

                //set role into claims to improve effiency
        Set<Role> roles = user.getRoles();
        String allowedReadResources = "";
        String allowedCreateResources = "";
        String allowedUpdateResources = "";
        String allowedDeleteResources = "";

        for(Role role : roles){
            if (role.isAllowedRead())
                allowedReadResources = String.join(role.getAllowedResource(), allowedReadResources, ",");
            if (role.isAllowedCreate())
                allowedCreateResources = String.join(role.getAllowedResource(), allowedCreateResources, ",");
            if (role.isAllowedUpdate())
                allowedUpdateResources = String.join(role.getAllowedResource(), allowedUpdateResources, ",");
            if (role.isAllowedDelete())
                allowedDeleteResources = String.join(role.getAllowedResource(), allowedDeleteResources, ",");
        }

        logger.info("======, allowedReadResources = {}", allowedReadResources);
        logger.info("======, allowedCreateResources = {}", allowedCreateResources);
        logger.info("======, allowedUpdateResources = {}", allowedUpdateResources);
        logger.info("======, allowedDeleteResources = {}", allowedDeleteResources);

        claims.put("allowedReadResources", allowedReadResources.replaceAll(",$", ""));
        claims.put("allowedCreateResources", allowedCreateResources.replaceAll(",$", ""));
        claims.put("allowedUpdateResources", allowedUpdateResources.replaceAll(",$", ""));
        claims.put("allowedDeleteResources", allowedDeleteResources.replaceAll(",$", ""));

        //Step 4: organize our payload: Claims ---> map with predefined key, add your own custom key/value pairs
        //Step 5: set claims JWT api
                     //Set the JWT Claims
        JwtBuilder builder = Jwts.builder().setClaims(claims).signWith(signatureAlgorithm, signingKey);
        //Step 6: generate the token
                    //Builds the JWT and serializes it to a compact, URL-safe string
        String generateToken = builder.compact();
        return generateToken;
    }

    //Compare signature and get the payload if it pass
    public Claims decryptJwtToken(String token){
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .parseClaimsJws(token).getBody();

        return claims;
    }
}
