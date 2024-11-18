package com.example.day22exerciseq1.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Project {
    @NotEmpty (message = "id must not be empty")
    @Size(min = 2,message = "id must at least be 2 character long")
    private String  id;

    @NotEmpty (message = "title must not be empty")
    @Size(min = 8,message = "title must at least be 8 character long")
    private String title;

    @NotEmpty (message = "description must not be empty")
    @Size(min = 15,message = "description must at least be 15 character long")
    private String description;


    @NotEmpty (message = "status must not be empty")
    @Pattern(regexp = "^(Not Started|In Progress|Completed)$",message = "invalid status")
    private String status;

    @NotEmpty (message = "company name must not be empty")
    @Size(min = 6,message = "companyName must at least be 6 character long")
    private String companyName;





}
