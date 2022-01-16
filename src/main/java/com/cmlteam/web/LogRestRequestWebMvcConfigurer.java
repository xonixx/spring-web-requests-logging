package com.cmlteam.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RequiredArgsConstructor
public class LogRestRequestWebMvcConfigurer implements WebMvcConfigurer {
  private final LogRestRequestWebInterceptor logRestRequestWebInterceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(logRestRequestWebInterceptor);
  }
}
