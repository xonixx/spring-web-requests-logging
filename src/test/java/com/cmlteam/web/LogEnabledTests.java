package com.cmlteam.web;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test-enabled")
public class LogEnabledTests extends ApiTestsBase {
  @Test
  public void testPostLogs() throws Exception {
    // WHEN
    mockMvc
        .perform(
            post("/test/postEndpoint")
                .content(JSON_REQUEST)
                .contentType(MediaType.APPLICATION_JSON))
        // THEN
        .andExpect(status().isOk());

    assertEquals(1, memoryAppender.getSize());
    List<ILoggingEvent> loggedEvents = memoryAppender.getLoggedEvents();
    ILoggingEvent event = loggedEvents.get(0);
    assertEquals(Level.INFO, event.getLevel());
    assertEquals(
        "POST /test/postEndpoint\n"
            + "{\n"
            + "  \"id\" : 123,\n"
            + "  \"payload\" : \"Some test body...\",\n"
            + "  \"bool\" : true,\n"
            + "  \"val\" : null\n"
            + "}",
        event.getMessage());
  }
}
