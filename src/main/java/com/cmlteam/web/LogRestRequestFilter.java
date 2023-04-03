package com.cmlteam.web;

import org.springframework.web.filter.GenericFilterBean;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;

public class LogRestRequestFilter extends GenericFilterBean {

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    // For _logged_ AJAX calls must wrap request to capture the body while letting the controllers
    // consume the request's input stream
    if (LogRestRequestUtil.shouldLogRequest(request) && LogRestRequestUtil.isAjaxRequest(request)) {
      request = new LogRestRequestWrapper((HttpServletRequest) request);
    }

    chain.doFilter(request, response);
  }
}
