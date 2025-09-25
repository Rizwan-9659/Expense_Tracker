package Tracker;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ExpenseTrackerApp {

    // ANSI Colors
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String CYAN = "\u001B[36m";

    static Scanner sc = new Scanner(System.in);
    static ExpenseManager manager = new ExpenseManager();

    public static void main(String[] args) {
        while (true) {
            System.out.println(CYAN + "\n💰 Expense Tracker" + RESET);
            System.out.println(YELLOW + "1. Add Expense" + RESET);
            System.out.println(YELLOW + "2. View Monthly Report" + RESET);
            System.out.println(YELLOW + "3. Export Report to CSV" + RESET);
            System.out.println(YELLOW + "4. Exit" + RESET);
            System.out.print(GREEN + "Enter choice: " + RESET);

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addExpense();
                    break;
                case 2:
                    viewReport();
                    break;
                case 3:
                    exportCSV();
                    break;
                case 4:
                    System.out.println(GREEN + "👋 Exiting... Bye!" + RESET);
                    return;
                default:
                    System.out.println(RED + "❌ Invalid option." + RESET);
            }
        }
    }

    private static void addExpense() {
        try {
            System.out.print(CYAN + "Enter date (YYYY-MM-DD): " + RESET);
            LocalDate date = LocalDate.parse(sc.nextLine());

            System.out.print(CYAN + "Enter category (Food, Travel, Shopping, etc.): " + RESET);
            String category = sc.nextLine();

            System.out.print(CYAN + "Enter description: " + RESET);
            String desc = sc.nextLine();

            System.out.print(CYAN + "Enter amount: " + RESET);
            double amount = sc.nextDouble();
            sc.nextLine(); // consume newline

            manager.addExpense(new Expense(date, category, desc, amount));
        } catch (Exception e) {
            System.out.println(RED + "❌ Invalid input. Try again." + RESET);
            sc.nextLine();
        }
    }

    private static void viewReport() {
        System.out.print(CYAN + "Enter month (1-12): " + RESET);
        int month = sc.nextInt();
        System.out.print(GREEN + "Enter year: " + RESET);
        int year = sc.nextInt();
        sc.nextLine(); // consume newline

        List<Expense> monthly = manager.getExpensesByMonth(month, year);
        if (monthly.isEmpty()) {
            System.out.println(RED + "No expenses for this month." + RESET);
            return;
        }

        System.out.println(CYAN + "\n📊 Monthly Report:" + RESET);
        monthly.forEach(System.out::println);

        System.out.println(YELLOW + "\n💡 Totals by Category:" + RESET);
        Map<String, Double> totals = manager.getCategoryTotals(month, year);
        totals.forEach((cat, total) -> System.out.println(YELLOW + cat + ": ₹" + total + RESET));

        System.out.println(GREEN + "\nTotal Expenses: ₹" + manager.getTotalByMonth(month, year) + RESET);
    }

    private static void exportCSV() {
        System.out.print(GREEN + "Enter filename (example: report.csv): " + RESET);
        String filename = sc.nextLine();

        List<Expense> allExpenses = manager.getAllExpenses();
        if (allExpenses.isEmpty()) {
            System.out.println(RED + "No expenses to export." + RESET);
            return;
        }

        Reportgenerator.exportToCSV(allExpenses, filename);
    }
}
