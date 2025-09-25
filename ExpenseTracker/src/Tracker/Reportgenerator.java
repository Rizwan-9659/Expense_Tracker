package Tracker;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Reportgenerator {
    public static void exportToCSV(List<Expense> expenses, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.append("Date,Category,Description,Amount\n");
            for (Expense e : expenses) {
                writer.append(e.getDate() + "," + e.getCategory() + "," + e.getDescription() + "," + e.getAmount() + "\n");
            }
            System.out.println("📄 Report exported to " + filename);
        } catch (IOException e) {
            System.out.println("❌ Error exporting CSV: " + e.getMessage());
        }
    }
}
