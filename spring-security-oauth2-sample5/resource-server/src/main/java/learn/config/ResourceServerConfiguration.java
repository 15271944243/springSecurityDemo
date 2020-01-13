package learn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.jwk.JwkTokenStore;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2019/12/31 14:31
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    /**
     * 根据官网的java config配置无效,why?
     * @param http
     * @throws Exception
     */
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                    .anyRequest().authenticated()
//                .and()
//                .oauth2ResourceServer()
//                    .jwt()
//                        .jwkSetUri("http://127.0.0.1:8079/auth/.well-known/jwks.json");
//    }

//    @Override
//    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//        resources.tokenStore(tokenStore());
////        resources.tokenServices(tokenServices());
//    }

//    @Bean
//    public RemoteTokenServices tokenServices() {
//        final RemoteTokenServices tokenServices = new RemoteTokenServices();
//        tokenServices.setCheckTokenEndpointUrl("http://127.0.0.1:8079/auth/oauth/check_token");
//        tokenServices.setClientId("client01");
//        tokenServices.setClientSecret("secret01");
//        return tokenServices;
//    }
//
//    @Bean
//    public TokenStore tokenStore() {
//        return new JwkTokenStore();
//    }
}
