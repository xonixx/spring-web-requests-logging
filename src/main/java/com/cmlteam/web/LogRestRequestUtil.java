package com.cmlteam.web;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

class LogRestRequestUtil {
  private static final String[] LOGGED_METHODS = new String[] {"POST", "PUT", "PATCH", "DELETE"};

  static boolean shouldLogRequest(ServletRequest servletRequest) {
    for (String m : LOGGED_METHODS) {
      if (m.equalsIgnoreCase(((HttpServletRequest) servletRequest).getMethod())) {
        return true;
      }
    }
    return false;
  }

  static boolean isAjaxRequest(ServletRequest servletRequest) {
    return "application/json".equalsIgnoreCase(servletRequest.getContentType());
  }

  static boolean isMultipartRequest(ServletRequest servletRequest) {
    String contentType = servletRequest.getContentType();
    return contentType != null
        && (contentType.startsWith("multipart/form-data")
            || contentType.startsWith("multipart/mixed"));
  }
}
