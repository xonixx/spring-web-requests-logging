package com.cmlteam.web;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test-enabled")
public class LogEnabledTests extends ApiTestsBase {
  @Test
  public void testPostLogs() throws Exception {
    // WHEN
    callPostEndpoint();

    // THEN
    assertEquals(1, memoryAppender.getSize());
    List<ILoggingEvent> loggedEvents = memoryAppender.getLoggedEvents();
    ILoggingEvent event = loggedEvents.get(0);
    assertEquals(Level.INFO, event.getLevel());
    assertEquals(
        "POST /test/postEndpoint?aaa=bbb\n"
            + "{\n"
            + "  \"id\" : 123,\n"
            + "  \"payload\" : \"Some test body...\",\n"
            + "  \"bool\" : true,\n"
            + "  \"val\" : null\n"
            + "}",
        event.getMessage());
  }

}
