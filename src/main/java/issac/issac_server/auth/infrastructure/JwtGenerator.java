package issac.issac_server.auth.infrastructure;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import issac.issac_server.user.domain.Role;
import issac.issac_server.user.domain.User;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtGenerator {

    public String generateToken(Key SECRET, Long ACCESS_EXPIRATION, User user) {
        Long now = System.currentTimeMillis();

        return Jwts.builder()
                .setHeader(createHeader())
                .setClaims(createClaims(user))
                .setSubject(String.valueOf(user.getId()))
                .setExpiration(new Date(now + ACCESS_EXPIRATION))
                .signWith(SECRET, SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateGuestToken(Key SECRET, Long ACCESS_EXPIRATION) {
        Long now = System.currentTimeMillis();

        return Jwts.builder()
                .setHeader(createHeader())
                .setClaims(createGuestClaims())
                .setSubject("guest")
                .setExpiration(new Date(now + ACCESS_EXPIRATION))
                .signWith(SECRET, SignatureAlgorithm.HS256)
                .compact();
    }

    private Map<String, Object> createHeader() {
        Map<String, Object> header = new HashMap<>();
        header.put("typ", "JWT");
        header.put("alg", "HS256");
        return header;
    }

    private Map<String, Object> createClaims(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("Identifier", user.getId().toString());
        claims.put("Role", user.getRole());
        return claims;
    }

    private Map<String, Object> createGuestClaims() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("Role", Role.GUEST);
        return claims;
    }


    public String generateAppleClientSecret(String teamId, String clientId, String keyId, String privateKey){

        // 현재 시간과 만료 시간 설정 (최대 6개월 유효)
        long now = System.currentTimeMillis() / 1000;
        long expiration = now + (24 * 60 * 60); // 1일

        // privateKey를 ECPrivateKey 객체로 변환
        PrivateKey privateKeyFromString = getPrivateKeyFromString(privateKey);

        // JWT 생성
        return  Jwts.builder()
                .setIssuer(teamId)              // 발급자 (Team ID)
                .setIssuedAt(new Date(now * 1000)) // 발급 시간
                .setExpiration(new Date(expiration * 1000))
                .setAudience("https://appleid.apple.com")
                .setSubject(clientId)
                .signWith(privateKeyFromString, SignatureAlgorithm.ES256)
                .setHeaderParam("kid", keyId)
                .setHeaderParam("alg", "ES256")
                .compact();
    }

    private PrivateKey getPrivateKeyFromString(String privateKeyString)  {
        try {
            byte[] decodedKey = Base64.getDecoder().decode(privateKeyString);

            KeyFactory keyFactory = KeyFactory.getInstance("EC");
            return keyFactory.generatePrivate(new java.security.spec.PKCS8EncodedKeySpec(decodedKey));
        } catch (Exception e) {
            // 예외 처리: private key를 ECPrivateKey로 변환 중 발생한 예외 처리
            System.err.println("Error loading private key: " + e.getMessage());
            e.printStackTrace();
            return null; // 필요시 null 반환 또는 다른 방식으로 처리
        }
    }


}
