package com.example.day22exerciseq1.Controller;

import com.example.day22exerciseq1.ApiResponse.ApiResponse;
import com.example.day22exerciseq1.Model.Project;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/project")

public class ProjectController {

    ArrayList<Project> projects = new ArrayList<>();

    @GetMapping("/get/all")
    public ResponseEntity GetAllProjects() {
        return ResponseEntity.ok(projects);
    }

    @PostMapping("/add")
    public ResponseEntity addNewProject(@RequestBody @Valid Project project, Errors error) {

        if (error.hasErrors()){
            String message = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        projects.add(project);
        return ResponseEntity.status(200).body(new ApiResponse("Project has been added successfully"));
    }

    @PutMapping("/update/{index}")
    public ResponseEntity updateProject (@PathVariable int index, @RequestBody @Valid Project project, Errors error) {
        if (error.hasErrors()){
            String message = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        projects.set(index, project);
        return ResponseEntity.status(200).body("Project updated successfully");
    }


    public ResponseEntity deleteProject (@PathVariable int index){
        projects.remove(index);
        return ResponseEntity.status(200).body("Project deleted successfully");
    }


    @PutMapping("/change/status/{index}")
    public ResponseEntity changeStatus(@PathVariable int index) {
        if (projects.get(index).getStatus().equals("Not Started")){
            projects.get(index).setStatus("In Progress");
        }else if (projects.get(index).getStatus().equals("In Progress")){
            projects.get(index).setStatus("Completed");
        }else if (projects.get(index).getStatus().equals("Completed")) {
            projects.get(index).setStatus("Not Started");
        }

        return ResponseEntity.status(200).body("Status changed successfully");
    }



    @GetMapping("/get/title/{title}")
    public ResponseEntity getProjectbyTitle(@PathVariable String title) {

        for (Project p : projects) {
            if (p.getTitle().equals(title)) {
                return ResponseEntity.status(200).body(p);
            }
        }
        return ResponseEntity.status(400).body("Project Not Available");

    }


    @GetMapping("/get/company/{company}")
    public ResponseEntity getProjectByCompany(@PathVariable String company) {
        ArrayList<Project> companyProjects = new ArrayList<>();
        for (Project p : projects) {
            if (p.getCompanyName().equals(company)) {
                companyProjects.add(p);
            }
        }
        return ResponseEntity.status(200).body(companyProjects);
    }






}
