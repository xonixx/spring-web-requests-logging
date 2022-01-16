package com.cmlteam.web;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("test")
public class TestApi {
  @ResponseBody
  @PostMapping("postEndpoint")
  public Object postEndpoint(@RequestBody TestRequestDto request) {
    return new HashMap<>();
  }
}
