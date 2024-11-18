package com.example.day22exerciseq2.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Event {
    @NotEmpty (message = "id can not be empty")
    @Size(min = 2,message = "id must at least be 2 character long")
    private String id;

    @NotEmpty (message = "id can not be empty")
    @Size(min = 15,message = "description must at least be 15 character long")
    private String description;

    @NotNull(message = "capacity cannot be null")
    @Min(value = 25, message = "capacity must be a number and at least 25 ")
    private int capacity;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate startDate;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate endDate;
}
