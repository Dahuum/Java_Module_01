import java.util.Scanner;

public class Program {
    public static boolean isPrime(int num) {
        boolean isPrime = true;
        
        if (num <= 1) return false;
        if (num == 2) return isPrime;
        
        for (int i = 2; i * i <= num; i++) { 
            if (num % i == 0) {
                isPrime = false;
                break;
            }
        }
        
        return isPrime;
    }
    
    public static int calculateDigits(int num) {
        int result = 0;
        
        while (num > 0) {
            result += num % 10;
            num /= 10;
        }
        
        return result;
    }
    
    public static void main (String [] args) {
        Scanner scanner = new Scanner(System.in);
        int coffeeRequestCount = 0;
        
        
        while (true) {
            System.out.print("-->  ");
            int num = scanner.nextInt();
            
            if (num == 42) break;
            
            int number = calculateDigits(num);
            
            if (isPrime(number)) coffeeRequestCount++;
        }
        scanner.close();
        System.out.println("Count of coffee-request : " + coffeeRequestCount);   
    }
}