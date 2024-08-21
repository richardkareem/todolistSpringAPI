package org.main.models.dto;

import java.util.Date;

public class TodosSaveDto {
    private int id;
    private String title;
    private String description;
    private Date createdAt;
    private Date endAt;
    private boolean completed;
    private int id_user;

    public TodosSaveDto(int id, String title, String description, Date createdAt, Date endAt, boolean completed, int id_user) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.endAt = endAt;
        this.completed = completed;
        this.id_user = id_user;
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

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
}
