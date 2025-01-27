package com.portfolio.api.models;

import lombok.Builder;
import lombok.Value;
import org.jetbrains.annotations.NotNull;

import javax.validation.Valid;
import java.time.Instant;

@Builder(toBuilder = true)  // Builder to be able to modify the class afterward
@Value                      // Create a private constructor, equals, hashCode, toString and makes the class final
public class Task {

    @NotNull
    @Valid
    String idTask;

    @NotNull
    @Valid
    Instant creationDate;

    @NotNull
    @Valid
    Instant updateDate;

    @NotNull
    @Valid
    Instant endDate;

    @NotNull
    @Valid
    String description;

    @NotNull
    @Valid
    String title;

    @NotNull
    @Valid
    String priority;

}
