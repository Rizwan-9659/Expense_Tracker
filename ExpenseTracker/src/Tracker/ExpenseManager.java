package Tracker;

import java.util.*;
import java.time.LocalDate;
import java.util.stream.Collectors;

public class ExpenseManager {
    private List<Expense> expenses;

    public ExpenseManager() {
        expenses = new ArrayList<>();
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
        System.out.println("✅ Expense added: " + expense);
    }

    // Filter expenses by month
    public List<Expense> getExpensesByMonth(int month, int year) {
        return expenses.stream()
                .filter(e -> e.getDate().getMonthValue() == month && e.getDate().getYear() == year)
                .collect(Collectors.toList());
    }

    // Total amount for month
    public double getTotalByMonth(int month, int year) {
        return getExpensesByMonth(month, year)
                .stream()
                .mapToDouble(Expense::getAmount)
                .sum();
    }

    // Group by category
    public Map<String, Double> getCategoryTotals(int month, int year) {
        return getExpensesByMonth(month, year)
                .stream()
                .collect(Collectors.groupingBy(
                        Expense::getCategory,
                        Collectors.summingDouble(Expense::getAmount)
                ));
    }

    public List<Expense> getAllExpenses() {
        return expenses;
    }
}
