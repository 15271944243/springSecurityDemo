package xiaoxiaoxiang.learn.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 配置认证服务器
 配置客户端信息：ClientDetailsServiceConfigurer
 inMemory：内存配置
 withClient：客户端标识
 secret：客户端安全码
 authorizedGrantTypes：客户端授权类型
 scopes：客户端授权范围
 redirectUris：注册回调地址
 配置 Web 安全
 通过 GET 请求访问认证服务器获取授权码
 端点：/oauth/authorize
 通过 POST 请求利用授权码访问认证服务器获取令牌
 端点：/oauth/token
 附：默认的端点 URL
 /oauth/authorize：授权端点
 /oauth/token：令牌端点
 /oauth/confirm_access：用户确认授权提交端点
 /oauth/error：授权服务错误信息端点
 /oauth/check_token：用于资源服务访问的令牌解析端点
 /oauth/token_key：提供公有密匙的端点，如果你使用 JWT 令牌的话
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter{

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 配置客户端
        clients
                // 使用内存设置
                .inMemory()
                // client_id
                .withClient("client")
                // client_secret
                .secret("secret")
                // 授权类型
                .authorizedGrantTypes("authorization_code")
                // 授权范围
                .scopes("app")
                // 注册回调地址
                .redirectUris("http://www.funtl.com");
    }
}
