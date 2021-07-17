package ru.divineempire.service.passwordchecker.web;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class Reply {
    @NonNull
    @Getter
    Boolean valid;

    @NonNull
    @Getter
    Integer successes;

    @NonNull
    @Getter
    Integer fails;

    @NonNull
    @Getter
    List<String> successfulChecksList;

    @NonNull
    @Getter
    List<String> failedChecksList;

    @NonNull
    @Getter
    List<String> errorMessages;
}
