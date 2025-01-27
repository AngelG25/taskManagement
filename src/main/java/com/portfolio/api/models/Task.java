package com.portfolio.api.models;

import lombok.Value;
import org.jetbrains.annotations.NotNull;

import javax.validation.Valid;
import java.time.Instant;

@Value
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
