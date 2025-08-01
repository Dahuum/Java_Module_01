public class Program {
    public static void main(String[] args) {
        User u1 = new User("John", 100);
        User u2 = new User("Mike", 200);
        User u3 = new User("Alice", 300);

        // Header
        System.out.println("╔═════════════════════════════════╗");
        System.out.println("║        👥 USER REGISTRY         ║");
        System.out.println("╚═════════════════════════════════╝");
        System.out.println();
        
        // User creation confirmation
        System.out.println("✨ Creating users...");
        System.out.println();
        
        // Display users with style
        System.out.println("📋 REGISTERED USERS:");
        System.out.println("┌─────────────────────────────┐");
        System.out.printf("│ 👤 %-8s │ ID: #%-3d │ 💰 $%-4d │%n", u1.getName(), u1.getId(), u1.getBalance());
        System.out.printf("│ 👤 %-8s │ ID: #%-3d │ 💰 $%-4d │%n", u2.getName(), u2.getId(), u2.getBalance());
        System.out.printf("│ 👤 %-8s │ ID: #%-3d │ 💰 $%-4d │%n", u3.getName(), u3.getId(), u3.getBalance());
        System.out.println("└─────────────────────────────┘");
        
        System.out.println();
        System.out.println("📊 SUMMARY:");
        System.out.println("   • Total users created: 3");
        System.out.println("   • Total balance: $" + (u1.getBalance() + u2.getBalance() + u3.getBalance()));
        System.out.println("   • Average balance: $" + (u1.getBalance() + u2.getBalance() + u3.getBalance()) / 3);
        
        System.out.println();
        System.out.println("╔═════════════════════════════════╗");
        System.out.println("║         ✅ COMPLETED!           ║");
        System.out.println("║    All users registered!        ║");
        System.out.println("╚═════════════════════════════════╝");
    }
}