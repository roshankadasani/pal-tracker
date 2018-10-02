package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TimeEntryController {

    TimeEntryRepository repo;

    public TimeEntryController(TimeEntryRepository repo){
        this.repo = repo;
    }

    @PostMapping("/time-entries")
    @ResponseBody
    public ResponseEntity create(@RequestBody TimeEntry entry) {
        return new ResponseEntity(repo.create(entry),HttpStatus.CREATED);

    }

    @GetMapping("/time-entries/{id}")
    @ResponseBody
    public ResponseEntity<TimeEntry> read(@PathVariable("id") long id) {

        TimeEntry readEntry = repo.find(id);
        if (readEntry == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(readEntry, HttpStatus.OK);
    }

    @GetMapping("/time-entries")
    @ResponseBody
    public ResponseEntity<List<TimeEntry>> list() {
        return new ResponseEntity(repo.list(),HttpStatus.OK);
    }

    @PutMapping("/time-entries/{id}")
    @ResponseBody
    public ResponseEntity update(@PathVariable("id") long id, @RequestBody TimeEntry entry) {

        TimeEntry updated = repo.update(id, entry);

        if (updated == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(updated, HttpStatus.OK);
    }

    @DeleteMapping("/time-entries/{id}")
    @ResponseBody
    public ResponseEntity<TimeEntry> delete(@PathVariable("id") long id){
        repo.delete(id);
        return new ResponseEntity(null, HttpStatus.NO_CONTENT);
    }
}
