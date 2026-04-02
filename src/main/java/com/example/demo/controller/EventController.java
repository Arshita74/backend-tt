package com.example.demo.controller;

import com.example.demo.model.Event;
import com.example.demo.repository.EventRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/events")
public class EventController {

    private final EventRepository repo;

    public EventController(EventRepository repo){
        this.repo = repo;
    }

    @PostMapping
    public Event createEvent(@RequestBody Event event){
        return repo.save(event);
    }

    @GetMapping({"", "/all"})
    public List<Event> getEvents(){
        return repo.findAll();
    }

    @DeleteMapping("/{id}")
    public String deleteEvent(@PathVariable Long id){
        repo.deleteById(id);
        return "Event deleted successfully";
    }
}