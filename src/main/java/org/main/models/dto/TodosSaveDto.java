package org.main.models.dto;

import java.time.LocalDateTime;
import java.util.Date;

public class TodosSaveDto {
    private int id;
    private String title;
    private String description;
    private String createdAt;
    private String endAt;
    private boolean completed;
    private int id_user;
    private int id_type;

    public int getId_type() {
        return id_type;
    }

    public void setId_type(int id_type) {
        this.id_type = id_type;
    }

    public TodosSaveDto(int id, String title, String description, String createdAt, String endAt, boolean completed, int id_user, int id_type) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.endAt = endAt;
        this.completed = completed;
        this.id_user = id_user;
        this.id_type = id_type;
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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getEndAt() {
        return endAt;
    }

    public void setEndAt(String endAt) {
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
