package com.portfolio.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import org.jetbrains.annotations.NotNull;

import javax.validation.Valid;
import java.time.Instant;

@Builder(toBuilder = true)  // Builder to be able to modify the class afterward
@Value                      // Create a private constructor, equals, hashCode, toString and makes the class final
@Jacksonized                // Allows jackson to deserialize the object sent from Postman
@JsonTypeName("TASK")
public class Task {

    // JsonProperty is used to deserialize or serialize a JSON into a Java Object or vice versa with Jackson
    // This serialization or deserialization takes place in the REST when the endpoint receives a JSON

    @JsonProperty(value = "id_task")    // Establish the value in the upcoming json as id_task
    @Valid
    String idTask;

    @JsonProperty(value = "creation_date", access = JsonProperty.Access.READ_ONLY)  // Establish the value in the upcoming json as creation_date
    @Valid
    Instant creationDate;

    @JsonProperty(value = "update_date")    // Establish the value in the upcoming json as update_date
    @Valid
    Instant updateDate;

    @JsonProperty(value = "end_date")       // Establish the value in the upcoming json as end_date
    @NotNull
    @Valid
    Instant endDate;

    @JsonProperty(value = "description", required = true)   // Establish the value in the upcoming json as description
    @NotNull
    @Valid
    String description;

    @JsonProperty(value = "title", required = true)     // Establish the value in the upcoming json as title
    @NotNull
    @Valid
    String title;

    @JsonProperty(value = "priority")       // Establish the value in the upcoming json as priority
    @NotNull
    @Valid
    String priority;

}
