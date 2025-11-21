package ch.zli.m223.dto;

public class BudgetDTO {
    private int amount;
    private int month;
    private int year;
    private Long userId;
    private Long categoryId;
    
    // Getters & Setters
    public int getAmount() { return amount; }
    public void setAmount(int amount) { 
        this.amount = amount; 
    }
    
    public int getMonth() { return month; }
    public void setMonth(int month) { 
        this.month = month; 
    }
    
    public int getYear() { return year; }
    public void setYear(int year) { 
        this.year = year; 
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