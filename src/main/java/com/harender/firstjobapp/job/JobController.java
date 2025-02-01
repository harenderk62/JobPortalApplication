package com.harender.firstjobapp.job;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private JobService jobService;

    public JobController(JobService jobService){
        this.jobService = jobService;
    }

    @GetMapping("")
    public ResponseEntity<List<Job>> findAll(){
        return new ResponseEntity<>(jobService.findAll(),HttpStatus.OK);
    } 

    @PostMapping("")
    public ResponseEntity<String> createJob(@RequestBody Job job){
        jobService.createJob(job);
        return new ResponseEntity<>("Job Created Successfully",HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id){
        Job job = jobService.getJobById(id);
        if(job != null){
            return new ResponseEntity<>(job, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJobById(@PathVariable Long id){
        boolean isDeleted = jobService.deleteJobById(id);

        if(isDeleted){
            return ResponseEntity.ok("Deleted successfully");
        }

        return new ResponseEntity("Job Not Found",HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateJobById(@PathVariable Long id,@RequestBody Job updatedJob){
        boolean isUpdated = jobService.updateJobById(id,updatedJob);

        if(isUpdated){
            return new ResponseEntity<>("Job Updated Successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Job not Found", HttpStatus.NOT_FOUND);
    }
}
