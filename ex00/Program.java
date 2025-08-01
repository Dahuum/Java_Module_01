public class Program {
    public static void main(String[] args) {
        User Sender = new User("Abdurrahman", 0);
        User Recipient = new User("Ismail", 5000);
        
        // Header
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║           💸 TRANSACTION SYSTEM           ║");
        System.out.println("╚══════════════════════════════════════════╝");
        System.out.println();
        
        // Initial balances
        System.out.println("🏦 INITIAL ACCOUNT BALANCES");
        System.out.println("══════════════════════════════");
        printUserBalance("📤 Sender", Sender);
        printUserBalance("📥 Recipient", Recipient);
        
        System.out.println();
        System.out.println("⚡ PROCESSING TRANSACTIONS...");
        System.out.println("────────────────────────────────");
        
        try {
            // Transaction 1
            System.out.println("💳 Transaction #1: OUTCOME (-$200)");
            Transaction T1 = new Transaction(Recipient, Sender, Transaction.TransferCategory.OUTCOME, -200);
            System.out.println("   ✅ Transaction completed successfully!");
            System.out.println();
            
            System.out.println("📊 BALANCES AFTER TRANSACTION #1:");
            printUserBalance("📤 Sender", Sender);
            printUserBalance("📥 Recipient", Recipient);
            
            System.out.println();
            System.out.println("────────────────────────────────");
            
            // Transaction 2
            System.out.println("💳 Transaction #2: INCOME (+$200)");
            Transaction T2 = new Transaction(Sender, Recipient, Transaction.TransferCategory.INCOME, 200);
            System.out.println("   ✅ Transaction completed successfully!");
            System.out.println();
            
            System.out.println("📊 FINAL BALANCES:");
            printUserBalance("📤 Sender", Sender);
            printUserBalance("📥 Recipient", Recipient);
            
            System.out.println();
            System.out.println("╔══════════════════════════════════════════╗");
            System.out.println("║          🎉 ALL TRANSACTIONS DONE! 🎉    ║");
            System.out.println("║            Money transferred safely       ║");
            System.out.println("╚══════════════════════════════════════════╝");
            
        } catch (IllegalArgumentException e) {
            System.out.println();
            System.out.println("╔══════════════════════════════════════════╗");
            System.out.println("║               ❌ ERROR ❌                 ║");
            System.out.println("╚══════════════════════════════════════════╝");
            System.err.println("🚫 Transaction Failed: " + e.getMessage());
            System.out.println();
            
            System.out.println("📊 CURRENT BALANCES (No changes made):");
            printUserBalance("📤 Sender", Sender);
            printUserBalance("📥 Recipient", Recipient);
        }
    }
    
    // Helper method for consistent balance formatting
    private static void printUserBalance(String label, User user) {
        String balanceColor = user.getBalance() >= 0 ? "💚" : "❤️";
        System.out.printf("   %s %-15s │ Balance: %s $%-8.2f%n", 
            label, user.getName(), balanceColor, (double)user.getBalance());
    }
}