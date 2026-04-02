package com.example.demo.controller;

import com.example.demo.model.Schedule;
import com.example.demo.repository.ScheduleRepository;
import com.example.demo.service.ScheduleService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.time.LocalDate;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    private final ScheduleRepository scheduleRepo;
    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleRepository scheduleRepo, ScheduleService scheduleService) {
        this.scheduleRepo = scheduleRepo;
        this.scheduleService = scheduleService;
    }

    // Book room
    @PostMapping("/book")
    public String bookRoom(@RequestBody Schedule schedule) {
        return scheduleService.bookRoom(schedule);
    }

    // Get all bookings
    @GetMapping({"", "/all"})
    public List<Schedule> getAllBookings() {
        return scheduleRepo.findAll();
    }

    // Cancel booking
    @DeleteMapping({ "/cancel/{id}", "/{id}" })
    public String cancelBooking(@PathVariable Long id) {
        if (scheduleRepo.existsById(id)) {
            scheduleRepo.deleteById(id);
            return "Booking cancelled successfully";
        }
        return "Booking not found with ID: " + id;
    }

    // Get bookings by date
    @GetMapping("/date")
    public List<Schedule> getBookingsByDate(@RequestParam String date) {
        return scheduleRepo.findByDate(LocalDate.parse(date));
    }

    // Get bookings for a specific room
    @GetMapping("/room")
    public List<Schedule> getRoomBookings(@RequestParam String roomName) {
        return scheduleRepo.findByRoomName(roomName);
    }
}