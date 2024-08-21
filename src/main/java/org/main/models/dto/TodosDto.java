package org.main.models.dto;

import java.util.Date;

public class TodosDto {
    private int id;
    private String title;
    private String description;
    private Date createdAt;
    private Date endAt;
    private boolean completed;
    private String username;

    public TodosDto(int id, String title, String description, Date createdAt, Date endAt, boolean completed, String username) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.endAt = endAt;
        this.completed = completed;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getEndAt() {
        return endAt;
    }

    public void setEndAt(Date endAt) {
        this.endAt = endAt;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
