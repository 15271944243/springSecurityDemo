package learn.config;

import org.springframework.security.jwt.crypto.sign.RsaSigner;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * refer to https://www.baeldung.com/spring-security-oauth2-jws-jwk#10-adding-the-kid-value-to-the-jwt-header
 * @author: xiaoxiaoxiang
 * @date: 2020/1/8 19:36
 */
public class JwtCustomAccessTokenConverter extends JwtAccessTokenConverter {

    private Map<String, String> customHeaders = new HashMap<>();

    final RsaSigner signer;

    public JwtCustomAccessTokenConverter(Map<String, String> customHeaders, KeyPair keyPair) {
        super();
        super.setKeyPair(keyPair);
        this.signer = new RsaSigner((RSAPrivateKey) keyPair.getPrivate());
        this.customHeaders = customHeaders;
    }
}
