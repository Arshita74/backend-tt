package com.example.demo.config;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalTime;

@Configuration
public class DatabaseSeeder {

    @Bean
    public CommandLineRunner initData(
            RoomRepository roomRepo,
            UserRepository userRepo,
            IssueRepository issueRepo,
            EventRepository eventRepo,
            ScheduleRepository scheduleRepo) {
        return args -> {
            if (userRepo.count() == 0) {
                User u = new User();
                u.setUsername("admin");
                u.setPassword("admin123");
                u.setRole("ADMIN");
                userRepo.save(u);
            }

            if (roomRepo.count() == 0) {
                Room r1 = new Room();
                r1.setRoomName("Conference A");
                r1.setRoomType("Conference");
                r1.setCapacity(50);
                roomRepo.save(r1);

                Room r2 = new Room();
                r2.setRoomName("Meeting B");
                r2.setRoomType("Meeting");
                r2.setCapacity(15);
                roomRepo.save(r2);

                Room r3 = new Room();
                r3.setRoomName("Auditorium");
                r3.setRoomType("Event");
                r3.setCapacity(300);
                roomRepo.save(r3);
            }
            
            if (issueRepo.count() == 0) {
                Issue i1 = new Issue();
                i1.setRoomName("Conference A");
                i1.setIssueType("Hardware");
                i1.setDescription("Projector bulb is dead");
                i1.setReportedBy("admin");
                i1.setStatus("OPEN");
                issueRepo.save(i1);
            }
            
            if (scheduleRepo.count() == 0) {
                Schedule s1 = new Schedule();
                s1.setRoomName("Conference A");
                s1.setEventName("Weekly Sync");
                s1.setDate(LocalDate.now());
                s1.setStartTime(LocalTime.of(10, 0));
                s1.setEndTime(LocalTime.of(11, 0));
                s1.setBookedBy("admin");
                scheduleRepo.save(s1);
            }
            
            if (eventRepo.count() == 0) {
                Event e1 = new Event();
                e1.setEventName("Tech Meetup");
                e1.setOrganizer("admin");
                e1.setRoomName("Auditorium");
                e1.setDate(LocalDate.now().plusDays(2));
                e1.setDescription("Monthly tech meetup for developers");
                eventRepo.save(e1);
            }
        };
    }
}
