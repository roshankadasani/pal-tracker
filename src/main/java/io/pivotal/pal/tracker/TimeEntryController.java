package io.pivotal.pal.tracker;

import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TimeEntryController {

    private TimeEntryRepository t;
    private final CounterService counter;
    private final GaugeService gauge;

    public TimeEntryController(TimeEntryRepository ter,
            CounterService counter,
            GaugeService gauge)
    {
        t =ter;
        this.counter = counter;
        this.gauge = gauge;
    }

    @PostMapping("/time-entries")
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry te) {
        //need to set this to created...
        te = t.create(te);
        counter.increment("TimeEntry.created");
        gauge.submit("timeEntries.count", t.list().size());
        return new ResponseEntity(te,HttpStatus.CREATED);
    }

    @GetMapping("/time-entries/{id}")
    public ResponseEntity read(@PathVariable("id") long i) {

        TimeEntry x = t.find(i);
        ResponseEntity re = null;

        if (x == null)
        {
           re = new ResponseEntity(HttpStatus.NOT_FOUND);

        } else {
            counter.increment("TimeEntry.read");
            re = new ResponseEntity(x, HttpStatus.OK);
        }
        return  re;
    }

    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
        counter.increment("TimeEntry.listed");
        return  ResponseEntity.ok(t.list());
    }

    @PutMapping("/time-entries/{id}")
    public ResponseEntity update(@PathVariable("id") long id, @RequestBody TimeEntry te) {


        TimeEntry x = t.update(id, te);
        ResponseEntity re = null;

        if (x == null)
        {
            re = new ResponseEntity(HttpStatus.NOT_FOUND);

        } else {
            counter.increment("TimeEntry.updated");
            re = new ResponseEntity(x, HttpStatus.OK);
        }
        return  re;
    }



    @DeleteMapping("/time-entries/{id}")
    public ResponseEntity delete(@PathVariable("id") long id) {
        t.delete(id);
        counter.increment("TimeEntry.deleted");
        gauge.submit("timeEntries.count", t.list().size());
        return   new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
