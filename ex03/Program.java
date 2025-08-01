import java.util.UUID;

public class Program {
    public static void main(String[] args) {
        // Header
        System.out.println("╔════════════════════════════════════════════════╗");
        System.out.println("║           💳 TRANSACTION MANAGER 💳           ║");
        System.out.println("╚════════════════════════════════════════════════╝");
        System.out.println();

        // Create two users
        User u1 = new User("John", 1000);
        User u2 = new User("Mike", 500);
        
        System.out.println("👥 INITIAL SETUP:");
        System.out.println("┌────────────────────────────────┐");
        System.out.printf("│ 👤 %-10s │ Balance: $%-6d │%n", u1.getName(), u1.getBalance());
        System.out.printf("│ 👤 %-10s │ Balance: $%-6d │%n", u2.getName(), u2.getBalance());
        System.out.println("└────────────────────────────────┘");
        System.out.println();

        // Create transactions
        System.out.println("⚡ CREATING TRANSACTIONS...");
        Transaction t1 = new Transaction(u1, u2, Transaction.TransferCategory.OUTCOME, -300);
        Transaction t2 = new Transaction(u2, u1, Transaction.TransferCategory.INCOME, 300);
        
        System.out.println("   ✅ Transaction #1: " + u1.getName() + " → " + u2.getName() + " ($300 OUTCOME)");
        System.out.println("   ✅ Transaction #2: " + u2.getName() + " → " + u1.getName() + " ($300 INCOME)");

        // Link transactions to users
        TransactionsList u1Transactions = new TransactionsLinkedList();
        TransactionsList u2Transactions = new TransactionsLinkedList();

        u1Transactions.addTransaction(t1);
        u2Transactions.addTransaction(t2);
        
        System.out.println("   📝 Transactions logged to user accounts");
        System.out.println();

        // Print user balances after transactions
        System.out.println("💰 ACCOUNT BALANCES AFTER TRANSACTIONS:");
        System.out.println("┌────────────────────────────────┐");
        String u1Icon = u1.getBalance() >= 1000 ? "💚" : u1.getBalance() >= 500 ? "💛" : "❤️";
        String u2Icon = u2.getBalance() >= 1000 ? "💚" : u2.getBalance() >= 500 ? "💛" : "❤️";
        System.out.printf("│ %s %-10s │ Balance: $%-6d │%n", u1Icon, u1.getName(), u1.getBalance());
        System.out.printf("│ %s %-10s │ Balance: $%-6d │%n", u2Icon, u2.getName(), u2.getBalance());
        System.out.println("└────────────────────────────────┘");
        System.out.println();

        // Show all transactions for user1
        System.out.println("📋 TRANSACTION HISTORY FOR " + u1.getName().toUpperCase() + ":");
        System.out.println("═══════════════════════════════════════════════");
        
        Transaction[] u1TransArray = u1Transactions.toArray();
        if (u1TransArray.length > 0) {
            for (int i = 0; i < u1TransArray.length; i++) {
                Transaction t = u1TransArray[i];
                String categoryIcon = t.getCategory() == Transaction.TransferCategory.OUTCOME ? "📤" : "📥";
                String amountColor = t.getTransferAmount() < 0 ? "🔴" : "🟢";
                
                System.out.println("┌─────────────────────────────────────────────┐");
                System.out.printf("│ %s Transaction #%d                          │%n", categoryIcon, i + 1);
                System.out.printf("│ 🆔 ID: %-35s │%n", shortenUUID(t.getId()));
                System.out.printf("│ 👤 To: %-35s │%n", t.getRecipient().getName());
                System.out.printf("│ %s Amount: $%-30d │%n", amountColor, t.getTransferAmount());
                System.out.printf("│ 📊 Category: %-27s │%n", t.getCategory());
                System.out.println("└─────────────────────────────────────────────┘");
            }
        } else {
            System.out.println("   📭 No transactions found");
        }
        System.out.println();

        // Remove a transaction and show remaining
        System.out.println("🗑️  REMOVING TRANSACTION...");
        try {
            u1Transactions.removeTransactionById(t1.getId());
            System.out.println("   ✅ Transaction " + shortenUUID(t1.getId()) + " removed successfully!");
        } catch (TransactionNotFoundException e) {
            System.out.println("   ❌ Error: " + e.getMessage());
        }

        System.out.println();
        System.out.println("📋 REMAINING TRANSACTIONS FOR " + u1.getName().toUpperCase() + ":");
        System.out.println("═══════════════════════════════════════════════");
        
        Transaction[] remainingTransactions = u1Transactions.toArray();
        if (remainingTransactions.length > 0) {
            for (int i = 0; i < remainingTransactions.length; i++) {
                Transaction t = remainingTransactions[i];
                System.out.println("   🆔 Transaction ID: " + shortenUUID(t.getId()));
            }
        } else {
            System.out.println("   📭 No transactions remaining");
        }
        
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════╗");
        System.out.println("║              🎉 PROCESS COMPLETE! 🎉           ║");
        System.out.println("║         All operations executed successfully   ║");
        System.out.println("╚════════════════════════════════════════════════╝");
    }
    
    // Helper method to shorten UUID for display
    private static String shortenUUID(UUID uuid) {
        return uuid.toString().substring(0, 8) + "...";
    }
}