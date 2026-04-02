package com.example.demo.repository;

import com.example.demo.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    // Find bookings for a room on a specific date
    List<Schedule> findByRoomNameAndDate(String roomName, LocalDate date);

    // Find bookings by date
    List<Schedule> findByDate(LocalDate date);

    // Find bookings by room
    List<Schedule> findByRoomName(String roomName);
}