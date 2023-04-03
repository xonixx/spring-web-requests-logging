package com.cmlteam.web;

import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

class LogRestRequestWrapper extends HttpServletRequestWrapper {

  private final String body;

  LogRestRequestWrapper(HttpServletRequest request) throws IOException {
    super(request);
    body = new String(request.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
  }

  @Override
  public ServletInputStream getInputStream() throws IOException {
    final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body.getBytes());
    return new ServletInputStream() {
      @Override
      public int read() throws IOException {
        return byteArrayInputStream.read();
      }

      @Override
      public boolean isFinished() {
        return false;
      }

      @Override
      public boolean isReady() {
        return true;
      }

      @Override
      public void setReadListener(ReadListener listener) {}
    };
  }

  @Override
  public BufferedReader getReader() throws IOException {
    return new BufferedReader(new InputStreamReader(getInputStream()));
  }

  String getBody() {
    return body;
  }
}
