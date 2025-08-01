public class Program {
    public static void main(String[] args) {
        // Create users
        User u1 = new User("John", 100);
        User u2 = new User("Mike", 200);
        User u3 = new User("Alice", 300);

        // Pretty header
        System.out.println("╔═══════════════════════════════════════╗");
        System.out.println("║           USER MANAGEMENT             ║");
        System.out.println("╚═══════════════════════════════════════╝");
        System.out.println();

        // Display created users with style
        System.out.println("📋 Created Users:");
        System.out.println("┌─────────────────────────────────────┐");
        System.out.printf("│ %-10s │ ID: %-3d │ Balance: $%-6.2f │%n", u1.getName(), u1.getId(), (double)u1.getBalance());
        System.out.printf("│ %-10s │ ID: %-3d │ Balance: $%-6.2f │%n", u2.getName(), u2.getId(), (double)u2.getBalance());
        System.out.printf("│ %-10s │ ID: %-3d │ Balance: $%-6.2f │%n", u3.getName(), u3.getId(), (double)u3.getBalance());
        System.out.println("└─────────────────────────────────────┘");
        System.out.println();
        
        // Create and populate list
        UsersList list = new UsersArrayList();
        list.addUser(u1);
        list.addUser(u2);
        
        System.out.println("✅ Added users to list!");
        System.out.println();
        
        try {
            System.out.println("🔍 Testing User Lookup:");
            System.out.println("═══════════════════════");
            
            // Test 1: Get user by ID
            System.out.println("🆔 Looking up user with ID 1:");
            User foundUser1 = list.getUserById(1);
            System.out.println("   ➤ Found: " + foundUser1.getName() + " 👤");
            
            // Test 2: Get user by index
            System.out.println("📍 Getting user at index 0:");
            User userAtIndex0 = list.getUserByIndex(0);
            System.out.println("   ➤ Balance: $" + userAtIndex0.getBalance() + " 💰");
            
            // Display total users
            System.out.println("👥 Total users in list: " + list.getNumberOfUsers());
            
            System.out.println();
            System.out.println("─────────────────────────");
            
            // Test 3: Another lookup
            System.out.println("🆔 Looking up user with ID 2:");
            User foundUser2 = list.getUserById(2);
            System.out.println("   ➤ Found: " + foundUser2.getName() + " 👤");
            
            System.out.println("📍 Getting user at index 1:");
            User userAtIndex1 = list.getUserByIndex(1);
            System.out.println("   ➤ Balance: $" + userAtIndex1.getBalance() + " 💰");
            
            System.out.println("👥 Total users in list: " + list.getNumberOfUsers());
            
            System.out.println();
            System.out.println("╔═══════════════════════════════════════╗");
            System.out.println("║            ✨ SUCCESS! ✨             ║");
            System.out.println("║      All operations completed!       ║");
            System.out.println("╚═══════════════════════════════════════╝");
            
        } catch (UserNotFoundException e) {
            System.out.println();
            System.out.println("╔═══════════════════════════════════════╗");
            System.out.println("║              ❌ ERROR ❌              ║");
            System.out.println("╚═══════════════════════════════════════╝");
            System.err.println("🚫 " + e.getMessage());
            System.out.println();
        }
    }
}