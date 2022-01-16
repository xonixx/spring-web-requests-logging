package com.cmlteam.web;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;

import static com.cmlteam.web.JsonUtil.json;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MvcTests extends ApiTestsBase {

  private static final String JSON_REQUEST =
      json()
          .add("id", 123)
          .add("payload", "Some test body...")
          .add("bool", true)
          .add("val", (Object) null)
          .toString();

  private static MemoryAppender memoryAppender;

  @BeforeAll
  public static void setup() {
    Logger logger = (Logger) LoggerFactory.getLogger("com.cmlteam.web");
    memoryAppender = new MemoryAppender();
    memoryAppender.setContext((LoggerContext) LoggerFactory.getILoggerFactory());
    logger.setLevel(Level.DEBUG);
    logger.addAppender(memoryAppender);
    memoryAppender.start();
  }

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

    System.out.println(memoryAppender.getSize());
  }
}
