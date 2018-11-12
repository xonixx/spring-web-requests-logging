package com.cmlteam.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class LogRestRequestWebInterceptorConfiguration extends WebMvcConfigurerAdapter {
  private final LogRestRequestWebInterceptor logRestRequestWebInterceptor;

  @Autowired
  public LogRestRequestWebInterceptorConfiguration(
      LogRestRequestWebInterceptor logRestRequestWebInterceptor) {
    this.logRestRequestWebInterceptor = logRestRequestWebInterceptor;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(logRestRequestWebInterceptor);
  }
}
