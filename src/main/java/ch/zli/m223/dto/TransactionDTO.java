package ch.zli.m223.dto;

import java.time.LocalDateTime;

public class TransactionDTO {
    private int amount;
    private String description;
    private LocalDateTime date;
    private LocalDateTime createdAt;
    private Long userId;
    private Long categoryId;


    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { 
        this.userId = userId; 
    }
    
    public Long getCategoryId() { return categoryId; }
    public void setCategoryId(Long categoryId) {
         this.categoryId = categoryId; 
        }

}
