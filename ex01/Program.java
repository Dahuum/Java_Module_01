import java.util.Scanner;

public class Program {
    public static void main (String [] args) {
        boolean isPrime = true;
        int counter = 0, number = 0;
        
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        scanner.close();
        
        try {
            number = Integer.parseInt(line);
            if (number <= 1) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            System.err.println("IllegalArgument");
            System.exit(-1);
        }
        
        /*  num = 7 case: 
                Check 1 (for i=2): 4 <= 7 is true. The loop runs, and iterations becomes 1.
                Check 2 (for i=3): 9 <= 7 is false. The loop stops.
        */
        for (int i = 2; i * i <= number; i++) {
            counter++;
            
            if (number % i == 0) {
                isPrime = false;
                break;
            }
        }
        /*  */
        if (isPrime) counter++;
        System.out.println(isPrime + " " + counter);
    }
}