package io.pivotal.pal.tracker;

import java.time.LocalDate;

public class TimeEntry {

    public TimeEntry() {

    }

    public TimeEntry(long id, long projectId, long userId, LocalDate date, int hours) {
        this.id = id;
        this.projectId = projectId;
        this.userId = userId;
        this.date = date;
        this.hours = hours;
    }

    public TimeEntry(long id, long projectId, LocalDate date) {
        this.id = id;
        this.projectId = projectId;
        this.userId = 1;
        this.date = date;
        this.hours = 1;
    }

    public TimeEntry(long projectId, long userId, LocalDate date, int hours) {
        //this.id = 1;
        this.projectId = projectId;
        this.userId = userId;
        this.date = date;
        this.hours = hours;
    }

    public long getId() {
        return id;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        TimeEntry other = (TimeEntry) obj;

        return this.getId() == other.getId();
    }


    @Override
    public String toString() {
        return "TimeEntry{" +
                "id=" + id +
                ", projectId=" + projectId +
                ", userId=" + userId +
                ", date=" + date +
                ", hours=" + hours +
                '}';
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    private long id;
    private long projectId;
    private long userId;
    private LocalDate date;
    private int hours;
}
