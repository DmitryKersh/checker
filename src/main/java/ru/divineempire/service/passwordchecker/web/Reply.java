package ru.divineempire.service.passwordchecker.web;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class Reply {
    @NonNull
    @Getter
    private final Boolean valid;
}
