package com.example.demo.controller;

import com.example.demo.model.Issue;
import com.example.demo.repository.IssueRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/issues")
public class IssueController {

    private final IssueRepository repo;

    public IssueController(IssueRepository repo){
        this.repo = repo;
    }

    @PostMapping
    public Issue reportIssue(@RequestBody Issue issue){
        return repo.save(issue);
    }

    @GetMapping({"", "/all"})
    public List<Issue> getIssues(){
        return repo.findAll();
    }

    @PutMapping({"/resolve/{id}", "/{id}"})
    public String resolveIssue(@PathVariable Long id){
        return repo.findById(id).map(issue -> {
            issue.setStatus("RESOLVED");
            repo.save(issue);
            return "Issue resolved successfully";
        }).orElse("Issue not found with ID: " + id);
    }

    @DeleteMapping("/{id}")
    public String deleteIssue(@PathVariable Long id){
        repo.deleteById(id);
        return "Issue deleted successfully";
    }
}