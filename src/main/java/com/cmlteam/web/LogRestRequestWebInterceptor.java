package com.cmlteam.web;

import com.cmlteam.util.Util;
import com.cmlteam.web.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.Map;

/** We need to use interceptor since in plain web Filter the MultipartRequest is not parsed yet */
@Slf4j
public class LogRestRequestWebInterceptor extends HandlerInterceptorAdapter {
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    String requestMethod = request.getMethod();

    if (LogRestRequestUtil.shouldLogRequest(request)) {
      StringBuilder sb = new StringBuilder();

      sb.append(requestMethod).append(' ').append(request.getRequestURI());
      Map<String, String[]> parameterMap = request.getParameterMap();
      if (!parameterMap.isEmpty()) {
        sb.append('?');
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
          sb.append(entry.getKey())
              .append('=')
              .append(StringUtils.join(entry.getValue(), ','))
              .append('&');
        }
        sb.setLength(sb.length() - 1);
      }
      if (LogRestRequestUtil.isAjaxRequest(request)) {
        LogRestRequestWrapper requestWrapper =
            WebUtils.getNativeRequest(request, LogRestRequestWrapper.class);
        sb.append('\n').append(JsonUtil.prettyPrintJson(requestWrapper.getBody()));
      } else if (LogRestRequestUtil.isMultipartRequest(request)) {
        if (request instanceof MultipartRequest) {

          MultipartRequest multipartRequest = (MultipartRequest) request;

          sb.append('\n').append("Files:").append('\n');
          for (Iterator it = multipartRequest.getFileNames(); it.hasNext(); ) {
            String fileName = (String) it.next();
            MultipartFile file = multipartRequest.getFile(fileName);

            sb.append("fileName: ").append(file.getOriginalFilename()).append('\n');
            sb.append("fileSize: ").append(Util.renderFileSize(file.getSize())).append('\n');
          }
          sb.setLength(sb.length() - 1);
        }
      } else {
        // Some additional actions can be made upon DELETE request
      }
      log.info(sb.toString());
    }
    return super.preHandle(request, response, handler);
  }
}
