package com.example.demo.controller;

import com.example.demo.model.Room;
import com.example.demo.model.Schedule;
import com.example.demo.model.Issue;
import com.example.demo.repository.RoomRepository;
import com.example.demo.repository.ScheduleRepository;
import com.example.demo.repository.IssueRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/admin")
public class AdminController {

    private final RoomRepository roomRepo;
    private final ScheduleRepository scheduleRepo;
    private final IssueRepository issueRepo;

    public AdminController(RoomRepository roomRepo,
            ScheduleRepository scheduleRepo,
            IssueRepository issueRepo) {
        this.roomRepo = roomRepo;
        this.scheduleRepo = scheduleRepo;
        this.issueRepo = issueRepo;
    }

    // View all rooms
    @GetMapping("/rooms")
    public List<Room> getAllRooms() {
        return roomRepo.findAll();
    }

    // View all bookings
    @GetMapping("/bookings")
    public List<Schedule> getAllBookings() {
        return scheduleRepo.findAll();
    }

    // View reported issues
    @GetMapping("/issues")
    public List<Issue> getIssues() {
        return issueRepo.findAll();
    }
}
