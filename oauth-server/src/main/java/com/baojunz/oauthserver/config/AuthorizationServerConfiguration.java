package com.baojunz.oauthserver.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

  @Autowired
  BCryptPasswordEncoder passwordEncoder;

  @Bean
  @Primary
  @ConfigurationProperties(prefix = "spring.datasource")
  public DataSource dataSource() {
    // 配置数据源（注意，我使用的是 HikariCP 连接池），以上注解是指定数据源，否则会有冲突
    return DataSourceBuilder.create().build();
  }

  @Bean
  public TokenStore tokenStore() {
    // 基于 JDBC 实现，令牌保存到数据
    return new JdbcTokenStore(dataSource());
  }

  @Bean
  public ClientDetailsService jdbcClientDetailsService() {
    // 基于 JDBC 实现，需要事先在数据库配置客户端信息
    return new JdbcClientDetailsService(dataSource());
  }

  /**
   * @Description: 用来配置客户端详情信息，一般使用数据库来存储或读取应用配置的详情信息.
   * @Author: baojun.z
   * @Date: 19-5-29
   */
  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//    // 内存中配置客户端
//    clients.inMemory()
//        .withClient("client")
//        .secret(passwordEncoder.encode("secret"))
//        .authorizedGrantTypes("authorization_code")
//        .scopes("all")
//        .redirectUris("http://www.baidu.com");

    // 数据库读取客户端配置
    clients.withClientDetails(jdbcClientDetailsService());
  }

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    // 设置令牌
    endpoints.tokenStore(tokenStore());
  }
}


