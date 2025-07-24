package com.mla.socialchristian.security;

import com.google.gson.Gson;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class JWTCreator {
    public static final String HEADER_AUTHORIZATION = "Authorization";

    public static String create(String key, LoggedUserModel loggedUserModel) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        var sub = TokenCriptoSub.encrypt(loggedUserModel.toString());

        SecretKey secret = Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .subject(sub)
                .issuedAt(loggedUserModel.getIssuedAt())
                .expiration(loggedUserModel.getExpiration())
                .signWith(secret ,Jwts.SIG.HS512)
                .compact();
    }

    public static LoggedUserModel create(String token, String key) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        SecretKey secret = Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));
        var claims = Jwts.parser().verifyWith(secret).build().parseSignedClaims(token).getPayload();

        var jsonContent = TokenCriptoSub.decrypt(claims.getSubject());
        return new Gson().fromJson(jsonContent, LoggedUserModel.class);
    }
}
