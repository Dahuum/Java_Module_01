import java.util.Scanner;

public class Program1 {
    
    public static String buildChart(int minGrade) {
        switch (minGrade) {
            case 1: return "=>";
            case 2: return "==>";
            case 3: return "===>";
            case 4: return "====>";
            case 5: return "=====>";
            case 6: return "======>";
            case 7: return "=======>";
            case 8: return "========>";
            case 9: return "=========>";
            default: return ">";
        }
    }
    
    public static int findMin(String gradesLine) {
        String[] grades = gradesLine.split(" ");
        int min = Integer.parseInt(grades[0]);
        
        for (int i = 1; i < 5; i++) {
            int grade = Integer.parseInt(grades[i]);
            if (grade < min) min = grade;
        }
        return min;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int expectedWeek = 1;
        StringBuilder output = new StringBuilder();
        
        while (true) {
            String line = scanner.nextLine();
            if (line.equals("42")) break;
            
            int weekNum = Integer.parseInt(line.substring(5));
            if (weekNum != expectedWeek) {
                System.err.println("IllegalArgument");
                System.exit(-1);
            }
            
            String gradesLine = scanner.nextLine();
            int min = findMin(gradesLine);
            
            output.append("Week ").append(weekNum).append(" ");
            output.append(buildChart(min)).append("\n");
            
            expectedWeek++;
        }
        
        System.out.print(output.toString());
        scanner.close();
    }
}