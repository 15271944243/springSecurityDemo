package learn.config;

import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaSigner;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.util.JsonParser;
import org.springframework.security.oauth2.common.util.JsonParserFactory;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.util.HashMap;
import java.util.Map;

/**
 * JwtAccessTokenConverter默认不支持kid,需要自行添加
 * refer to https://www.baeldung.com/spring-security-oauth2-jws-jwk#10-adding-the-kid-value-to-the-jwt-header
 * @author: xiaoxiaoxiang
 * @date: 2020/1/8 19:36
 */
public class JwtCustomAccessTokenConverter extends JwtAccessTokenConverter {

    private Map<String, String> customHeaders = new HashMap<>();

    final RsaSigner signer;

    private JsonParser objectMapper = JsonParserFactory.create();

    /**
     * First of all, we'll need to:
     * - configure the parent class as we've been doing, setting up the KeyPair we configured
     * - obtain a Signer object that uses the private key from the keystore
     * - of course, a collection of custom headers we want to add to the structure
     * @param customHeaders
     * @param keyPair
     */
    public JwtCustomAccessTokenConverter(Map<String, String> customHeaders, KeyPair keyPair) {
        super();
        super.setKeyPair(keyPair);
        this.signer = new RsaSigner((RSAPrivateKey) keyPair.getPrivate());
        this.customHeaders = customHeaders;
    }

    /**
     * Now we'll override the encode method.
     * Our implementation will be the same as the parent one,
     * with the only difference that we'll also pass the custom headers when creating the String token:
     * @param accessToken
     * @param authentication
     * @return
     */
    @Override
    protected String encode(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        String content;
        try {
            content = this.objectMapper.formatMap(getAccessTokenConverter().convertAccessToken(accessToken, authentication));
        } catch (Exception ex) {
            throw new IllegalStateException(
                    "Cannot convert access token to JSON", ex);
        }
        String token = JwtHelper.encode(content, this.signer, this.customHeaders).getEncoded();
        return token;
    }
}
