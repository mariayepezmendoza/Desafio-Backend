/**
 * 
 */
package com.maria.api.security.config;

/**
 * @author Maria
 *
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration 
public class ConfigOAuth2 extends AuthorizationServerConfigurerAdapter{
	
	   private String clientId = "pixeltrice";
	   private String clientSecret = "pixeltrice-secret-key";
	   private String privateKey = "-----BEGIN RSA PRIVATE KEY-----\r\n"
	   		+ "MIIEowIBAAKCAQEA0We9vTAks97L5kdi1tZC/HY4reZ4vsBqH2u+48/IgPCehhtt\r\n"
	   		+ "gtL4/FhV7ImK9LamNtkATjZoyAJjUMea7+Vnol3jKiKYAKTixoD8DDKR/Bgp1aAU\r\n"
	   		+ "MdqVlmE8SEf4CaZMuOU8aFumV+3DTzRifZY6kYHABo2swsHSrszZLKMq/YklslBn\r\n"
	   		+ "X/G8coI5OKANByVY5YEKTNO2DfCqlh5lhu1FLd04gAz7muFEIxTXbaSfen5F4fg1\r\n"
	   		+ "Y2Hw9Okia/ZqsZKi92wjy7XCz7ooZpG6J4/qN5D80y2Nfr/E4ObOaRTadZdeOHQD\r\n"
	   		+ "VWqPhsR7WM2Rrm7LRQSPNEzehu2YKAnOTtE2YwIDAQABAoIBAAer9k6VEfMpCW1R\r\n"
	   		+ "VjSqut+G8UKlu+y5Heoyx1aPSTg4GIavQI7XbdnI3rUAwP+AJYzC3HkBE0uokrzl\r\n"
	   		+ "4y5AyFAxgl4L0GfNoqVFGnbT73c952J1L+g3qt1MiKnZQSQRXKQ1ecYU/X4hLtOb\r\n"
	   		+ "BrS1KUYla8SRsi+cbDyNMrOo8q1BJQxKfRetwXTmrw34aY1vfrg1RyMx/xsgnCjC\r\n"
	   		+ "NZZtLJ4dY70caqcsvH8rzgp37mvmsn9nQj9DNmC8lrLiKRA+690lNhvZz9tuIP6b\r\n"
	   		+ "cUSiaMTXjBZWB3xdJIqc/9yWfCDCJTNqq/5gssPszYf8AxxlBfvC5amq7ZbyKJ1Y\r\n"
	   		+ "czTGNsECgYEA9BZMynoekEujLsbvzR4JgZm9+AHpO9TVYps5mFHI7fBNyau+NwUM\r\n"
	   		+ "KKan6NsTCbfTWjsR1QgW0ldT/nx6NZMqeBJ/kR02COr/s7sIVmxkQxwRfrhnZp3+\r\n"
	   		+ "CAxk9bX+6QNEsm6H5j7k8RGIJlWYeZpkXK57Q6TqvX9WWaTNNIA+fEMCgYEA26Ad\r\n"
	   		+ "ey/xi52G3nIr+3mu9pFi98zEfOAbdr93Q32QWCtTvXaR9w+pADNbbFuN+LlFsYg3\r\n"
	   		+ "+svxs63K8CxhqgpBxZiTFv5CxPTUbSDGSL9c+dHJ0NsbrpDvs6/FgD9By5Hanmar\r\n"
	   		+ "q4euVVryLlTSdVRR1B2IZZ9QS7Eloblk2F7Jy2ECgYEA77by5at3Vskh0u/HVI/a\r\n"
	   		+ "UDCYU/g0gfEtO7EsqD4x8hQdoSENJ2XKoiXCI7r7gIDLjgMTKT/PgfYH5JeFQhZt\r\n"
	   		+ "EA1CwVaJ2cpJI89cidvbv9jSCby6uBoUAP2DLZj2ZwsxrXdF+kbseyB3sQZBP7P0\r\n"
	   		+ "Ot2U60MM0tw7BsEGUzhbpZcCgYARB9eBZ/RZGTwOH9hdRdBVemysPoq2DELU8H/1\r\n"
	   		+ "EdWRoLp7pKgo+mPmihT60BawRN2sqJ1+qzXw8vcbrLVeFDGJew1c6RaQY1Pdqn71\r\n"
	   		+ "wTEahjQaKAN7g+2yh1zPVxhpCsRjKB8DzB+uqsDA6v7u7jzyL+AV0UbOt8vtQI0b\r\n"
	   		+ "Yi5PwQKBgEH53rwaQYTp2IOC82WgyUYvVLD8RCYhCr3Gp8n4NWEF/JqxfFFOAuQU\r\n"
	   		+ "l0CzlEFHsKIqzI7vftfKm5BKT2AeOB+QFZRfE4DVR7JBuFlOnE9Ch8F7mXI6OE8Q\r\n"
	   		+ "TSOeh1RizQET/15r5cgmhvAyIPcPhE/pHQ4o9vKtTfZLbmIrkjMQ\r\n"
	   		+ "-----END RSA PRIVATE KEY-----";
	   private String publicKey = "-----BEGIN PUBLIC KEY-----\r\n"
	   		+ "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA0We9vTAks97L5kdi1tZC\r\n"
	   		+ "/HY4reZ4vsBqH2u+48/IgPCehhttgtL4/FhV7ImK9LamNtkATjZoyAJjUMea7+Vn\r\n"
	   		+ "ol3jKiKYAKTixoD8DDKR/Bgp1aAUMdqVlmE8SEf4CaZMuOU8aFumV+3DTzRifZY6\r\n"
	   		+ "kYHABo2swsHSrszZLKMq/YklslBnX/G8coI5OKANByVY5YEKTNO2DfCqlh5lhu1F\r\n"
	   		+ "Ld04gAz7muFEIxTXbaSfen5F4fg1Y2Hw9Okia/ZqsZKi92wjy7XCz7ooZpG6J4/q\r\n"
	   		+ "N5D80y2Nfr/E4ObOaRTadZdeOHQDVWqPhsR7WM2Rrm7LRQSPNEzehu2YKAnOTtE2\r\n"
	   		+ "YwIDAQAB\r\n"
	   		+ "-----END PUBLIC KEY-----";
	   
	   @Autowired
	   @Qualifier("authenticationManagerBean")
	   private AuthenticationManager authenticationManager;
	   
	   @Autowired
	   PasswordEncoder passwordEncoder;
	   
	   @Bean
	   public JwtAccessTokenConverter tokenEnhancer() {
	      JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
	      converter.setSigningKey(privateKey);
	      converter.setVerifierKey(publicKey);
	      return converter;
	   }
	   
	   @Bean
	   public JwtTokenStore tokenStore() {
	      return new JwtTokenStore(tokenEnhancer());
	   }
	   
	   @Override
	   public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
	      endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore())
	      .accessTokenConverter(tokenEnhancer());
	   }
	   @Override
	   public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
	      security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
	   }
	   @Override
	   public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
	      clients.inMemory().withClient(clientId).secret(passwordEncoder.encode(clientSecret)).scopes("read", "write")
	         .authorizedGrantTypes("password", "refresh_token").accessTokenValiditySeconds(20000)
	         .refreshTokenValiditySeconds(20000);

	   }

}