package com.cmlteam.web;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.cmlteam.web.JsonUtil.json;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ApiTestsBase {
  @Autowired MockMvc mockMvc;
  static final int TEST_ID = 123;
  static final String TEST_FILE_NAME = "hello.txt";
  static final String JSON_REQUEST =
      json()
          .add("id", TEST_ID)
          .add("payload", "Some test body...")
          .add("bool", true)
          .add("val", (Object) null)
          .toString();

  private static final String MEMORY_APPENDER = "memoryAppender";

  MemoryAppender memoryAppender;

  @BeforeEach
  public void setup() {
    Logger logger = (Logger) LoggerFactory.getLogger("com.cmlteam.web");
    if (logger.getAppender(MEMORY_APPENDER) == null) {
      memoryAppender = new MemoryAppender();
      memoryAppender.setName(MEMORY_APPENDER);
      memoryAppender.setContext((LoggerContext) LoggerFactory.getILoggerFactory());
      logger.setLevel(Level.DEBUG);
      logger.addAppender(memoryAppender);
      memoryAppender.start();
    }

    memoryAppender = (MemoryAppender) logger.getAppender(MEMORY_APPENDER);
    memoryAppender.reset();
  }

  protected void callPostEndpoint() throws Exception {
    // WHEN
    mockMvc
        .perform(
            post("/test/postEndpoint?aaa=bbb")
                .content(JSON_REQUEST)
                .contentType(MediaType.APPLICATION_JSON))
        // THEN
        .andExpect(status().isOk());
  }

  protected void checkSingleLogAdded(String expectedText) {
    assertEquals(1, memoryAppender.getSize());
    List<ILoggingEvent> loggedEvents = memoryAppender.getLoggedEvents();
    ILoggingEvent event = loggedEvents.get(0);
    assertEquals(Level.INFO, event.getLevel());
    assertEquals(expectedText, event.getMessage());
  }
}
