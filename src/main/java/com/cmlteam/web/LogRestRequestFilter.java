package com.cmlteam.web;

import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class LogRestRequestFilter extends GenericFilterBean {

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    // For _logged_ AJAX calls must wrap request to capture the body while letting the controllers
    // to consume the request's input stream
    if (LogRestRequestUtil.shouldLogRequest(request) && LogRestRequestUtil.isAjaxRequest(request)) {
      request = new LogRestRequestWrapper((HttpServletRequest) request);
    }

    chain.doFilter(request, response);
  }
}
