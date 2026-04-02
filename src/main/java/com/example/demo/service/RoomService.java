package com.example.demo.service;

import com.example.demo.model.Room;
import com.example.demo.model.Schedule;
import com.example.demo.repository.RoomRepository;
import com.example.demo.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class RoomService {

    private final RoomRepository repo;
    private final ScheduleRepository scheduleRepo;

    public RoomService(RoomRepository repo, ScheduleRepository scheduleRepo) {
        this.repo = repo;
        this.scheduleRepo = scheduleRepo;
    }

    // Add new room
    public Room addRoom(Room room) {
        return repo.save(room);
    }

    // Get all rooms
    public List<Room> getRooms() {
        return repo.findAll();
    }

    // Delete room
    public void deleteRoom(Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
        }
    }

    // Find available rooms
    public List<Room> findAvailableRooms(LocalDate date, LocalTime start, LocalTime end) {

        List<Room> allRooms = repo.findAll();
        List<Schedule> schedules = scheduleRepo.findByDate(date);

        List<Room> availableRooms = new ArrayList<>();

        for (Room room : allRooms) {

            boolean booked = false;

            for (Schedule s : schedules) {

                if (s.getRoomName() != null && s.getRoomName().equals(room.getRoomName())
                    && s.getStartTime() != null && s.getEndTime() != null) {

                    boolean conflict =
                            start.isBefore(s.getEndTime()) &&
                            end.isAfter(s.getStartTime());

                    if (conflict) {
                        booked = true;
                        break;
                    }
                }
            }

            if (!booked) {
                availableRooms.add(room);
            }
        }

        return availableRooms;
    }
}