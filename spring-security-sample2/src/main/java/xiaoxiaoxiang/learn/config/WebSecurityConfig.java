package xiaoxiaoxiang.learn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2019/12/30 11:09
 */

/**
 * @EnableWebSecurity 注解使得 SpringMVC 集成了 Spring Security 的 web 安全支持;
 * 另外，WebSecurityConfig 配置类同时集成了 WebSecurityConfigurerAdapter,
 * 重写了其中的特定方法，用于自定义 Spring Security 配置;
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * configure(HttpSecurity) 定义了哪些 URL 路径应该被拦截,
     * "/", "/home"允许所有人访问,"/login"作为登录入口,"logout"作为登出入口,也被允许访问,
     * 而剩下的 “/hello” 则需要登陆后才可以访问
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/home").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    /**
     * UserDetailsService 在内存中配置一个用户,
     * user/password 分别是用户名和密码,这个用户拥有 USER 角色
     * @return
     */
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("password")
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }
}
