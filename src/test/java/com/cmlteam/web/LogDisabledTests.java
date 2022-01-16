package com.cmlteam.web;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class LogDisabledTests extends ApiTestsBase {
  @Test
  public void testPostLogs() throws Exception {
    // WHEN
    callPostEndpoint();

    // THEN
    assertEquals(0, memoryAppender.getSize());
  }

  @Test
  public void testMultipartUpload() throws Exception {
    // GIVEN
    MockMultipartFile file =
        new MockMultipartFile(
            "file", TEST_FILE_NAME, MediaType.TEXT_PLAIN_VALUE, "Hello, World!".getBytes());

    // WHEN
    mockMvc
        .perform(multipart("/test/postUploadEndpoint?aaa=bbb").file(file))
        // THEN
        .andExpect(status().isOk());

    assertEquals(0, memoryAppender.getSize());
  }
}
