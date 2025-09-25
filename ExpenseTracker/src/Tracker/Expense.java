package Tracker;

import java.time.LocalDate;

public class Expense {
    private LocalDate date;
    private String category;
    private String description;
    private double amount;

    public Expense(LocalDate date, String category, String description, double amount) {
        this.date = date;
        this.category = category;
        this.description = description;
        this.amount = amount;
    }

    // Getters
    public LocalDate getDate() { return date; }
    public String getCategory() { return category; }
    public String getDescription() { return description; }
    public double getAmount() { return amount; }

    @Override
    public String toString() {
        return date + " | " + category + " | " + description + " | ₹" + amount;
    }
}
