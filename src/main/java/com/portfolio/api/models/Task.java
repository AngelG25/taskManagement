package com.portfolio.api.models;

import java.time.Instant;
import java.util.Objects;

public class Task {

    private Instant endDate;

    private Instant updateDate;

    private String description;

    private String idTask;

    private String title;

    private String priority;

    public Instant getCreationDate() {
        return creationDate;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    public Instant getUpdateDate() {
        return updateDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIdTask() {
        return idTask;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    private Instant creationDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(getCreationDate(), task.getCreationDate()) && Objects.equals(getEndDate(), task.getEndDate())
                && Objects.equals(getUpdateDate(), task.getUpdateDate()) && Objects.equals(getDescription(), task.getDescription())
                && Objects.equals(getIdTask(), task.getIdTask()) && Objects.equals(getTitle(), task.getTitle())
                && Objects.equals(getPriority(), task.getPriority());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCreationDate(), getEndDate(), getUpdateDate(), getDescription(), getIdTask(), getTitle(), getPriority());
    }

    @Override
    public String toString() {
        return "Task{" +
                "endDate=" + endDate +
                ", updateDate=" + updateDate +
                ", description='" + description + '\'' +
                ", idTask='" + idTask + '\'' +
                ", title='" + title + '\'' +
                ", priority='" + priority + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }
}
