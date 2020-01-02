package xiaoxiaoxiang.learn.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 配置客户端
        clients
                // 使用内存设置
                .inMemory()
                // client_id
                .withClient("client01")
                // client_secret
                .secret(bCryptPasswordEncoder.encode("secret"))
                // 授权类型
                .authorizedGrantTypes("authorization_code")
                // 授权范围
                .scopes("app")
                // 注册回调地址
                .redirectUris("http://www.funtl.com");
    }
//    权限控制模型
//    RBAC（Role-Based Access Control，基于角色的访问控制）
//    ACL （Access Control List，访问控制列表）
//    ABAC（Attribute-Based Access Control，基于属性的访问控制）
//    PBAC（Policy-Based Access Control，基于策略的访问控制）

//    RBAC 是什么: 权限控制模型
//         做什么:
//            who 资源所有者
//            what 能访问哪些资源
//            how 具体怎么访问
//         怎么用
//
//
}
