package com.cmlteam.web;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test-enabled")
public class LogEnabledTests extends ApiTestsBase {

  @Test
  public void testPostLogs() throws Exception {
    // WHEN
    callPostEndpoint();

    // THEN
    checkSingleLogAdded(
        "POST /test/postEndpoint?aaa=bbb\n"
            + "{\n"
            + "  \"id\" : 123,\n"
            + "  \"payload\" : \"Some test body...\",\n"
            + "  \"bool\" : true,\n"
            + "  \"val\" : null\n"
            + "}");
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

    checkSingleLogAdded(
        "POST /test/postUploadEndpoint?aaa=bbb\n"
            + "Files:\n"
            + "fileName: hello.txt\n"
            + "fileSize: 13 B");
  }
}
