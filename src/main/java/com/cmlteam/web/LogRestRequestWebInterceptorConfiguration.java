package com.cmlteam.web;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.cmlteam.web.LogRestRequestWebInterceptorConfiguration.LOG_REQUEST_WEB_INTERCEPTOR_PROPS_ENABLED;

@Configuration
@ConditionalOnProperty(value = LOG_REQUEST_WEB_INTERCEPTOR_PROPS_ENABLED)
public class LogRestRequestWebInterceptorConfiguration {

  static final String LOG_REQUEST_WEB_INTERCEPTOR_PROPS = "logRestRequestWebInterceptor";
  static final String LOG_REQUEST_WEB_INTERCEPTOR_PROPS_ENABLED =
      LOG_REQUEST_WEB_INTERCEPTOR_PROPS + ".enabled";

  @Bean
  public LogRestRequestWebInterceptor logRestRequestWebInterceptor() {
    return new LogRestRequestWebInterceptor();
  }

  @Bean
  public LogRestRequestWebMvcConfigurer logRestRequestWebMvcConfigurer(
      LogRestRequestWebInterceptor logRestRequestWebInterceptor) {
    return new LogRestRequestWebMvcConfigurer(logRestRequestWebInterceptor);
  }

  @Bean
  public LogRestRequestFilter logRestRequestFilter() {
    return new LogRestRequestFilter();
  }
}
