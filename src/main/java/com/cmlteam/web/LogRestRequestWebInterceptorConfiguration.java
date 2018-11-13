package com.cmlteam.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import static com.cmlteam.web.LogRestRequestWebInterceptorProperties.LOG_REQUEST_WEB_INTERCEPTOR_PROPS_ENABLED;

@Configuration
@EnableConfigurationProperties(value = LogRestRequestWebInterceptorProperties.class)
@ConditionalOnProperty(value = LOG_REQUEST_WEB_INTERCEPTOR_PROPS_ENABLED)
public class LogRestRequestWebInterceptorConfiguration extends WebMvcConfigurerAdapter {
  @Autowired private final LogRestRequestWebInterceptor logRestRequestWebInterceptor;

  /*@Autowired
  public LogRestRequestWebInterceptorConfiguration(
      LogRestRequestWebInterceptor logRestRequestWebInterceptor) {
    this.logRestRequestWebInterceptor = logRestRequestWebInterceptor;
  }*/

  @Bean
  public LogRestRequestWebInterceptor logRestRequestWebInterceptor(){
    return new LogRestRequestWebInterceptor();
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(logRestRequestWebInterceptor);
  }
}
