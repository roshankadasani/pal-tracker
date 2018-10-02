package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private HashMap<Long, TimeEntry> repo = new HashMap<>();
    private long incrementer = 1;

    public InMemoryTimeEntryRepository() {
    }

    public TimeEntry create(TimeEntry entry) {

        entry.setId(incrementer);
        repo.put(new Long(incrementer), entry);
        incrementer++;
        return entry;
    }

    public TimeEntry find(long id){
        return repo.get(new Long(id));
    }

    public List<TimeEntry> list()
    {
        return new ArrayList<TimeEntry>(repo.values());
    }

    public TimeEntry update(long id, TimeEntry entry) {
        TimeEntry toUpdate = repo.get(id);
        entry.setId(id);
        if (toUpdate == null) {
            return null;
        }
        repo.put(id, entry);
        return entry;
    }

    public void delete(long id) {
        repo.remove(id);
    }


}
