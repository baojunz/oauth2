package com.baojunz.oauthserver.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

@Configuration
@EnableAuthorizationServer // 声明一个认证服务器，当用此注解后，应用启动后将自动生成几个Endpoint
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

  @Autowired
  BCryptPasswordEncoder passwordEncoder;

  @Autowired
  AuthenticationManager authenticationManager;

  @Bean
  @Primary
  @ConfigurationProperties(prefix = "spring.datasource")
  public DataSource dataSource() {
    // 配置数据源
    return DataSourceBuilder.create().build();
  }

  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    // 客户端配置
    clients.withClientDetails(new JdbcClientDetailsService(dataSource()));
  }

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    // 令牌配置
    endpoints.tokenStore(new JdbcTokenStore(dataSource()));
    // 密码模式需要在OAuth2AuthorizationServer 中配置AuthenticationManager
    endpoints.authenticationManager(authenticationManager);
  }


  @Override
  public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
    /* 配置token获取合验证时的策略 */
    security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()").allowFormAuthenticationForClients();
  }
}


