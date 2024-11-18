package com.example.day22exerciseq2.Controller;

import com.example.day22exerciseq2.ApiResponse.ApiResponse;
import com.example.day22exerciseq2.Model.Event;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/event")
public class EventController {

    ArrayList<Event> events= new ArrayList<Event>();

    @GetMapping("/get/all")
    public ResponseEntity getEvents() {
        return ResponseEntity.status(200).body(events);
    }


    @PostMapping("/add")
    public ResponseEntity setEvents(@RequestBody @Valid Event event, Errors errors) {
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        events.add(event);
        return ResponseEntity.status(200).body(new ApiResponse("Event added successfully"));
    }


    @PutMapping("/update/{index}")
    public ResponseEntity updateEvent(@PathVariable int index , @RequestBody @Valid Event event, Errors errors) {

        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }

                events.set(index, event);
                return ResponseEntity.status(200).body("Event updated successfully");


    }

    // endpoint to delete an event
    @DeleteMapping("/delete/{index}")
    public ResponseEntity deleteEvent(@PathVariable int index) {

                events.remove(index);
                return ResponseEntity.status(200).body(new ApiResponse("Event deleted successfully"));

    }

    // endpoint to change the capacity of an event
    @Validated
    @PutMapping("/update/capacity/{index}/{capacity}")
    public ResponseEntity getEventById(@PathVariable @Min(value = 25, message = "Capacity must be a number and at least 25") int capacity,
                                       @PathVariable int index) {

                events.get(index).setCapacity(capacity);
                return ResponseEntity.status(200).body(new ApiResponse("Event capacity updated successfully"));
    }

    // endpoint to search for an event by id
    @GetMapping("get/id/{id}")
    public ResponseEntity getEventById(@PathVariable String id) {
        for (Event e : events) {
            if (e.getId().equals(id)) {
                return ResponseEntity.status(200).body(e);

            }
        }
        return null;
    }





}
