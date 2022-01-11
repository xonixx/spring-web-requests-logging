package com.cmlteam.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static com.cmlteam.web.LogRestRequestWebInterceptorConfiguration.LOG_REQUEST_WEB_INTERCEPTOR_PROPS_ENABLED;

@Configuration
@Import(AppConfiguration.class)
@ConditionalOnProperty(value = LOG_REQUEST_WEB_INTERCEPTOR_PROPS_ENABLED)
public class LogRestRequestWebInterceptorConfiguration implements WebMvcConfigurer {

  static final String LOG_REQUEST_WEB_INTERCEPTOR_PROPS = "logRestRequestWebInterceptor";
  static final String LOG_REQUEST_WEB_INTERCEPTOR_PROPS_ENABLED =
      LOG_REQUEST_WEB_INTERCEPTOR_PROPS + ".enabled";

  // In this case autowired in constructor doesn't work
  @Autowired private LogRestRequestWebInterceptor logRestRequestWebInterceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(logRestRequestWebInterceptor);
  }
}
