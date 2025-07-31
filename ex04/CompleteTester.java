import java.io.*;
import java.util.*;

public class CompleteTester {
    
    static class TestCase {
        String name;
        String input;
        String expected;
        String[] potentialIssues;
        
        TestCase(String name, String input, String expected, String... issues) {
            this.name = name;
            this.input = input;
            this.expected = expected;
            this.potentialIssues = issues;
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        TestCase[] testCases = {
            new TestCase("Normal mixed case", "DASDASWLKLOKTERER", 
                "Should show normal histogram with varying heights",
                "Basic functionality test"),
            
            new TestCase("All same counts", "AAABBBCCC", 
                "All bars should be same height, all show count 3",
                "Scaling might fail", "All bars might collapse to same height"),
            
            new TestCase("One character dominates", "A".repeat(40) + "B", 
                "A should fill full height (10), B should be tiny (height 1 or 0)",
                "B might disappear completely", "Scaling precision issues"),
            
            new TestCase("Single character type", "A".repeat(7), 
                "Should show single bar with count 7 at appropriate height",
                "Might crash with only one character", "Height calculation issues"),
            
            new TestCase("Two equal characters", "A".repeat(5) + "B".repeat(5), 
                "Both A and B should have same height, both show count 5",
                "Heights might differ due to rounding", "Display alignment issues"),
            
            new TestCase("All appear once", "ABCDEFGHIJ", 
                "All bars should be height 1, all show count 1",
                "Might all scale to 0", "Could crash with all equal counts"),
            
            new TestCase("Powers of 2 pattern", "A" + "B".repeat(2) + "C".repeat(4) + "D".repeat(8) + "E".repeat(16), 
                "Should show exponential growth: A=1, B=2, C=4, D=8, E=16",
                "Scaling might not handle exponential growth well"),
            
            new TestCase("Empty string", "", 
                "Should handle gracefully - no crash, maybe show nothing or error message",
                "Might crash", "Division by zero", "Array bounds issues"),
            
            new TestCase("Single character", "A", 
                "Should show single bar with count 1",
                "Might crash", "Scaling issues with single data point"),
            
            new TestCase("Linear staircase", "A" + "B".repeat(2) + "C".repeat(3) + "D".repeat(4) + "E".repeat(5), 
                "Should show perfect staircase: A=1, B=2, C=3, D=4, E=5",
                "Nice test for proportional scaling"),
            
            new TestCase("More than 10 unique", "ABCDEFGHIJKLMNOPQRSTUVWXYZ", 
                "Should only show top 10 most frequent (all have count 1 here)",
                "Might show more than 10", "Could crash", "Selection logic issues"),
            
            new TestCase("Very close large counts", "A".repeat(100) + "B".repeat(99) + "C".repeat(98) + "D".repeat(97), 
                "Heights should be very close, might all appear same due to scaling",
                "Precision loss in scaling", "All bars might be same height"),
            
            new TestCase("Extreme difference", "A".repeat(1000) + "B".repeat(1) + "C".repeat(2), 
                "A should be max height, B and C might disappear (scale to 0)",
                "B and C might not show", "Integer overflow possible"),
            
            new TestCase("Perfect 10-height test", generatePerfectTenTest(), 
                "Should show perfect scaling from height 1 to 10",
                "Ultimate scaling test"),
            
            new TestCase("Numbers and symbols", "1234567890!@#$%^&*()", 
                "Check how non-letters are handled",
                "Might crash with non-letters", "Character handling issues"),
            
            new TestCase("Case sensitivity", "AaAaAaBbBbCcCc", 
                "Check if A/a treated as same or different",
                "Case handling might be inconsistent"),
            
            new TestCase("Ascending counts", "A" + "B".repeat(2) + "C".repeat(3) + "D".repeat(4) + "E".repeat(5), 
                "Should show increasing heights left to right",
                "Good visual test for proper ordering"),
            
            new TestCase("Single massive count", "A".repeat(500), 
                "Should show single bar filling full height",
                "Performance issues", "Display formatting with large numbers"),
            
            new TestCase("Many small equal counts", "ABCDEFGHIJKLMNOPQRSTUVWXYZ".repeat(2), 
                "Many chars with count 2 each, should show top 10",
                "Selection and display of equal counts"),
            
            new TestCase("Fibonacci pattern", "A" + "B" + "C".repeat(2) + "D".repeat(3) + "E".repeat(5) + "F".repeat(8), 
                "Natural growth pattern: 1,1,2,3,5,8",
                "Tests natural scaling progression")
        };
        
        System.out.println("=".repeat(70));
        System.out.println("            COMPLETE HISTOGRAM TESTER");
        System.out.println("=".repeat(70));
        System.out.println();
        
        boolean runAll = false;
        System.out.println("Choose testing mode:");
        System.out.println("1. Interactive (step by step)");
        System.out.println("2. Generate all test files");
        System.out.println("3. Show all test cases at once");
        System.out.print("Enter choice (1-3): ");
        
        String choice = scanner.nextLine();
        
        switch(choice) {
            case "1":
                runInteractiveMode(testCases, scanner);
                break;
            case "2":
                generateTestFiles(testCases);
                break;
            case "3":
                showAllTestCases(testCases);
                break;
            default:
                System.out.println("Invalid choice, running interactive mode...");
                runInteractiveMode(testCases, scanner);
        }
    }
    
    static void runInteractiveMode(TestCase[] testCases, Scanner scanner) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("INTERACTIVE MODE - Step by step testing");
        System.out.println("=".repeat(50));
        
        for (int i = 0; i < testCases.length; i++) {
            TestCase test = testCases[i];
            
            System.out.println("\nTEST " + (i+1) + "/" + testCases.length + ": " + test.name);
            System.out.println("-".repeat(50));
            
            if (test.input.length() > 60) {
                System.out.println("Input: \"" + test.input.substring(0, 60) + "...\"");
            } else {
                System.out.println("Input: \"" + test.input + "\"");
            }
            System.out.println("Length: " + test.input.length() + " characters");
            System.out.println("Expected: " + test.expected);
            
            if (test.potentialIssues.length > 0) {
                System.out.println("Watch for: " + String.join(", ", test.potentialIssues));
            }
            
            System.out.println("\n>>> COPY THIS INPUT TO YOUR PROGRAM:");
            System.out.println("┌" + "─".repeat(Math.max(test.input.length(), 20)) + "┐");
            System.out.println("│" + test.input + " ".repeat(Math.max(0, 20 - test.input.length())) + "│");
            System.out.println("└" + "─".repeat(Math.max(test.input.length(), 20)) + "┘");
            
            System.out.println("\nPress ENTER after testing this case...");
            scanner.nextLine();
            
            System.out.print("Any issues? (y/n or notes): ");
            String response = scanner.nextLine().trim();
            
            if (!response.toLowerCase().startsWith("n") && !response.isEmpty()) {
                System.out.println("✗ Issue noted for: " + test.name);
                if (response.length() > 1) {
                    System.out.println("  Notes: " + response);
                }
            } else {
                System.out.println("✓ " + test.name + " - OK");
            }
        }
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("TESTING COMPLETE!");
        System.out.println("=".repeat(50));
    }
    
    static void generateTestFiles(TestCase[] testCases) {
        try {
            // Create individual test files
            for (int i = 0; i < testCases.length; i++) {
                String filename = "test_" + (i+1) + "_" + 
                    testCases[i].name.toLowerCase().replaceAll("[^a-z0-9]", "_") + ".txt";
                PrintWriter writer = new PrintWriter(filename);
                writer.println(testCases[i].input);
                writer.close();
            }
            
            // Create master test runner
            PrintWriter runner = new PrintWriter("run_tests.bat");
            runner.println("@echo off");
            runner.println("echo Starting histogram tests...");
            runner.println("echo.");
            
            for (int i = 0; i < testCases.length; i++) {
                String filename = "test_" + (i+1) + "_" + 
                    testCases[i].name.toLowerCase().replaceAll("[^a-z0-9]", "_") + ".txt";
                    
                runner.println("echo ========================================");
                runner.println("echo TEST " + (i+1) + ": " + testCases[i].name);
                runner.println("echo Input length: " + testCases[i].input.length());
                runner.println("echo Expected: " + testCases[i].expected);
                runner.println("echo ========================================");
                runner.println("java Program < " + filename);
                runner.println("echo.");
                runner.println("pause");
                runner.println("echo.");
            }
            runner.close();
            
            System.out.println("Generated " + testCases.length + " test files:");
            for (int i = 0; i < testCases.length; i++) {
                String filename = "test_" + (i+1) + "_" + 
                    testCases[i].name.toLowerCase().replaceAll("[^a-z0-9]", "_") + ".txt";
                System.out.println("  " + filename);
            }
            System.out.println("\nCreated: run_tests.bat");
            System.out.println("\nUsage: run_tests.bat");
            System.out.println("Or individual: java Program < test_1_normal_mixed_case.txt");
            
        } catch (IOException e) {
            System.out.println("Error creating files: " + e.getMessage());
        }
    }
    
    static void showAllTestCases(TestCase[] testCases) {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("ALL TEST CASES - Copy and paste each input");
        System.out.println("=".repeat(70));
        
        for (int i = 0; i < testCases.length; i++) {
            TestCase test = testCases[i];
            System.out.println("\n" + (i+1) + ". " + test.name.toUpperCase());
            System.out.println("   Expected: " + test.expected);
            if (test.potentialIssues.length > 0) {
                System.out.println("   Watch for: " + String.join(", ", test.potentialIssues));
            }
            System.out.println("   INPUT: " + test.input);
            System.out.println("   " + "-".repeat(60));
        }
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("Copy each INPUT line to your Program.java when prompted");
        System.out.println("=".repeat(70));
    }
    
    static String generatePerfectTenTest() {
        // Generate input where scaling should give perfect heights 1,2,3...10
        StringBuilder sb = new StringBuilder();
        sb.append("A".repeat(5));      // Should scale to 1
        sb.append("B".repeat(10));     // Should scale to 2  
        sb.append("C".repeat(15));     // Should scale to 3
        sb.append("D".repeat(20));     // Should scale to 4
        sb.append("E".repeat(25));     // Should scale to 5
        sb.append("F".repeat(30));     // Should scale to 6
        sb.append("G".repeat(35));     // Should scale to 7
        sb.append("H".repeat(40));     // Should scale to 8
        sb.append("I".repeat(45));     // Should scale to 9
        sb.append("J".repeat(50));     // Should scale to 10
        return sb.toString();
    }
}