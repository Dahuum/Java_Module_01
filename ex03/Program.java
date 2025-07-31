import java.util.Scanner;

public class Program {
    
    public static boolean isNumber(String num) {
        try {
            Integer.parseInt(num);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }
    
    public static void printExit() {
        System.err.println("IllegalArgument");
        System.exit(-1);
    }
    
    public static String buildChart(int minGrade) {
        switch (minGrade) {
            case 1: return "=>\n";
            case 2: return "==>\n";
            case 3: return "===>\n";
            case 4: return "====>\n";
            case 5: return "=====>\n";
            case 6: return "======>\n";
            case 7: return "=======>\n";
            case 8: return "========>\n";
            case 9: return "=========>\n";
            default: return ">";
        }
    }
    
    public static int checkGradesAndReturnMin(String gradesLine) {
        /* temporarily to parse, makanstorish as7aybe */
        String grades[] = gradesLine.split(" ");

        if (grades.length != 5) return -1;
        
        if (!isNumber(grades[0]) || Integer.parseInt(grades[0]) < 1 || Integer.parseInt(grades[0]) > 9) {
            return -1;
        }
        int min = Integer.parseInt(grades[0]);

        
        for (int i = 1; i < grades.length; i++) {
            if (!isNumber(grades[i])) return -1;
            int currentGrade = Integer.parseInt(grades[i]);
            if (currentGrade < 1 || currentGrade > 9) return -1;
            
            // System.out.println("Checking if current min (" + min + ") > new grade (" + currentGrade + ")... Result: " + (min > currentGrade));
            if (min > currentGrade) min = currentGrade;
            
        }
        return min;
    }
    
    public static void main (String [] args) {
        Scanner scanner = new Scanner(System.in);
        int expectedWeek = 1;
        StringBuilder finalOutput = new StringBuilder();
        int min;
        
        while (true) {
            System.out.print("-> ");
            String weekLine = scanner.nextLine();
            
            if (weekLine.equals("42")) break;
            
            if (weekLine.startsWith("Week ")) {
                String weekNum = weekLine.substring(5);
                if (!isNumber(weekNum)) printExit();
                
                if (Integer.parseInt(weekNum) != expectedWeek)printExit();
                else {
                    System.out.print("-> ");
                    String gradesLine = scanner.nextLine();
                    min = checkGradesAndReturnMin(gradesLine);
                    if (min == -1) printExit();
                    else {
                        finalOutput.append("Week " + expectedWeek + " ");
                        finalOutput.append(buildChart(min));
                    }
                }
                expectedWeek++;
            } else printExit();
        }
        System.out.print(finalOutput);
        scanner.close();
    }
}