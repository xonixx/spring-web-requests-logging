package com.cmlteam.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("test")
public class TestApi {
  @ResponseBody
  @PostMapping("postEndpoint")
  public ResponseEntity<?> postEndpoint(@RequestBody TestRequestDto request) {
    return (ApiTestsBase.TEST_ID == request.getId()
            ? ResponseEntity.ok()
            : ResponseEntity.badRequest())
        .build();
  }

  @PostMapping(path = "/postUploadEndpoint")
  public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
    return (file.isEmpty()
            ? ResponseEntity.notFound()
            : ApiTestsBase.TEST_FILE_NAME.equals(file.getOriginalFilename())
                ? ResponseEntity.ok()
                : ResponseEntity.badRequest())
        .build();
  }
}
