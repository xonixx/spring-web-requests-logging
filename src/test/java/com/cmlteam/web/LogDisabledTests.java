package com.cmlteam.web;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class LogDisabledTests extends ApiTestsBase {
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

    assertEquals(0, memoryAppender.getSize());
  }
}
