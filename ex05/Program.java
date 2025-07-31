import java.util.Scanner;
/* 
    * Absolute correctness of data is guaranteed, 
        except for sequential ordering of classes when populating the timetable
*/

public class Program {
    
    public static void printFinalTimetable(String[] students, int[][] timetable, int studentCount,
                                           int[] classHours, int[] classDates, int classCount,
                                           String[] weekDaysRef) {
    
        int colWidth = 13; // uniform width
    
        // 1. Print header with pipes
        System.out.print("|" + String.format("%-" + colWidth + "s", "Student"));
        for (int j = 0; j < classCount; j++) {
            String dayName = weekDaysRef[classDates[j] % 7];
            String label = String.format("%d:00 %s %d", classHours[j], dayName, classDates[j]);
            System.out.print("|" + String.format("%-" + colWidth + "s", label));
        }
        System.out.println("|");
    
        // 2. Print rows
        for (int i = 0; i < studentCount; i++) {
            System.out.print("|" + String.format("%-" + colWidth + "s", students[i]));
            for (int j = 0; j < classCount; j++) {
                String mark = switch (timetable[i][j]) {
                    case 1 -> "1";
                    case -1 -> "-1";
                    default -> "";
                };
                System.out.print("|" + String.format("%-" + colWidth + "s", mark));
            }
            System.out.println("|");
        }
    }
    
    public static void main ( String [] args ) {
        String[] weekDaysRef = {"MO", "TU", "WE", "TH", "FR", "SA", "SU"};

        Scanner scanner = new Scanner(System.in);
        int studentCount = 0, dotCount = 0, i = 0, slotCount = 0, classInstanceCount = 0;
        
        String [] students = new String[10];
        
        int weeklyHours [] = new int[10];
        String [] weeklyDays = new String[10];
        
        int[] classHours = new int[100];
        int[] classDates = new int[100];

        
        while (dotCount == 0) {
            System.out.print("--> ");
            String Name = scanner.nextLine();
            // System.out.println("Name: " + "'" + Name + "'");
            if (Name.length() == 1 && Name.charAt(0) == '.') {
                dotCount = 1;
                continue;
            };
            students[i++] = Name;
            studentCount++;
        }
        
        i = 0;
        while (dotCount == 1) {
            System.out.print("--> ");
            String line = scanner.nextLine();
            if (line.length() == 1 && line.charAt(0) == '.') {
                dotCount = 2;
                continue;
            };
            String [] words = line.split(" ");
            weeklyHours[i] = Integer.parseInt(words[0]);
            weeklyDays[i] = words[1];
            i++; slotCount++;
        }
        
        for (int day = 1; day <= 30; day++) {
            int weekDayIndex = (day + 0) % 7;
            String DayName = weekDaysRef[weekDayIndex];
            
            for (int j = 0; j < slotCount; j++) {
                if (weeklyDays[j].equals(DayName)) {
                    classDates[classInstanceCount] = day;
                    classHours[classInstanceCount] = weeklyHours[j];
                    classInstanceCount++;
                }
            }
        }
        
        int[][] timetable = new int[studentCount][classInstanceCount];
        
        while (true) {
            System.out.print("--> ");
            String line = scanner.nextLine().trim();
            
            if (line.equals(".")) break;
        
            String[] parts = line.split("\\s+");
            if (parts.length != 4) {
                System.out.println("Invalid input, expected: <name> <x> <y> <status>");
                continue;
            }
        
            String name = parts[0];
            int x = Integer.parseInt(parts[1]);
            int y = Integer.parseInt(parts[2]);
            String status = parts[3];
        
            int studentIndex = 0;
            for (; studentIndex < studentCount; studentIndex++) {
                if (students[studentIndex].equals(name)) break;
            }
            
            int classIndex = 0;
            for (; classIndex < classInstanceCount; classIndex++) {
                if (classHours[classIndex] == x && classDates[classIndex] == y) break;
            }
            
            timetable[studentIndex][classIndex] = status.equals("HERE") ? 1 : -1;
        }

        printFinalTimetable(students, timetable, studentCount,
                            classHours, classDates, classInstanceCount,
                            weekDaysRef);

        
        
        scanner.close();
    }
}