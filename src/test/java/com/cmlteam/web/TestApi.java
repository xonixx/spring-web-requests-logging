package com.cmlteam.web;

import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("test")
public class TestApi {
  @ResponseBody
  @PostMapping("postEndpoint")
  public TestResponseDto postEndpoint(@RequestBody TestRequestDto request) {
    return new TestResponseDto(
        true,
        Arrays.asList(
            new TestRecordDto(100, "John Smith", "john@example.com"),
            new TestRecordDto(101, "Jane Smith", "jane@gmail.com"),
            new TestRecordDto(102, "John Doe", null)));
  }
}
