public class Program {
    public static void main (String [] args) {
        User Sender = new User("Abdurrahman", 0);
        User Recipient = new User("Ismail", 5000);
        
        System.out.println("Sender: " + Sender.getName() + " " + ", Balance: " + Sender.getBalance());
        System.out.println("Recipient: " + Recipient.getName() + " " + ", Balance: " + Recipient.getBalance());
        System.out.println();       
        try {
            Transaction T1 = new Transaction(Recipient, Sender, Transaction.TransferCategory.OUTCOME , -200); 
            System.out.println(); 
            System.out.println("Sender: " + Sender.getName() + " " + ", Balance: " + Sender.getBalance());
            System.out.println("Recipient: " + Recipient.getName() + " " + ", Balance: " + Recipient.getBalance());
            Transaction T2 = new Transaction(Sender, Recipient, Transaction.TransferCategory.INCOME  ,  200);
            
            System.out.println(); 
            System.out.println("Sender: " + Sender.getName() + " " + ", Balance: " + Sender.getBalance());
            System.out.println("Recipient: " + Recipient.getName() + " " + ", Balance: " + Recipient.getBalance());
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());        
        }
    }
}