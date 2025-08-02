import java.util.Scanner;
import java.util.UUID;

public class Menu {
    private final Scanner scanner = new Scanner(System.in);
    private final TransactionsService service = new TransactionsService();
    private final boolean isDev;

    public Menu(boolean isDev) {
        this.isDev = isDev;
    }

    public void run() {
        while (true) {
            printMenu();
            System.out.print("-> ");
            String input = scanner.nextLine().trim();

            switch (input) {
                case "1" -> addUser();
                case "2" -> viewBalance();
                case "3" -> performTransfer();
                case "4" -> viewTransactions();
                case "5" -> {
                    if (isDev) removeTransaction();
                    else System.out.println("Option not available.");
                }
                case "6" -> {
                    if (isDev) checkValidity();
                    else System.out.println("Option not available.");
                }
                case "7" -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }

            System.out.println("---------------------------------------------------------");
        }
    }

    private void printMenu() {
        System.out.println("1. Add a user");
        System.out.println("2. View user balances");
        System.out.println("3. Perform a transfer");
        System.out.println("4. View all transactions for a specific user");
        if (isDev) {
            System.out.println("5. DEV - remove a transfer by ID");
            System.out.println("6. DEV - check transfer validity");
        }
        System.out.println("7. Finish execution");
    }

    private void addUser() {
        System.out.println("Enter a user name and a balance");
        System.out.print("-> ");
        String[] input = scanner.nextLine().trim().split(" ");
        if (input.length != 2) {
            System.out.println("Invalid input format.");
            return;
        }

        String name = input[0];
        int balance;

        try {
            balance = Integer.parseInt(input[1]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid balance.");
            return;
        }

        service.addUser(name, balance);
        int id = service.getUserByIndex(service.getUsersCount() - 1).getId();
        System.out.println("User with id = " + id + " is added");
    }

    private void viewBalance() {
        System.out.println("Enter a user ID");
        System.out.print("-> ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            int balance = service.getUserBalance(id);
            String name = service.getUserById(id).getName();
            System.out.println(name + " - " + balance);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void performTransfer() {
        System.out.println("Enter a sender ID, a recipient ID, and a transfer amount");
        System.out.print("-> ");
        String[] input = scanner.nextLine().trim().split(" ");
        if (input.length != 3) {
            System.out.println("Invalid input format.");
            return;
        }

        try {
            int senderId = Integer.parseInt(input[0]);
            int recipientId = Integer.parseInt(input[1]);
            int amount = Integer.parseInt(input[2]);

            service.performTransfer(senderId, recipientId, amount);
            System.out.println("The transfer is completed");
        } catch (Exception e) {
            System.out.println("Transfer failed: " + e.getMessage());
        }
    }

    private void viewTransactions() {
        System.out.println("Enter a user ID");
        System.out.print("-> ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Transaction[] txs = service.getTransactionsForUser(id);
            for (Transaction t : txs) {
                String to = t.getRecipient().getName();
                int toId = t.getRecipient().getId();
                System.out.println("To " + to + "(id = " + toId + ") " +
                        t.getTransferAmount() + " with id = " + t.getId());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void removeTransaction() {
        System.out.println("Enter a user ID and a transfer ID");
        System.out.print("-> ");
        String[] input = scanner.nextLine().trim().split(" ");
        if (input.length != 2) {
            System.out.println("Invalid input format.");
            return;
        }

        try {
            int id = Integer.parseInt(input[0]);
            UUID uuid = UUID.fromString(input[1]);

            Transaction t = service.getTransactionById(id, uuid);
            service.removeTransaction(id, uuid);
            System.out.println("Transfer To " + t.getRecipient().getName() +
                    "(id = " + t.getRecipient().getId() + ") " +
                    (-t.getTransferAmount()) + " removed");
        } catch (Exception e) {
            System.out.println("Error removing: " + e.getMessage());
        }
    }

    private void checkValidity() {
        System.out.println("Check results:");
        Transaction[] unpaired = service.checkValidity();
        if (unpaired.length == 0) {
            System.out.println("All transactions are paired correctly.");
            return;
        }
        for (Transaction t : unpaired) {
            System.out.println(t.getRecipient().getName() + "(id = " + t.getRecipient().getId() + ")" +
                    " has an unacknowledged transfer id = " + t.getId() +
                    " from " + t.getSender().getName() + "(id = " + t.getSender().getId() + ")" +
                    " for " + (-t.getTransferAmount()));
        }
    }
}
