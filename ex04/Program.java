import java.util.UUID;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Program {
    public static void main(String[] args) {
        // Initialize service
        TransactionsService service = new TransactionsService();
        
        // Main header
        printHeader("💳 ADVANCED TRANSACTION MANAGEMENT SYSTEM", "═");
        System.out.println();

        try {
            // STEP 1: Add users
            printStepHeader("STEP 1", "👥 USER REGISTRATION");
            
            service.addUser("Alice", 1000);   // ID: 1
            service.addUser("Bob", 500);      // ID: 2
            service.addUser("Charlie", 300);  // ID: 3

            System.out.println("┌─────────────────────────────────────┐");
            System.out.printf("│ ✅ %-10s │ ID: %d │ Balance: $%-5d │%n", "Alice", 1, 1000);
            System.out.printf("│ ✅ %-10s │ ID: %d │ Balance: $%-5d │%n", "Bob", 2, 500);
            System.out.printf("│ ✅ %-10s │ ID: %d │ Balance: $%-5d │%n", "Charlie", 3, 300);
            System.out.println("└─────────────────────────────────────┘");
            
            System.out.println("📊 Total initial funds: $" + (1000 + 500 + 300));
            System.out.println();

            // STEP 2: Perform Transfers
            printStepHeader("STEP 2", "💸 EXECUTING TRANSFERS");
            
            System.out.println("🔄 Processing 4 transfer operations...");
            System.out.println();
            
            performTransferWithStyle(service, 1, 2, 200, "Alice", "Bob");
            performTransferWithStyle(service, 2, 3, 100, "Bob", "Charlie");
            performTransferWithStyle(service, 1, 2, 50, "Alice", "Bob");
            performTransferWithStyle(service, 3, 1, 150, "Charlie", "Alice");
            
            System.out.println("✨ All transfers completed successfully!");
            System.out.println();

            // STEP 3: Print balances
            printStepHeader("STEP 3", "💰 ACCOUNT BALANCES");
            
            int aliceBalance = service.getUserBalance(1);
            int bobBalance = service.getUserBalance(2);
            int charlieBalance = service.getUserBalance(3);
            
            System.out.println("┌─────────────────────────────────────┐");
            System.out.printf("│ %s %-10s │ Balance: $%-12d │%n", getBalanceIcon(aliceBalance), "Alice", aliceBalance);
            System.out.printf("│ %s %-10s │ Balance: $%-12d │%n", getBalanceIcon(bobBalance), "Bob", bobBalance);
            System.out.printf("│ %s %-10s │ Balance: $%-12d │%n", getBalanceIcon(charlieBalance), "Charlie", charlieBalance);
            System.out.println("├─────────────────────────────────────┤");
            System.out.printf("│ 📊 Total Balance: $%-16d │%n", aliceBalance + bobBalance + charlieBalance);
            System.out.println("└─────────────────────────────────────┘");
            System.out.println();

            // STEP 4: Print Bob's transactions before deletion
            printStepHeader("STEP 4", "📋 TRANSACTION HISTORY ANALYSIS");
            
            System.out.println("🔍 Analyzing Bob's transaction history...");
            Transaction[] bobTxs = service.getTransactionsForUser(2);
            
            System.out.println();
            System.out.println("📦 BOB'S TRANSACTION PORTFOLIO:");
            System.out.println("═══════════════════════════════════════════════════════");
            
            if (bobTxs.length == 0) {
                System.out.println("📭 No transactions found");
            } else {
                for (int i = 0; i < bobTxs.length; i++) {
                    Transaction t = bobTxs[i];
                    printTransactionCard(t, i + 1);
                }
                System.out.println("📊 Total transactions: " + bobTxs.length);
            }
            System.out.println();

            // STEP 5: Remove 1 transaction from Bob
            printStepHeader("STEP 5", "🗑️ TRANSACTION REMOVAL");
            
            if (bobTxs.length > 0) {
                UUID toDelete = bobTxs[0].getId();
                System.out.println("🎯 Target transaction: " + shortenUUID(toDelete));
                System.out.println("⚠️  Removing transaction from Bob's history...");
                
                service.removeTransaction(2, toDelete);
                
                System.out.println("✅ Transaction successfully removed!");
                System.out.println("🔄 Bob's transaction count: " + bobTxs.length + " → " + (bobTxs.length - 1));
            } else {
                System.out.println("⚠️  No transactions to remove for Bob");
            }
            System.out.println();

            // STEP 6: Group all transactions by UUID and check pairing
            printStepHeader("STEP 6", "🔗 TRANSACTION PAIRING ANALYSIS");
            
            System.out.println("🔍 Analyzing transaction pairs across all users...");
            System.out.println();

            Map<UUID, List<Transaction>> transactionMap = new HashMap<>();

            // Collect all transactions
            for (int i = 0; i < 3; i++) {
                Transaction[] txs = service.getUserByIndex(i).getTransactionsList().toArray();
                for (Transaction t : txs) {
                    UUID id = t.getId();
                    transactionMap.putIfAbsent(id, new ArrayList<>());
                    transactionMap.get(id).add(t);
                }
            }

            System.out.println("🧾 TRANSACTION PAIRING REPORT:");
            System.out.println("═══════════════════════════════════════════════════════");
            
            int pairedCount = 0;
            int unpairedCount = 0;
            
            for (Map.Entry<UUID, List<Transaction>> entry : transactionMap.entrySet()) {
                UUID uuid = entry.getKey();
                List<Transaction> list = entry.getValue();
                
                System.out.println();
                System.out.println("🆔 UUID: " + uuid);
                System.out.println("   📊 Transactions sharing this UUID: " + list.size());
                System.out.println("┌─────────────────────────────────────────────────┐");

                for (int i = 0; i < list.size(); i++) {
                    Transaction t = list.get(i);
                    String typeIcon = t.getCategory() == Transaction.TransferCategory.OUTCOME ? "📤" : "📥";
                    String amountColor = t.getTransferAmount() < 0 ? "🔴" : "🟢";
                    
                    System.out.printf("│ [%d] %s %s%n", i + 1, typeIcon, t.getCategory());
                    System.out.printf("│     👤 %s → %s%n", t.getSender().getName(), t.getRecipient().getName());
                    System.out.printf("│     💰 %s $%d%n", amountColor, Math.abs(t.getTransferAmount()));
                    
                    if (i < list.size() - 1) {
                        System.out.println("│     ─────────────────────────────────────────    │");
                    }
                }

                System.out.println("│                                                 │");
                if (list.size() == 2) {
                    System.out.println("│ ✅ STATUS: PROPERLY PAIRED                      │");
                    System.out.println("│    🔗 These 2 transactions form a valid pair    │");
                    pairedCount++;
                } else if (list.size() == 1) {
                    System.out.println("│ ❌ STATUS: UNPAIRED (SINGLE TRANSACTION)        │");
                    System.out.println("│    ⚠️  Missing corresponding transaction        │");
                    unpairedCount++;
                } else {
                    System.out.println("│ ❌ STATUS: MULTIPLE DUPLICATES                  │");
                    System.out.printf("│    🚨 %d transactions with same UUID!           │%n", list.size());
                    unpairedCount++;
                }
                System.out.println("└─────────────────────────────────────────────────┘");
            }
            
            System.out.println();
            System.out.println("📊 PAIRING SUMMARY:");
            System.out.println("   • Properly paired: " + pairedCount + " transactions");
            System.out.println("   • Unpaired: " + unpairedCount + " transactions");
            System.out.println("   • System integrity: " + (unpairedCount == 0 ? "✅ HEALTHY" : "⚠️ NEEDS ATTENTION"));
            System.out.println();

            // STEP 7: Show all users' current transactions
            printStepHeader("STEP 7", "📊 COMPREHENSIVE TRANSACTION OVERVIEW");
            
            System.out.println("📋 Complete transaction overview for all users:");
            System.out.println();
            
            for (int i = 0; i < 3; i++) {
                User u = service.getUserByIndex(i);
                Transaction[] txs = u.getTransactionsList().toArray();
                
                System.out.println("👤 " + u.getName().toUpperCase() + "'S TRANSACTION PORTFOLIO:");
                System.out.println("┌─────────────────────────────────────────────────┐");
                
                if (txs.length == 0) {
                    System.out.println("│ 📭 No transactions in portfolio                 │");
                } else {
                    System.out.printf("│ 📊 Portfolio size: %-28d │%n", txs.length);
                    System.out.println("├─────────────────────────────────────────────────┤");
                    
                    for (int j = 0; j < txs.length; j++) {
                        Transaction t = txs[j];
                        String typeIcon = t.getCategory() == Transaction.TransferCategory.OUTCOME ? "📤" : "📥";
                        String amountColor = t.getTransferAmount() < 0 ? "🔴" : "🟢";
                        
                        System.out.printf("│ %s #%-2d | %s → %s%n", typeIcon, j + 1, t.getSender().getName(), t.getRecipient().getName());
                        System.out.printf("│      Amount: %s $%-6d | UUID: %s │%n", 
                            amountColor, Math.abs(t.getTransferAmount()), shortenUUID(t.getId()));
                        
                        if (j < txs.length - 1) {
                            System.out.println("├─────────────────────────────────────────────────┤");
                        }
                    }
                }
                System.out.println("└─────────────────────────────────────────────────┘");
                System.out.println();
            }

            // Final success message
            printHeader("🎉 SYSTEM ANALYSIS COMPLETE! 🎉", "═");
            System.out.println("All operations executed successfully!");
            System.out.println("Transaction system is operating optimally.");
            printFooter("═");

        } catch (Exception e) {
            System.out.println();
            printHeader("❌ SYSTEM ERROR DETECTED ❌", "═");
            System.err.println("🚨 Critical Error: " + e.getMessage());
            System.err.println("📞 Please contact system administrator");
            printFooter("═");
        }
    }
    
    // Helper methods for beautiful formatting
    private static void printHeader(String title, String border) {
        int width = 60;
        String borderLine = border.repeat(width);
        int padding = (width - title.length() - 2) / 2;
        String paddedTitle = " ".repeat(padding) + title + " ".repeat(width - title.length() - padding - 2);
        
        System.out.println("╔" + borderLine + "╗");
        System.out.println("║" + paddedTitle + "║");
        System.out.println("╚" + borderLine + "╝");
    }
    
    private static void printFooter(String border) {
        System.out.println("╚" + border.repeat(60) + "╝");
    }
    
    private static void printStepHeader(String step, String title) {
        System.out.println("┌─ " + step + ": " + title + " " + "─".repeat(Math.max(0, 45 - step.length() - title.length())) + "┐");
    }
    
    private static void performTransferWithStyle(TransactionsService service, int from, int to, int amount, String fromName, String toName) throws Exception {
        service.performTransfer(from, to, amount);
        System.out.printf("   💸 %s → %s | $%d%n", fromName, toName, amount);
    }
    
    private static String getBalanceIcon(int balance) {
        if (balance >= 1000) return "💚";
        if (balance >= 500) return "💛";
        if (balance >= 100) return "🧡";
        return "❤️";
    }
    
    private static void printTransactionCard(Transaction t, int index) {
        String typeIcon = t.getCategory() == Transaction.TransferCategory.OUTCOME ? "📤" : "📥";
        String amountColor = t.getTransferAmount() < 0 ? "🔴" : "🟢";
        
        System.out.println("┌─────────────────────────────────────────────────┐");
        System.out.printf("│ %s Transaction #%-2d                              │%n", typeIcon, index);
        System.out.printf("│ 👥 %s → %s%n", t.getSender().getName(), t.getRecipient().getName());
        System.out.printf("│ %s Amount: $%-6d | Category: %s%n", amountColor, Math.abs(t.getTransferAmount()), t.getCategory());
        System.out.printf("│ 🆔 UUID: %-38s │%n", shortenUUID(t.getId()));
        System.out.println("└─────────────────────────────────────────────────┘");
    }
    
    private static String shortenUUID(UUID uuid) {
        return uuid.toString().substring(0, 8) + "...";
    }
}