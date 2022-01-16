package com.cmlteam.web;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class TestResponseDto {
  private final boolean result;
  private final List<TestRecordDto> records;
}
