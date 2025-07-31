import java.util.UUID;

public class Transaction {
    public static enum TransferCategory {
            INCOME,
            OUTCOME
    }

    private UUID Id;
    private User Recipient;
    private User Sender;
    private TransferCategory category;
    private int TransferAmount;
    
    public Transaction(User Sender, User Recipient, TransferCategory category, int amount) {
        if ((category == TransferCategory.INCOME && amount < 0) || (category == TransferCategory.OUTCOME && amount > 0))
            throw new IllegalArgumentException("Invalid amount: INCOME must be positive, OUTCOME must be negative.");
        
        if ((category == TransferCategory.OUTCOME) && ((Sender.getBalance() + amount) < 0))
            throw new IllegalArgumentException("Insufficient balance for OUTCOME transaction.");
            
        this.Id = UUID.randomUUID();
        this.Recipient = Recipient; this.Sender = Sender; this.category = category; this.TransferAmount = amount;
        
        if (category == TransferCategory.OUTCOME)
            Sender.setBalance(Sender.getBalance() + amount);
        else if (category == TransferCategory.INCOME)
            Sender.setBalance(Sender.getBalance() + amount);

    }
    
    public UUID getId() { return Id; }
    public User getSender() { return Sender; }
    public User getRecipient() { return Recipient; }
    public TransferCategory getCategory() { return category; }
    public int getTransferAmount() { return TransferAmount; }

}