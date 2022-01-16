package com.cmlteam.web;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TestRecordDto {
    private final int id;
    private final String name;
    private final String email;
}
