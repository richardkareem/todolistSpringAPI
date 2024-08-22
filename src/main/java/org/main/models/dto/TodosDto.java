package org.main.models.dto;

import java.time.LocalDateTime;
import java.util.Date;

public class TodosDto {
    private int id;
    private String title;
    private String description;
    private String created_at;
    private String end_at;
    private boolean completed;
    private String username;
    private String type_todo;

    public TodosDto(int id, String title, String description, String created_at, String end_at, boolean completed, String username, String type_todo) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.created_at = created_at;
        this.end_at = end_at;
        this.completed = completed;
        this.username = username;
        this.type_todo = type_todo;
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

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getEnd_at() {
        return end_at;
    }

    public void setEnd_at(String end_at) {
        this.end_at = end_at;
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

    public String getType_todo() {
        return type_todo;
    }

    public void setType_todo(String type_todo) {
        this.type_todo = type_todo;
    }
}
