package com.example.demo.controller;

import com.example.demo.model.Room;
import com.example.demo.service.RoomService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService service;

    public RoomController(RoomService service) {
        this.service = service;
    }

    // Add room
    @PostMapping
    public Room addRoom(@RequestBody Room room) {
        return service.addRoom(room);
    }

    // Get all rooms (supports both /rooms and /rooms/all)
    @GetMapping({"", "/all"})
    public List<Room> getRooms() {
        return service.getRooms();
    }

    // Delete room
    @DeleteMapping("/{id}")
    public String deleteRoom(@PathVariable Long id) {
        service.deleteRoom(id);
        return "Room deleted successfully";
    }

    // Check available rooms
    @GetMapping("/available")
    public List<Room> getAvailableRooms(
            @RequestParam String date,
            @RequestParam String start,
            @RequestParam String end) {

        return service.findAvailableRooms(
                LocalDate.parse(date),
                LocalTime.parse(start),
                LocalTime.parse(end)
        );
    }
}