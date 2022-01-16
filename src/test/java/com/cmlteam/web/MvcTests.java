package com.cmlteam.web;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static com.cmlteam.web.JsonUtil.json;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MvcTests extends ApiTestsBase {

  private static final String JSON_REQUEST =
      json().add("id", 123).add("payload", "Some test body...").toString();

  @Test
  public void testPostLogs() throws Exception {
    mockMvc
        .perform(
            post("/test/postEndpoint")
                .content(JSON_REQUEST)
                .contentType(MediaType.APPLICATION_JSON))
        // THEN
        .andExpect(status().isOk());
  }
}
