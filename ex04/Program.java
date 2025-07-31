import java.util.Scanner;

public class Program {
    
    public static void main(String [] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("--> ");
        String text = scanner.nextLine();
        
        int counts[] = new int[65536];
        for (char c : text.toCharArray()) counts[c]++;
        
        // for (int i = 0; i < 65536; i++) {
        //     if (counts[i] > 0)
        //         System.out.println((char)i + " appears " + counts[i] + " times");
        // }
        
        char[] allChars = new char[1000]; 
        int[] allCounts = new int[1000];
        int totalFound = 0;
        
        for (int i = 0; i < 65536; i++) {
            if (counts[i] > 0) {
                allChars[totalFound] = (char)i;
                allCounts[totalFound] = counts[i];
                totalFound++;
            }
        }
        for (int i = 0; i < totalFound - 1; i++) {
            for (int j = 0; j < totalFound - 1 - i; j++) {
                boolean shouldSwap = false;
                
                if (allCounts[j] < allCounts[j + 1]) shouldSwap = true;
                else if (allCounts[j] == allCounts[j + 1] && allChars[j] > allChars[j+1]) shouldSwap = true;
                
                if (shouldSwap) {
                    char tempChar = allChars[j];
                    allChars[j] = allChars[j + 1];
                    allChars[j+1] = tempChar;
                    
                    int tempCount = allCounts[j];
                    allCounts[j] = allCounts[j+1];
                    allCounts[j+1] = tempCount;
                }
            }
        }
        
        
        char[] topChars = new char[1000];
        int [] topCounts = new int[1000];
        int found = Math.min(10, totalFound);
        
        for (int i = 0; i < found; i++) {
            topChars[i] = allChars[i];
            topCounts[i] = allCounts[i];
        }
        
        // System.out.println("Sorted:");
        // for (int i = 0; i < topChars.length && topChars[i] != '\0'; i++) {
        //     System.out.println(topChars[i] + " appears " + topCounts[i] + " times");
        // }
        
        int maxFreq = 0;
        for (int i = 0; i < found; i++) {
            if (topCounts[i] > maxFreq) maxFreq = topCounts[i];
        }
        
        int[] scaledHeights = new int[found];
        for (int i = 0; i < found; i++) {
            if (maxFreq <= 10)
                scaledHeights[i] = topCounts[i];
            else
                scaledHeights[i] = (topCounts[i] * 10) / maxFreq;
        }
        
        // Test: print scaled heights
        // System.out.println("Scaled heights:");
        // for (int i = 0; i < found; i++) {
        //     System.out.println(topChars[i] + " → height " + scaledHeights[i]);
        // }
        
        // Fixed printing logic - translated from the correct version
        int maxHeight = 10; // or whatever your max scaled height is
        int totalLines = maxHeight + 2; // +2 for count row and character row
        
        System.out.println();
        for (int i = 0; i < totalLines; i++) {
            for (int j = 0; j < found; j++) {
                if (topChars[j] != 0) {
                    if (i + scaledHeights[j] + 2 == totalLines) {
                        // Print count at the top of each bar
                        System.out.format("%3d", topCounts[j]);
                    } else if (i == totalLines - 1) {
                        // Print character at the bottom row
                        System.out.format("%3c", topChars[j]);
                    } else if (i + scaledHeights[j] >= maxHeight) {
                        // Print # for the bar body
                        System.out.format("%3c", '#');
                    } else {
                        // Print spaces
                        System.out.print("   ");
                    }
                }
            }
            System.out.println();
        }
        
        scanner.close();
    }
    
}