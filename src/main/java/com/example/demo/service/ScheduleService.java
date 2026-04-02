package com.example.demo.service;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.*;
import com.example.demo.controller.*;
import com.example.demo.config.*;




import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ScheduleService {

    private final ScheduleRepository repo;

    public ScheduleService(ScheduleRepository repo) {
        this.repo = repo;
    }

    public String bookRoom(Schedule schedule) {
        if (schedule.getRoomName() == null || schedule.getDate() == null || 
            schedule.getStartTime() == null || schedule.getEndTime() == null) {
            return "Incomplete booking details provided.";
        }

        List<Schedule> existing =
                repo.findByRoomNameAndDate(schedule.getRoomName(), schedule.getDate());

        for (Schedule s : existing) {
            if (s.getStartTime() != null && s.getEndTime() != null) {
                boolean conflict =
                        schedule.getStartTime().isBefore(s.getEndTime()) &&
                        schedule.getEndTime().isAfter(s.getStartTime());

                if (conflict) {
                    return "Room already booked for this time!";
                }
            }
        }

        repo.save(schedule);
        return "Room booked successfully";
    }

    public List<Schedule> getAllSchedules() {
        return repo.findAll();
    }
}