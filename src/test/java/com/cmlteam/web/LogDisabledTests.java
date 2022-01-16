package com.cmlteam.web;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogDisabledTests extends ApiTestsBase {
  @Test
  public void testPostLogs() throws Exception {
    // WHEN
    callPostEndpoint();

    // THEN
    assertEquals(0, memoryAppender.getSize());
  }
}
