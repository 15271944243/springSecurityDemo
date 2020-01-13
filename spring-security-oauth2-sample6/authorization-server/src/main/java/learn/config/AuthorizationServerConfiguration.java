package learn.config;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.RSAKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.InMemoryApprovalStore;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;
import java.util.Collections;
import java.util.Map;

/**
 * 授权服务器配置
 * @author: xiaoxiaoxiang
 * @date: 2019/12/31 11:13
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private KeyPair keyPair;

    private static final String KEY_ID = new String(Base64.getEncoder().encode(KeyGenerators.secureRandom(32).generateKey()));

    /**
     * 也可以在配置文件里进行配置
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
                // 使用内存设置
                .inMemory()
                // client_id
                .withClient("client01")
                // client_secret
                .secret(bCryptPasswordEncoder.encode("secret01"))
                // 授权类型
                .authorizedGrantTypes("authorization_code")
                // 授权范围
                .scopes("app", "openid", "profile", "email", "address", "phone")
                // 自动批准
                .autoApprove("app")
                .redirectUris("http://test.xhqb.com", "https://www.baidu.com");
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        /**
         * 由于`/oauth/token_key`、`/oauth/check_token`默认的访问权限是denyAll()
         * 这里要改成认证成功后可访问权限:isAuthenticated()
         */
        security.tokenKeyAccess("isAuthenticated()").checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .accessTokenConverter(accessTokenConverter())
                .tokenStore(tokenStore())
                .approvalStore(approvalStore());
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    /**
     * JwtAccessTokenConverter默认不支持kid,需要自行添加
     * @return
     */
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        Map<String, String> customHeaders = Collections.singletonMap("kid", KEY_ID);
        return new JwtCustomAccessTokenConverter(customHeaders, this.keyPair);
    }

    @Bean
    public JWKSet jwkSet() {
        RSAKey.Builder builder = new RSAKey.Builder((RSAPublicKey) this.keyPair.getPublic())
                .keyUse(KeyUse.SIGNATURE)
                .algorithm(JWSAlgorithm.RS256)
                .keyID(KEY_ID);
        return new JWKSet(builder.build());
    }
    @Bean
    public ApprovalStore approvalStore() {
        return new InMemoryApprovalStore();
    }
}
