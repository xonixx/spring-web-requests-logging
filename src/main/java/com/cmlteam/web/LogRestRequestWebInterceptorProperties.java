package com.cmlteam.web;

import org.springframework.boot.context.properties.ConfigurationProperties;

import static com.cmlteam.web.LogRestRequestWebInterceptorProperties.LOG_REQUEST_WEB_INTERCEPTOR_PROPS_ENABLED;

@ConfigurationProperties(value = LOG_REQUEST_WEB_INTERCEPTOR_PROPS_ENABLED)
public class LogRestRequestWebInterceptorProperties {
    static final String LOG_REQUEST_WEB_INTERCEPTOR_PROPS = "logRestRequestWebInterceptor";
    static final String LOG_REQUEST_WEB_INTERCEPTOR_PROPS_ENABLED = LOG_REQUEST_WEB_INTERCEPTOR_PROPS + ".enabled";
}
